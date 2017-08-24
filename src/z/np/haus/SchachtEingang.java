package z.np.haus;

import java.util.*;
import z.np.*;
import z.np.boden.*;
import z.np.haus.innen.*;

public class SchachtEingang extends Haus
{
	public Haus connected;

	public SchachtEingang(int x, int y, NPKarte auf)
	{
		super(x, y, 1, 1, true, true, auf);
	}

	@Override
	public InnenTeil getInnenTeil(InnenTeilTyp typ)
	{
		return connected.getInnenTeil(typ);
	}

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		return connected != null && connected.requestEnergie(menge, real);
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		return connected != null ? connected.acceptEnergie(menge, real) : menge;
	}

	@Override
	public Long zeigeEnergie()
	{
		return connected != null ? connected.zeigeEnergie() : null;
	}

	@Override
	public long maxEnergie()
	{
		return connected != null ? connected.maxEnergie() : 0;
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		return connected != null && connected.requestMaterie(mat, real);
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		return connected != null ? connected.acceptMaterie(mat, real) : mat;
	}

	@Override
	public HashMap<MaterieTyp, Long> zeigeMaterie()
	{
		return connected != null ? connected.zeigeMaterie() : null;
	}

	@Override
	public long maxMaterie()
	{
		return connected != null ? connected.maxMaterie() : 0;
	}

	@Override
	public boolean requestItem(Item item, boolean real)
	{
		return connected != null && connected.requestItem(item, real);
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		return connected != null && connected.acceptItem(item, real);
	}

	@Override
	public List<Item> zeigeItems()
	{
		return connected != null ? connected.zeigeItems() : null;
	}

	@Override
	public long maxItems()
	{
		return connected != null ? connected.maxItems() : 0;
	}

	@Override
	public boolean requestChara(NPChara chara, boolean real)
	{
		return connected != null && connected.requestChara(chara, real);
	}

	@Override
	public boolean acceptChara(NPChara chara, boolean real)
	{
		return connected != null && connected.acceptChara(chara, real);
	}

	@Override
	public List<NPChara> zeigeCharas()
	{
		return connected != null ? connected.zeigeCharas() : null;
	}

	@Override
	public long maxCharas()
	{
		return connected != null ? connected.maxCharas() : 0;
	}
}