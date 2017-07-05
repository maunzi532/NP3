package idk;

import interf.*;
import java.awt.*;
import java.util.*;
import karte.*;
import pfadfind.*;
import z.sweeper.*;

public class XKarte
{
	public static ArrayList<Karte> karten = new ArrayList<>();
	public static Karte aktuell;
	public static Mark mark;
	public static ArrayList<UIAnschluss> gui = new ArrayList<>();
	public static KObjekt kamLock;
	public static int kamx, kamy;
	public static final int fwx = 20;
	public static final int fwy = 20;
	public static int sichtx = 8;
	public static int sichty = 6;
	public static PlD d;

	public static void init(Graphics2D gd)
	{
		aktuell = new SweeperKarte(40, 40);
		karten.add(aktuell);
		kamx = aktuell.xw * fwx / 2;
		kamy = aktuell.yw * fwy / 2;
		mark = new SweeperMark();
		aktuell.objekte.add(new KChara(0, 0, 2, 2, true, true, aktuell));
		aktuell.objekte.add(new KChara(2, 2, 1, 1, true, true, aktuell));
		//kamLock = aktuell.objekte.get(1);
		gui.add(new UIAnschluss(1, 1, new MultiOption(new String[]{"W1", "W2"}, 0, 2, 0, 1, 0, 1, 1, 5, 1, 20))
		{
			public void code(int re)
			{
				System.out.println(re);
			}
		});
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
		aktuell.tick();
		aufzeichnen(gd);
		kamera();
	}

	public static void maus(int xp, int yp)
	{
		Clickbar cl = null;
		for(int i = 0; i < gui.size() && cl == null; i++)
			cl = gui.get(i).registerClick(xp, yp, d);
		if(cl != null)
			cl.onFokus();
		gui.removeIf(UIAnschluss::weg);
		int xcal = d.xcal(xp);
		int ycal = d.ycal(yp);
		if(cl == null && xcal >= 0 && ycal >= 0 && xcal < aktuell.xw * d.fwx && ycal < aktuell.yw * d.fwy)
			mark.mdk(xcal / d.fwx, ycal / d.fwy, aktuell);
		else
			mark.mdk(-1, -1, null);
	}

	public static void aufzeichnen(Graphics2D gd)
	{
		for(int xi = -d.sichtx - 1; xi <= d.sichtx + 1; xi++)
			for(int yi = -d.sichty - 1; yi <= d.sichty + 1; yi++)
				aktuell.fliese(d.xkm + xi, d.ykm + yi).zeichne(gd,
						d.xks2 + d.xdfw * xi, d.yks2 + d.ydfw * yi, d.xdfw, d.ydfw);
		for(KObjekt c : aktuell.objekte)
			if(c.existent && c.x + 1 >= d.xkm - d.sichtx && c.y + 1 >= d.ykm - d.sichty
					&& c.x + c.xg - 1 <= d.xkm + d.sichtx && c.y + c.yg - 1 <= d.ykm + d.sichty)
				c.zeichne(gd, d.xort(c.x, c.sx), d.yort(c.y, c.sy), d.xdfw, d.ydfw);
		mark.zeichne(gd, aktuell, d);
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