package z.np.boden;

import z.np.*;

public class Schichten
{
	int maxdepth, maxheight;
	public NPKarte[] karten;
	public Portal portal;

	public Schichten(int maxdepth, int maxheight, int xw, int yw)
	{
		this.maxdepth = maxdepth;
		this.maxheight = maxheight;
		karten = new NPKarte[maxdepth + 1 + maxheight];
		portal = new Portal(xw / 2 - 1, yw / 2 - 1, 3, 3);
		for(int i = 0; i < karten.length; i++)
			karten[i] = new NPKarte(i - maxdepth, xw, yw, this);
		portal.auf = karte(0);
	}

	public NPKarte karte(int level)
	{
		if(level < -maxdepth)
			return null;
		if(level > maxheight)
			return null;
		return karten[level + maxdepth];
	}
}