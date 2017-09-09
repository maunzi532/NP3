package z.np.haus.innen;

import java.util.*;
import java.util.stream.*;
import z.np.boden.*;
import z.np.haus.*;
import z.np.transfer.*;

public class InnenMaterie extends InnenTeil implements MaterieTransferer
{
	private HashMap<MaterieTyp, Long> inhalt = new HashMap<>();
	private long menge;
	private long kapazitaet;

	public InnenMaterie(Haus von, int anteil)
	{
		super(InnenTeilTyp.MATERIELAGER, von);
		setAnteil(anteil);
	}

	@Override
	public void setAnteil(long anteil1)
	{
		anteil = anteil1;
		kapazitaet = anteil * anteil / 10;
		long zuviel = menge - kapazitaet;
		if(zuviel > 0)
			for(MaterieTyp tr : inhalt.keySet().stream().sorted(Comparator.comparingInt(e -> e.num)).collect(Collectors.toList()))
				if(inhalt.get(tr) >= zuviel)
				{
					inhalt.put(tr, inhalt.get(tr) - zuviel);
					break;
				}
				else
					zuviel -= inhalt.remove(tr);
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		if(inhalt.containsKey(mat.typ) && inhalt.get(mat.typ) >= mat.menge)
		{
			long danach = inhalt.get(mat.typ) - mat.menge;
			if(real)
			{
				if(danach <= 0)
					inhalt.remove(mat.typ);
				else
					inhalt.put(mat.typ, danach);
				menge -= mat.menge;
			}
			return true;
		}
		return false;
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		if(mat.menge <= 0 || menge >= kapazitaet)
			return mat;
		long abzug = mat.menge;
		if(menge + abzug > kapazitaet)
			abzug = kapazitaet - menge;
		if(real)
		{
			if(inhalt.containsKey(mat.typ))
				inhalt.put(mat.typ, inhalt.get(mat.typ) + abzug);
			else
				inhalt.put(mat.typ, abzug);
			menge += abzug;
		}
		return new Materie(mat.typ, mat.menge - abzug);
	}

	@Override
	public HashMap<MaterieTyp, Long> zeigeMaterie()
	{
		return inhalt;
	}

	@Override
	public long maxMaterie()
	{
		return kapazitaet;
	}

	public static InnenMaterie von(Haus h)
	{
		InnenTeil t = h.getInnenTeil(InnenTeilTyp.MATERIELAGER);
		return t == null ? null : (InnenMaterie) t;
	}
}