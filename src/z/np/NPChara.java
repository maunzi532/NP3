package z.np;

import karte.*;
import pfadfind.*;
import z.np.haus.*;

public class NPChara extends KChara implements Einheit
{
	NTyp typ;

	Haus inHaus;

	long energie;
	long energieLimit;

	long leben;
	long lebenLimit;

	/*long[] align;

	ArrayList<long[]> effects;*/

	public NPChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
	}

	public long leben()
	{
		return leben;
	}

	public long lebenLimit()
	{
		return lebenLimit;
	}

	public long energie()
	{
		return energie;
	}

	public long energieLimit()
	{
		return energieLimit;
	}

	public long energieMaxTransfer()
	{
		return energieLimit / 10;
	}
}