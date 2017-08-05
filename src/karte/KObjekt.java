package karte;

import idk.*;
import interf.*;
import java.awt.*;

public class KObjekt extends KOrt
{
	public int sx, sy;
	public boolean existent;
	public boolean solide;
	public boolean anzielbar;
	public Karte auf;
	public UITeil visual;

	public KObjekt(int x, int y, int xg, int yg, boolean existent, boolean solide, Karte auf)
	{
		super(x, y, xg, yg);
		this.existent = existent;
		this.solide = solide;
		this.auf = auf;
		visual = new KVis(xg, yg);
	}

	public int abstand(KObjekt k)
	{
		int xa = 0;
		if(k.x + k.xg <= x)
			xa = x - k.x - k.xg + 1;
		else if(x + xg <= k.x)
			xa = k.x - x - xg + 1;
		int ya = 0;
		if(k.y + k.yg <= y)
			ya = y - k.y - k.yg + 1;
		else if(y + yg <= k.y)
			ya = k.y - y - yg + 1;
		return xa * xa + ya * ya;
	}

	public int abstand(int xd, int yd)
	{
		int xa = 0;
		if(xd < x)
			xa = x - xd;
		else if(x + xg <= xd)
			xa = xd - x - xg + 1;
		int ya = 0;
		if(yd < y)
			ya = y - yd;
		else if(y + yg <= yd)
			ya = yd - y - yg + 1;
		return xa * xa + ya * ya;
	}

	public void tick(){}

	public void zeichne(Graphics2D gd, int x, int y, int w, int h)
	{
		if(visual != null)
			visual.aufzeichnen(gd, x, y, w / 2, h / 2);
		else
		{
			gd.setColor(Color.GREEN);
			gd.fillRect(x - w / 2 + 2, y - h / 2 + 2, w * xg - 4, h * yg - 4);
		}
	}
}