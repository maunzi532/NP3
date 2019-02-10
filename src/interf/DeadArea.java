package interf;

import java.awt.*;

public class DeadArea extends Clickbar
{
	Color bg;
	Color ra;

	public DeadArea(Color bg, Color ra)
	{
		super(2, 0, 1, 0, 1, 1, 1, 1, 1);
		this.bg = bg;
		this.ra = ra;
	}

	@Override
	public void onClick(int n){}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		gd.setColor(bg);
		gd.fillRect(xn - xr, yn - yr, xr * 2, yr * 2);
		gd.setColor(ra);
		gd.drawRect(xn - xr, yn - yr, xr * 2, yr * 2);
	}
}