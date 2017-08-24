package pfadfind;

import auftrag.*;
import idk.*;
import java.util.*;
import karte.*;

public class KChara<T extends Karte> extends KObjekt<T>
{
	Koordinate cs;
	Koordinate ct;
	boolean z;
	int spf = 1;
	int aks = -1;
	boolean nfeld;
	public boolean abbruch;

	public Auftrag auftrag = null;
	public ArrayList<Koordinate> pfad = new ArrayList<>();

	public KChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, T auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
		anzielbar = true;
	}

	@Override
	public void tick()
	{
		if(auftrag != null)
		{
			Boolean re = auftrag.los(aks >= 0, abbruch);
			if(re != null)
			{
				if(re && auftrag.danach != null)
					auftrag.danach.an(this);
				else
					auftrag = null;
			}
		}
		if(aks >= 0)
			tb();
		else
			abbruch = false;
	}

	public void pfad(Koordinate dest, int abstand)
	{
		ArrayList<Koordinate> dest1 = new ArrayList<>();
		if(abstand == 0)
			dest1.add(dest);
		else
			for(int ix = 0; ix < xg; ix++)
				for(int iy = 0; iy < yg; iy++)
					dest1.add(new Koordinate(dest.x - ix, dest.y - iy));
		pfad = new Pfadfind(x, y, abstand, 60, auf.bewK(xg, yg, this), dest1).los();
	}

	public void pfad(KObjekt dest, int abstand)
	{
		ArrayList<Koordinate> dest1 = new ArrayList<>();
		for(int ix = 1 - xg; ix < dest.xg; ix++)
			for(int iy = 1 - yg; iy < dest.yg; iy++)
				dest1.add(new Koordinate(dest.x + ix, dest.y + iy));
		pfad = new Pfadfind(x, y, abstand, 60, auf.bewK(xg, yg, this), dest1).los();
	}

	public void declaregtf(Koordinate ct, int hspf)
	{
		this.ct = ct;
		int dx = ct.x - x;
		int dy = ct.y - y;
		cs = new Koordinate(x, y);
		z = false;
		nfeld = false;
		if(dx * dx + dy * dy == 2)
			spf = hspf * 3;
		else
			spf = hspf * 2;
		aks = 0;
	}

	public void tb()
	{
		if(cs.equals(ct))
		{
			aks = -1;
			return;
		}
		if(abbruch && !nfeld)
			z = true;
		if(!z)
		{
			if(!new Koordinate(x, y).equals(ct) && !auf.begehbar(new KOrt(ct.x, ct.y, xg, yg), this))
				z = true;
			else if(++aks > spf / 2)
			{
				nfeld = true;
				x = ct.x;
				y = ct.y;
			}
		}
		if(z && aks > 0)
			aks--;
		sx = aks * (ct.x - cs.x) * XKarte.fwx / spf + (x == cs.x ? 0 : XKarte.fwx) * (ct.x >= cs.x ? -1 : 1);
		sy = aks * (ct.y - cs.y) * XKarte.fwy / spf + (y == cs.y ? 0 : XKarte.fwy) * (ct.y >= cs.y ? -1 : 1);
		if(aks <= 0 || aks >= spf)
			aks = -1;
	}
}