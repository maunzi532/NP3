package karte;

import interf.*;
import java.awt.*;

public class KVis extends UITeil
{
	int xg, yg;

	public KVis(int xg, int yg)
	{
		super();
		this.xg = xg;
		this.yg = yg;
	}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		gd.setColor(Color.GREEN);
		gd.fillRect(xn - xr + 2, yn - yr + 2, xr * 2 * xg - 2, yr * 2 * yg - 2);
	}
}