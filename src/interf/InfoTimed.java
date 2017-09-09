package interf;

import java.awt.*;

public class InfoTimed extends UITeil
{
	String text;
	boolean center;
	int ticks;
	public int ticksA;

	public InfoTimed(String text, boolean center, int ticks, int... location)
	{
		super(0, location);
		this.text = text;
		this.center = center;
		this.ticks = ticks;
	}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		gd.setColor(new Color(255, 255, 255, 127));
		gd.fillRect(xn - xr, yn - yr, xr * 2, yr * 2);
		gd.setColor(new Color(0, 0, 0, 127));
		gd.drawRect(xn - xr, yn - yr, xr * 2 - 1, yr * 2 - 1);
		fitLine(gd, text, xn, yn, xr - yr / 5, yr * 4 / 5, center);
	}

	@Override
	public int weg()
	{
		ticksA++;
		if(ticksA >= ticks)
			weg = 1;
		return weg;
	}
}