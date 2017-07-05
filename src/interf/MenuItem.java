package interf;

import java.awt.*;

public class MenuItem extends Clickbar
{
	String text;
	boolean center;
	boolean focused;

	public MenuItem(String text, boolean center, int cascade, int... location)
	{
		super(cascade, location);
		this.text = text;
		this.center = center;
	}

	@Override
	public void onClick(boolean r)
	{
		if(cascade > 0)
			weg = 1;
	}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		gd.setColor(Color.WHITE);
		gd.fillRect(xn - xr, yn - yr, xr * 2, yr * 2);
		gd.setColor(focused ? Color.BLUE : Color.BLACK);
		gd.drawRect(xn - xr, yn - yr, xr * 2 - 1, yr * 2 - 1);
		fitLine(gd, text, xn, yn, xr - yr / 5, yr * 4 / 5, center);
	}
}