package z.np;

import java.util.*;
import karte.*;
import pfadfind.*;
import z.np.boden.*;
import z.np.haus.*;

public class NPChara extends KChara implements Transferer
{
	NTyp typ;

	Haus inHaus;

	long leben;
	long lebenLimit;

	long energie;
	long maxenergie;

	Materie therm;
	long maxmaterie;

	ArrayList<Item> items = new ArrayList<>();
	int itemlimit;

	public NPChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
	}

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		if(energie < menge)
			return false;
		if(real)
			energie -= menge;
		return true;
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		long abzug = Math.min(menge, maxenergie - energie);
		if(real)
			energie += abzug;
		return menge - abzug;
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		if(therm == null || therm.typ != mat.typ || therm.menge < mat.menge)
			return false;
		if(real)
			therm = new Materie(mat.typ, therm.menge - mat.menge);
		return true;
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		if(therm == null)
		{
			if(real)
				therm = new Materie(mat.typ, Math.min(mat.menge, maxmaterie));
			return new Materie(mat.typ, Math.max(0, mat.menge - maxmaterie));
		}
		if(therm.typ == mat.typ)
		{
			long abzug = Math.min(mat.menge, maxmaterie - therm.menge);
			if(real)
				therm = new Materie(mat.typ, therm.menge + abzug);
			return new Materie(mat.typ, mat.menge - abzug);
		}
		return mat;
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		if(items.size() >= itemlimit)
			return false;
		if(real)
			items.add(item);
		return true;
	}
}