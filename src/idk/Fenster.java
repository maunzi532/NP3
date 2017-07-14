package idk;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import z.dec.*;

public class Fenster
{
	public static JFrame fenster;
	public static Dimension flaeche;
	public static BufferedImage bild;
	public static Graphics2D gd;
	//private static long time = System.currentTimeMillis();

	public static void main(String[] args)
	{
		initFenster();
		XKarte.mark = new DecMark();
		XKarte.init();
		while(true)
		{
			TA.bereit();
			if(TA.take[8] == 2)
				System.exit(0);
			Point m = MouseInfo.getPointerInfo().getLocation();
			Point f = fenster.getLocation();
			XKarte.tick(gd, flaeche.width, flaeche.height, m.x - f.x, m.y - f.y);
			fenster.getGraphics().drawImage(bild, 0, 0, null);
			fu(5);
			if(fenster.getContentPane().getWidth() != flaeche.width || fenster.getContentPane().getHeight() != flaeche.height)
			{
				flaeche = fenster.getContentPane().getSize();
				bild = new BufferedImage(flaeche.width, flaeche.height, BufferedImage.TYPE_INT_ARGB_PRE);
				gd = bild.createGraphics();
			}
			/*long time2 = System.currentTimeMillis();
			System.out.println(time2 - time);
			time = time2;*/
		}
	}

	public static void initFenster()
	{
		fenster = new JFrame();
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension bildschirm = Toolkit.getDefaultToolkit().getScreenSize();
		flaeche = new Dimension(1200, 800);
		fenster.setSize(flaeche);
		fenster.setLocation((bildschirm.width - flaeche.width) / 2, (bildschirm.height - flaeche.height) / 2);
		bild = new BufferedImage(flaeche.width, flaeche.height, BufferedImage.TYPE_INT_ARGB_PRE);
		gd = bild.createGraphics();
		gd.setColor(Color.BLACK);
		gd.fillRect(0, 0, flaeche.width, flaeche.height);
		TA.einbau(fenster);
		fenster.setVisible(true);
		while(!fenster.hasFocus())
			fu(10);
	}

	public static void fu(long millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(InterruptedException ignored){}
	}
}