package idk;

import interf.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import karte.*;

public class XKarte
{
	public static List<Karte> karten = new ArrayList<>();
	public static Karte aktuell;
	public static Mark mark;
	public static Xtb xtb;
	public static ArrayList<UIAnschluss> gui = new ArrayList<>();
	public static Texthalter th;
	public static KObjekt kamLock;
	public static int kamx, kamy;
	public static int sichtx, sichty;
	public static final int fwx = 20;
	public static final int fwy = 20;
	public static PlD d;

	public static void init1()
	{
		th = new Texthalter(0, 1, 0, 1, 1, 1, 1, 1);
		gui.add(new UIAnschluss(0, 1, th));
	}

	public static void init2()
	{
		if(aktuell != null)
		{
			kamx = aktuell.xw * fwx / 2;
			kamy = aktuell.yw * fwy / 2;
		}
		sichtx = 8;
		sichty = 6;
	}

	public static void tick(Graphics2D gd, int xw, int yw, int xp, int yp)
	{
		d = new PlD();
		d.xw = xw;
		d.yw = yw;
		d.fwx = fwx;
		d.fwy = fwy;
		d.sichtx = sichtx;
		d.sichty = sichty;
		d.kamx = kamx;
		d.kamy = kamy;
		d.calc();
		maus(xp, yp);
		mark.verarbeite();
		if(xtb.alleKarten)
			for(Karte k : karten)
				k.tick();
		else if(aktuell != null)
			aktuell.tick();
		aufzeichnen(gd);
		if(aktuell != null)
			kamera();
	}

	public static void maus(int xp, int yp)
	{
		ArrayList<Clickbar> cl = new ArrayList<>();
		boolean taken = false;
		for(int i = 0; i < gui.size() && !taken; i++)
			if(gui.get(i).registerClick(cl, xp, yp, d))
				taken = true;
		for(Clickbar cl1 : cl)
			cl1.onFokus();
		gui.removeIf(UIAnschluss::weg);
		int xcal = d.xcal(xp);
		int ycal = d.ycal(yp);
		if(aktuell != null && !taken && xcal >= 0 && ycal >= 0 && xcal < aktuell.xw * d.fwx && ycal < aktuell.yw * d.fwy)
			mark.mdk(xcal / d.fwx, ycal / d.fwy, aktuell);
		else
			mark.mdk(-1, -1, null);
	}

	public static void aufzeichnen(Graphics2D gd)
	{
		if(aktuell != null)
		{
			for(int xi = -d.sichtx - 1; xi <= d.sichtx + 1; xi++)
				for(int yi = -d.sichty - 1; yi <= d.sichty + 1; yi++)
					aktuell.fliese(d.xkm + xi, d.ykm + yi).zeichne(gd,
							d.xks2 + d.xdfw * xi, d.yks2 + d.ydfw * yi, d.xdfw, d.ydfw);
			for(int i = 0; i < aktuell.objekte.size(); i++)
			{
				KObjekt c = (KObjekt) aktuell.objekte.get(i);
				if(c.existent && c.x + 1 >= d.xkm - d.sichtx && c.y + 1 >= d.ykm - d.sichty
						&& c.x + c.xg - 1 <= d.xkm + d.sichtx && c.y + c.yg - 1 <= d.ykm + d.sichty)
					c.zeichne(gd, d.xort(c.x, c.sx), d.yort(c.y, c.sy), d.xdfw, d.ydfw);
			}
			mark.zeichne(gd, aktuell, d);
		}
		else
		{
			gd.setColor(Color.BLACK);
			gd.fillRect(0, 0, d.xw, d.yw);
		}
		for(int i = gui.size() - 1; i >= 0; i--)
			gui.get(i).aufzeichnen(gd, d);
	}

	public static void kamera()
	{
		if(kamLock != null)
		{
			kamx = kamLock.x * fwx + kamLock.sx;
			kamy = kamLock.y * fwy + kamLock.sy;
		}
		if(TA.take[37] > 0)
			kamx--;
		if(TA.take[38] > 0)
			kamy--;
		if(TA.take[39] > 0)
			kamx++;
		if(TA.take[40] > 0)
			kamy++;
	}
}