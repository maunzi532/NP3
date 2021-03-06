package interf;

import java.awt.*;

public class Textbox extends Clickbar
{
	String text;

	public Textbox(String text, int... location)
	{
		super(0, location);
		this.text = text;
	}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		gd.setColor(Color.WHITE);
		gd.fillRect(xn - xr, yn - yr, xr * 2, yr);
		gd.setColor(Color.BLACK);
		gd.drawRect(xn - xr, yn - yr, xr * 2 - 1, yr - 1);
		fitLine(gd, text, xn, yn - yr * 2 / 5, xr - yr / 5, yr * 2 / 5, false);
	}

	@Override
	public boolean registerClick2(int cx, int cy, int xn, int yn, int xr, int yr)
	{
		return clickbar;
	}

	@Override
	public void onClick(int n)
	{
		weg = 1;
	}
}