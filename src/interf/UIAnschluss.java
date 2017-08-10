package interf;

import idk.*;
import java.awt.*;
import java.util.*;
import karte.*;

public class UIAnschluss
{
	UITeil inhalt;
	Karte auf;
	KOrt ziel;
	int ex, ey;

	public UIAnschluss(int ex, int ey, UITeil inhalt)
	{
		this.inhalt = inhalt;
		this.ex = ex;
		this.ey = ey;
	}

	public UIAnschluss(Karte auf, KOrt ziel, int ex, int ey, UITeil inhalt)
	{
		this.inhalt = inhalt;
		this.auf = auf;
		this.ziel = ziel;
		this.ex = ex;
		this.ey = ey;
	}

	private int e(int w, int e)
	{
		return e < 0 ? 0 : e > 0 ? w : w / 2;
	}

	private int e(int w1, int w2, int e)
	{
		return e < 0 ? w1 : e > 0 ? w2 : w2 / 2 + w1 / 2;
	}

	public int[] bloc(PlD d)
	{
		if(auf == null)
			return new int[]{e(d.xw, ex), e(d.yw, ey), d.xw / 2, d.yw / 2};
		int sx = 0;
		int sy = 0;
		if(ziel instanceof KObjekt)
		{
			sx = ((KObjekt) ziel).sx;
			sy = ((KObjekt) ziel).sy;
		}
		return new int[]{e(d.xort2(ziel.x, 0, sx), d.xort2(ziel.x, ziel.xg, sx), ex),
				e(d.yort2(ziel.y, 0, sy), d.yort2(ziel.y, ziel.yg, sy), ey), d.xdfw / 2, d.ydfw / 2};
	}

	public void aufzeichnen(Graphics2D gd, PlD d)
	{
		if(auf == null || auf == XKarte.aktuell)
			inhalt.aufzeichnen(gd, bloc(d));
	}

	public boolean registerClick(ArrayList<Clickbar> alle, int cx, int cy, PlD d)
	{
		return (auf == null || auf == XKarte.aktuell) && inhalt.registerClick(alle, cx, cy, bloc(d));
	}

	public boolean weg()
	{
		if(auf != null && auf != XKarte.aktuell)
			return false;
		int re = inhalt.weg();
		if(re != 0)
			code(re);
		return re > 0;
	}

	public void code(int re){}
}