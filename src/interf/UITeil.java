package interf;

import java.awt.*;
import java.util.*;

public class UITeil
{
	public static final Font basicFont = new Font("Ubuntu", Font.PLAIN, 0);
	public int weg;
	public int cascade;
	public int[] location;
	public ArrayList<UITeil> in = new ArrayList<>();

	public UITeil(int cascade, int... location)
	{
		this.cascade = cascade;
		this.location = location;
	}

	public UITeil()
	{
		cascade = 0;
		location = new int[]{0, 1, 0, 1, 1, 1, 1, 1};
	}

	public void aufzeichnen(Graphics2D gd, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		aufzeichnen1(gd, xn, yn, xr2, yr2);
		for(UITeil t : in)
			t.aufzeichnen(gd, xn, yn, xr2, yr2);
	}

	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr){}

	public boolean registerClick(ArrayList<Clickbar> alle, int cx, int cy, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		for(int i = in.size() - 1; i >= 0; i--)
			if(in.get(i).registerClick(alle, cx, cy, xn, yn, xr2, yr2))
				return true;
		return false;
	}

	public int weg()
	{
		Boolean allC = null;
		for(int i = 0; i < in.size(); i++)
		{
			UITeil in2 = in.get(i);
			int weg1 = in2.weg();
			if(weg1 > 0)
			{
				if(in2.cascade == 2)
					weg = weg1;
				if(in2.cascade == 1 && allC == null)
					allC = true;
				in.remove(i);
				i--;
			}
			else if(in2.cascade == 1)
				allC = false;
		}
		if(allC != null && allC)
			weg = 1;
		return weg;
	}

	public void fitLine(Graphics2D gd, String s, int xm, int ym, int xr, int yr, boolean centerAlign)
	{
		fitLine(gd, basicFont, s, xm, ym, xr, yr, centerAlign);
	}

	public void fitLine(Graphics2D gd, Font font, String s, int xm, int ym, int xr, int yr, boolean centerAlign)
	{
		int fh = yr * 2;
		while(true)
		{
			gd.setFont(new Font(font.getFontName(), font.getStyle(), fh));
			FontMetrics fm = gd.getFontMetrics();
			int sw = fm.stringWidth(s);
			if(sw < xr * 2 || fh < 5)
			{
				gd.drawString(s, xm - (centerAlign ? sw / 2 : xr), ym + fm.getAscent() / 2 - fm.getDescent());
				return;
			}
			fh--;
		}
	}
}