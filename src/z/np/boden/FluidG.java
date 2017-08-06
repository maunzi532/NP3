package z.np.boden;

import java.util.*;
import java.util.stream.*;
import z.np.*;

public class FluidG implements Transferer
{
	ArrayList<Bodenteil> teile;
	HashMap<MaterieTyp, Long> inhalt;
	public long solidMenge;
	public long fluidMenge;
	public FluidG replace;

	public FluidG()
	{
		teile = new ArrayList<>();
		inhalt = new HashMap<>();
	}

	public FluidG replace()
	{
		if(replace == null)
			return this;
		return replace.replace();
	}

	public long kapazitaet()
	{
		return teile.size() * 16;
	}

	public long menge()
	{
		return solidMenge + fluidMenge;
	}

	public int tiefe()
	{
		return (int) (fluidMenge / teile.size());
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
				if(mat.typ.fluid)
					fluidMenge -= mat.menge;
				else
					solidMenge -= mat.menge;
			}
			return true;
		}
		return false;
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		if(mat.menge <= 0 || menge() >= kapazitaet())
			return mat;
		long abzug = mat.menge;
		if(menge() + abzug > kapazitaet())
			abzug = kapazitaet() - menge();
		if(real)
		{
			if(inhalt.containsKey(mat.typ))
				inhalt.put(mat.typ, inhalt.get(mat.typ) + abzug);
			else
				inhalt.put(mat.typ, abzug);
			if(mat.typ.fluid)
				fluidMenge += abzug;
			else
				solidMenge += abzug;
		}
		return new Materie(mat.typ, mat.menge - abzug);
	}

	public List<MaterieTyp> sortiert()
	{
		return inhalt.keySet().stream().sorted().collect(Collectors.toList());
	}

	public FluidG verbinde(FluidG a)
	{
		FluidG neu = new FluidG();
		replace = neu;
		a.replace = neu;
		neu.teile.addAll(teile);
		neu.teile.addAll(a.teile);
		neu.inhalt.putAll(inhalt);
		a.inhalt.forEach((mat, m) -> neu.inhalt.merge(mat, m, Long::sum));
		return neu;
	}
}