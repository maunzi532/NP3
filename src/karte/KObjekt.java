package karte;

import idk.*;
import interf.*;
import java.awt.*;

public class KObjekt<T extends Karte> extends KOrt implements Markierbar
{
	public int sx, sy;
	public boolean existent;
	public boolean solide;
	public boolean anzielbar;
	public T auf;
	public UITeil visual;

	public KObjekt(int x, int y, int xg, int yg, boolean existent, boolean solide, T auf)
	{
		super(x, y, xg, yg);
		this.existent = existent;
		this.solide = solide;
		this.auf = auf;
		visual = new KVis(xg, yg);
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

	@Override
	public KOrt ort()
	{
		return this;
	}

	@Override
	public Karte auf(Karte w)
	{
		return auf;
	}

	@Override
	public boolean weg()
	{
		return !existent;
	}
}