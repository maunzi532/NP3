package interf;

import java.awt.*;
import java.util.*;

public class UITeil
{
	public static final Font basicFont = new Font("Ubuntu", Font.PLAIN, 0);
	public boolean weg;
	public int cascade;
	public int[] location;
	public ArrayList<UITeil> in = new ArrayList<>();

	public UITeil(int cascade, int... location)
	{
		this.cascade = cascade;
		this.location = location;
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

	public Clickbar registerClick(int cx, int cy, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		for(UITeil t : in)
		{
			Clickbar cl = t.registerClick(cx, cy, xn, yn, xr2, yr2);
			if(cl != null)
				return cl;
		}
		return registerClick2(xn, yn, xr2, yr2, cx, cy);
	}

	public Clickbar registerClick2(int xn, int yn, int xr, int yr, int cx, int cy)
	{
		return null;
	}

	public boolean weg()
	{
		Boolean allC = null;
		for(int i = 0; i < in.size(); i++)
		{
			UITeil in2 = in.get(i);
			if(in2.weg())
			{
				if(in2.cascade == 2)
					weg = true;
				if(in2.cascade == 1 && allC == null)
					allC = true;
				in.remove(i);
				i--;
			}
			else if(in2.cascade == 1)
				allC = false;
		}
		if(allC != null && allC)
			weg = true;
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