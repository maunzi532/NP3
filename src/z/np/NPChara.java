package z.np;

import auftrag.*;
import idk.*;
import interf.*;
import java.util.*;
import karte.*;
import mark.*;
import pfadfind.*;
import z.np.boden.*;
import z.np.haus.*;
import z.np.transfer.*;

public class NPChara extends KChara<NPKarte> implements EnergieTransferer, MaterieTransferer, ItemTransferer
{
	NTyp typ;

	public Haus inHaus;

	long leben;
	long lebenLimit;

	long energie;
	long maxenergie;

	Materie therm;
	long maxmaterie;

	ArrayList<Item> items = new ArrayList<>();
	int itemlimit;

	public NPChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, NPKarte auf)
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
	public Long zeigeEnergie()
	{
		return energie;
	}

	@Override
	public long maxEnergie()
	{
		return maxenergie;
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
	public HashMap<MaterieTyp, Long> zeigeMaterie()
	{
		HashMap<MaterieTyp, Long> mat = new HashMap<>();
		mat.put(therm.typ, therm.menge);
		return mat;
	}

	@Override
	public long maxMaterie()
	{
		return maxmaterie;
	}

	@Override
	public boolean requestItem(Item item, boolean real)
	{
		return real ? items.remove(item) : items.contains(item);
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

	@Override
	public List<Item> zeigeItems()
	{
		return items;
	}

	@Override
	public long maxItems()
	{
		return itemlimit;
	}

	//TODO
	@Override
	public ArrayList<int[]> tasten1()
	{
		ArrayList<int[]> re = new ArrayList<>();
		re.add(new int[]{71});
		return re;
	}

	@Override
	public ArrayList<Exec> optionen1()
	{
		ArrayList<Exec> re = new ArrayList<>();
		if(auf.fliese(x, y).fluidG == null)
			re.add(new Exec("Graben", c ->
					((KChara) c).extraAuftrag(new Graben(((KObjekt<NPKarte>) c).auf.fliese(c.ort().x, c.ort().y))), this));
		else
			re.add(null);
		return re;
	}

	@Override
	public ArrayList<int[]> tasten2(Markierbar m1)
	{
		ArrayList<int[]> re = new ArrayList<>();
		re.add(new int[]{72, 201});
		re.add(new int[]{71});
		return re;
	}

	@Override
	public ArrayList<Exec> optionen2(Markierbar m1)
	{
		ArrayList<Exec> re = new ArrayList<>();
		if(m1 instanceof Haus)
		{
			re.add(new Exec("Betreten", (c, h) ->
			{
				KChara c1 = (KChara) c;
				Haus h1 = (Haus) h;
				c1.extraAuftrag(new FolgeZiel(h1, 2, new BetreteZiel(h1)));
			}, this, m1));
		}
		else if(m1 instanceof Bodenteil && auf.begehbar(new KOrt(m1.ort(), this), this))
				re.add(new Exec("Hingehen", (c, b) ->
						((KChara) c).extraAuftrag(new GeheZuZiel(b.ort().koord(), 0)), this, m1));
		else
			re.add(null);
		if(m1 instanceof Bodenteil && auf.begehbar(new KOrt(m1.ort(), this), this) && ((Bodenteil) m1).fluidG == null)
			re.add(new Exec("Graben", (c, b) ->
					((KChara) c).extraAuftrag(new GeheZuZiel(b.ort().koord(), 0, new Graben((Bodenteil) b))), this, m1));
		else
			re.add(null);
		return re;
	}
}