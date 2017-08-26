package z.np;

import idk.*;
import java.util.*;
import mark.*;
import z.np.boden.*;
import z.np.haus.*;

public class NPXtb extends Xtb
{
	public NPXtb()
	{
		Schichten schichten = new Schichten(1, 0, 10, 10);
		XKarte.karten = Arrays.asList(schichten.karten);
		NPKarte k = schichten.karte(0);
		XKarte.aktuell = k;
		k.objekte.add(new Haus(0, 0, 2, 2, true, true, k));
		for(int i = 0; i < k.xw; i++)
			k.objekte.add(new NPChara(i, 2, 1, 1, true, true, k));
		for(int i = 0; i < k.xw; i++)
			k.objekte.add(new NPChara(i, 3, 1, 1, true, true, k));
		alleKarten = true;
		XKarte.mark = new NMark();
	}
}