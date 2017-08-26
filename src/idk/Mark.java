package idk;

import java.awt.*;
import java.util.*;
import karte.*;

public abstract class Mark
{
	public Marker fokus;
	public Marker ziel;
	public Marker hover;

	public ArrayList<Markierbar> hoverliste = new ArrayList<>();
	public int hoverausw = -1;

	public Mark()
	{
		fokus = new Marker(Color.BLUE);
		ziel = new Marker(Color.MAGENTA);
		hover = new Marker(Color.YELLOW);
	}

	public void mdk(int nx, int ny, Karte aktuell)
	{
		if(fokus.marked != null && fokus.marked.weg())
			fokus.marked = null;
		if(ziel.marked != null && ziel.marked.weg())
			ziel.marked = null;
		if(aktuell != null)
		{
			hover(nx, ny, aktuell);
			mdk1();
		}
		else
			hover.marked = null;
	}

	public abstract void mdk1();

	public void hover(int nx, int ny, Karte aktuell)
	{
		if(getHoverAusw() == null || aktuell != getHoverAusw().auf(aktuell) || nx != getHoverAusw().ort().x || ny != getHoverAusw().ort().y)
		{
			hoverliste = aktuell.hier(nx, ny);
			hoverausw = hoverliste.size() - 1;
		}
		else
		{
			hoverliste.removeIf(Markierbar::weg);
			if(hoverausw >= hoverliste.size())
				hoverausw = hoverliste.size() - 1;
		}
		if(TA.take[66] == 2)
		{
			hoverausw--;
			if(hoverausw < 0)
				hoverausw = hoverliste.size() - 1;
		}
		hover.marked = getHoverAusw();
	}

	public Markierbar getHoverAusw()
	{
		if(hoverausw < 0)
			return null;
		return hoverliste.get(hoverausw);
	}

	public KOrt mitFokus(Marker m)
	{
		if(fokus.marked != null && fso())
			return new KOrt(m.marked.ort().x, m.marked.ort().y, fokus.marked.ort().xg, fokus.marked.ort().yg);
		return m.marked.ort();
	}

	public abstract void verarbeite();

	public boolean fso()
	{
		return true;
	}

	public void zeichne(Graphics2D gd, Karte ak, PlD d)
	{
		zeichne(gd, hover, ak, d);
		zeichne(gd, ziel, ak, d);
		zeichne(gd, fokus, ak, d);
	}

	public void zeichne(Graphics2D gd, Marker m, Karte ak, PlD d)
	{
		if(m.marked != null && m.marked.auf(ak) == ak)
		{
			KOrt mk = m.marked.ort();
			int msx = 0;
			int msy = 0;
			int mxg = 1;
			int myg = 1;
			if(m.marked instanceof KObjekt)
			{
				KObjekt m1 = (KObjekt) m.marked;
				msx = m1.sx;
				msy = m1.sy;
				mxg = m1.xg;
				myg = m1.yg;
			}
			else if(fso() && fokus.marked instanceof  KObjekt)
			{
				KObjekt f1 = (KObjekt) fokus.marked;
				mxg = f1.xg;
				myg = f1.yg;
			}
			zeichne(gd, d.xort(mk.x, msx), d.yort(mk.y, msy), d.xdfw, d.ydfw, mxg, myg, m.farbe);
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