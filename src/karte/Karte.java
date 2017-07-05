package karte;

import idk.*;
import java.util.*;

public abstract class Karte
{
	public int xw;
	public int yw;
	public Feld[][] fliesen;
	public ArrayList<KObjekt> objekte = new ArrayList<>();

	public Karte(int xw, int yw)
	{
		this.xw = xw;
		this.yw = yw;
		fliesen = new Feld[xw][yw];
	}

	public Feld fliese(int x, int y)
	{
		if(x < 0 || y < 0 || x >= xw || y >= yw)
			return Feld.LEERE;
		return fliesen[x][y];
	}

	public boolean begehbar(KOrt z, KObjekt wer)
	{
		for(int xi = 0; xi < z.xg; xi++)
			for(int yi = 0; yi < z.yg; yi++)
				if(!fliese(z.x + xi, z.y + yi).begehbar())
					return false;
		for(KObjekt k : objekte)
			if(k.existent && k.solide && k != wer && overlap(z, k))
				return false;
		return true;
	}

	public ArrayList<KObjekt> hier(int x, int y)
	{
		ArrayList<KObjekt> re = new ArrayList<>();
		for(KObjekt k : objekte)
			if(k.existent && k.anzielbar && overlap(new KOrt(x, y, 1, 1), k))
				re.add(k);
		return re;
	}

	public boolean overlap(KOrt k1, KOrt k2)
	{
		return k1.x + k1.xg - 1 >= k2.x && k1.x <= k2.x + k2.xg - 1 && k1.y + k1.yg - 1 >= k2.y && k1.y <= k2.y + k2.yg - 1;
	}

	public void tick()
	{
		for(KObjekt k : objekte)
			k.tick();
	}

	public boolean[][] bewK(int xg, int yg, KObjekt ausnahme)
	{
		boolean[][] re = new boolean[xw][yw];
		for(int ix = 0; ix < xw; ix++)
			for(int iy = 0; iy < yw; iy++)
				re[ix][iy] = fliesen[ix][iy].begehbar();
		for(KObjekt k : objekte)
			if(k.existent && k.solide && k != ausnahme)
				for(int igx = 0; igx < k.xg; igx++)
					for(int igy = 0; igy < k.yg; igy++)
						re[k.x + igx][k.y + igy] = false;
		if(xg == 1 && yg == 1)
			return re;
		boolean[][] re2 = new boolean[xw][yw];
		for(int ix = 0; ix < xw + 1 - xg; ix++)
			for(int iy = 0; iy < yw + 1 - yg; iy++)
			{
				re2[ix][iy] = true;
				for(int igx = 0; igx < xg; igx++)
					for(int igy = 0; igy < yg; igy++)
						if(!re[ix + igx][iy + igy])
							re2[ix][iy] = false;
			}
		return re2;
	}
}