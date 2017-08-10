package z.np.boden;

public class Schichten
{
	int maxdepth, maxheight;
	public NPKarte[] karten;

	public Schichten(int maxdepth, int maxheight, int xw, int yw)
	{
		this.maxdepth = maxdepth;
		this.maxheight = maxheight;
		karten = new NPKarte[maxdepth + 1 + maxheight];
		for(int i = 0; i < karten.length; i++)
			karten[i] = new NPKarte(i - maxdepth, xw, yw);
	}

	public NPKarte karte(int level)
	{
		return karten[level + maxdepth];
	}
}