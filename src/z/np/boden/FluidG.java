package z.np.boden;

import java.util.*;
import java.util.stream.*;

public class FluidG
{
	ArrayList<Bodenteil> teile;
	HashMap<Materie, Integer> inhalt;
	public int solidMenge;
	public int fluidMenge;
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

	public int kapazitaet()
	{
		return teile.size() * 16;
	}

	public int menge()
	{
		return solidMenge + fluidMenge;
	}

	public int tiefe()
	{
		return fluidMenge / teile.size();
	}

	public void rein(Fluid rein)
	{
		if(rein.menge <= 0 || menge() >= kapazitaet())
			return;
		int abzug = rein.menge;
		if(menge() + abzug > kapazitaet())
			abzug = kapazitaet() - menge();
		rein.menge -= abzug;
		if(inhalt.containsKey(rein.mat))
			inhalt.put(rein.mat, inhalt.get(rein.mat) + abzug);
		else
			inhalt.put(rein.mat, abzug);
		if(rein.mat.fluid)
			fluidMenge += abzug;
		else
			solidMenge += abzug;
	}

	public List<Materie> sortiert()
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
		a.inhalt.forEach((mat, m) -> neu.inhalt.merge(mat, m, Integer::sum));
		return neu;
	}
}