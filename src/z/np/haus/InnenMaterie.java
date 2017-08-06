package z.np.haus;

import java.util.*;
import z.np.*;
import z.np.boden.*;

public class InnenMaterie extends InnenTeil implements Transferer
{
	HashMap<MaterieTyp, Long> inhalt;
	long menge;
	long kapazitaet;

	public InnenMaterie(Haus von, int anteil)
	{
		super(InnenTeilTyp.MATERIELAGER, von, anteil);
		kapazitaet = anteil * anteil / 10;
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

	public static InnenMaterie von(Haus h)
	{
		InnenTeil t = h.innen.get(InnenTeilTyp.MATERIELAGER);
		return t == null ? null : (InnenMaterie) t;
	}
}