package z.np.transfer;

import interf.*;
import java.awt.*;

public class Balken extends Clickbar
{
	String name;
	Color farbe1;
	Color farbe2;
	long wert;
	long max;
	long ende;
	UITeil davor;
	InfoTimed info;

	public Balken(UITeil davor, String name, Color farbe1, Color farbe2, long wert, long max, long ende, int... location)
	{
		super(0, location);
		this.davor = davor;
		this.name = name;
		this.farbe1 = farbe1;
		this.farbe2 = farbe2;
		this.wert = wert;
		this.max = max;
		this.ende = ende;
	}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		int m2 = (int)(yr * 2 * max / ende);
		gd.setColor(farbe2);
		gd.fillRect(xn - xr, yn + yr - m2, xr * 2, m2);
		gd.setColor(Color.BLACK);
		gd.drawRect(xn - xr, yn + yr - m2, xr * 2 - 1, m2 - 1);
		int w2 = (int)(yr * 2 * wert / ende);
		gd.setColor(farbe1);
		gd.fillRect(xn - xr, yn + yr - w2, xr * 2, w2);
		gd.setColor(Color.BLACK);
		gd.drawRect(xn - xr, yn + yr - w2, xr * 2 - 1, w2 - 1);
	}

	@Override
	public void onFokus()
	{
		if(info == null || info.weg > 0)
		{
			info = new InfoTimed(name + ": " + wert, true, 2, 0, 1, 0, 1, 1, 3, 1, 10);
			davor.in.add(info);
		}
		else
			info.ticksA = 0;
		super.onFokus();
	}

	@Override
	public void onClick(int n){}
}