package pfadfind;

import java.util.*;

public class Pfad
{
	Koordinate vor = null;
	Koordinate hier;
	int abstandZ;
	int verarbeitung = 0;

	Pfad(Koordinate hier, Koordinate t)
	{
		this.hier = hier;
		int ux = t.x - hier.x;
		int uy = t.y - hier.y;
		abstandZ = ux * ux + uy * uy;
	}

	Pfad(Koordinate vor, Koordinate hier, Koordinate t)
	{
		this(hier, t);
		this.vor = vor;
	}

	Pfad(Pfad kopier, Koordinate nvor)
	{
		hier = kopier.hier;
		verarbeitung = kopier.verarbeitung;
		abstandZ = kopier.abstandZ;
		vor = nvor;
	}

	int len(HashMap<Koordinate, Pfad> pfade)
	{
		if(vor == null)
			return 0;
		int ux = hier.x - vor.x;
		int uy = hier.y - vor.y;
		int len1 = ux * ux + uy * uy + 1;
		if(pfade.containsKey(vor))
			return len1 + pfade.get(vor).len(pfade);
		return len1;
	}

	void suche(HashMap<Koordinate, Pfad> pfade, ArrayList<Koordinate> re, boolean a)
	{
		if(a)
			re.add(hier);
		if(vor != null)
			pfade.get(vor).suche(pfade, re, true);
	}

	@Override
	public String toString()
	{
		return "Pfad{" +
				(vor != null ? "vor = " + vor.x + " " + vor.y : "") +
				", hier = " + hier.x + " " + hier.y +
				", abstandZ = " + abstandZ +
				", verarbeitung = " + verarbeitung +
				'}';
	}
}