package idk;

import java.awt.*;
import java.util.*;
import karte.*;

public abstract class Mark
{
	public Marker fokus;
	public Marker ziel;
	public Marker hover;

	public ArrayList<KObjekt> hoverliste = new ArrayList<>();
	public int hoverausw = -1;

	public Mark()
	{
		fokus = new Marker(Color.BLUE);
		ziel = new Marker(Color.MAGENTA);
		hover = new Marker(Color.YELLOW);
	}

	public void mdk(int nx, int ny, Karte aktuell)
	{
		if(aktuell != null)
		{
			hover(nx, ny, aktuell);
			if(TA.take[201] == 2)
			{
				if(getHoverAusw() != null)
					fokus.marked = getHoverAusw();
				else
				{
					fokus.auf = hover.auf;
					fokus.marked = null;
					fokus.x = hover.x;
					fokus.y = hover.y;
				}
				fokus.existent = true;
			}
			if(TA.take[203] == 2)
			{
				if(getHoverAusw() != null)
					ziel.marked = getHoverAusw();
				else
				{
					ziel.auf = hover.auf;
					ziel.marked = null;
					ziel.x = hover.x;
					ziel.y = hover.y;
				}
				ziel.existent = true;
			}
		}
		else
			hover.existent = false;
	}

	public void hover(int nx, int ny, Karte aktuell)
	{
		if(aktuell != hover.auf || nx != hover.x || ny != hover.y)
		{
			hover.existent = true;
			hover.auf = aktuell;
			hoverliste = aktuell.hier(nx, ny);
			hover.x = nx;
			hover.y = ny;
			hoverausw = hoverliste.size() - 1;
			hover.marked = getHoverAusw();
		}
		else
		{
			hoverliste.removeIf(k -> !k.existent);
			if(hoverausw >= hoverliste.size())
				hoverausw = hoverliste.size() - 1;
		}
		if(TA.take[66] == 2)
		{
			hoverausw--;
			if(hoverausw < -1)
				hoverausw = hoverliste.size() - 1;
		}
		if(hoverausw >= 0)
			hover.marked = hoverliste.get(hoverausw);
		else
			hover.marked = null;
	}

	public KObjekt getHoverAusw()
	{
		if(hoverausw < 0)
			return null;
		return hoverliste.get(hoverausw);
	}

	public KOrt mitFokus(Marker m)
	{
		if(fokus.existent && fokus.marked != null)
			return new KOrt(m.x, m.y, fokus.marked.xg, fokus.marked.yg);
		return new KOrt(m.x, m.y, 1, 1);
	}

	public abstract void verarbeite();

	public void zeichne(Graphics2D gd, Karte ak, PlD d)
	{
		zeichne(gd, hover, ak, d);
		zeichne(gd, fokus, ak, d);
		zeichne(gd, ziel, ak, d);
	}

	public void zeichne(Graphics2D gd, Marker m, Karte ak, PlD d)
	{
		if(m.existent && m.getAuf() == ak)
		{
			int mx, my;
			int msx = 0;
			int msy = 0;
			int mxg = 1;
			int myg = 1;
			if(m.marked != null)
			{
				mx = m.marked.x;
				my = m.marked.y;
				msx = m.marked.sx;
				msy = m.marked.sy;
				mxg = m.marked.xg;
				myg = m.marked.yg;
			}
			else
			{
				mx = m.x;
				my = m.y;
				if(fokus.existent && fokus.marked != null)
				{
					mxg = fokus.marked.xg;
					myg = fokus.marked.yg;
				}
			}
			zeichne(gd, d.xort(mx, msx), d.yort(my, msy), d.xdfw, d.ydfw, mxg, myg, m.farbe);
		}
	}

	public void zeichne(Graphics2D gd, int xz, int yz, int w, int h, int xg, int yg, Color farbe)
	{
		gd.setColor(farbe);
		gd.fillRect(xz - w / 2, yz - h / 2, w * xg, 2);
		gd.fillRect(xz - w / 2, yz - h / 2 + 2, 2, h * yg - 4);
		gd.fillRect(xz - w / 2, yz - h / 2 + h * yg - 2, w * xg, 2);
		gd.fillRect(xz - w / 2 + w * xg - 2, yz - h / 2 + 2, 2, h * yg - 4);
	}
}