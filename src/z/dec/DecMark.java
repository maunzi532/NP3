package z.dec;

import auftrag.*;
import idk.*;
import interf.*;
import java.util.*;
import karte.*;
import pfadfind.*;

public class DecMark extends Mark
{
	public DecMark()
	{
		super();
		Karte k = new DecKarte(10, 10);
		XKarte.aktuell = k;
		XKarte.karten.add(k);
		XKarte.th.addText("hi");
		XKarte.th.addText("wugu");
	}

	@Override
	public void verarbeite()
	{
		if(XKarte.aktuell == null)
			return;
		if(fokus.existent && fokus.marked != null && ziel.existent)
		{
			if(ziel.marked != null)
			{
				if(fokus.marked instanceof DecChara && ziel.marked instanceof DecChara)
				{
					DecChara act1 = (DecChara) fokus.marked;
					DecChara act2 = (DecChara) ziel.marked;
					ArrayList<Exec> execs = new ArrayList<>();
					execs.add(new Exec("Labern", e -> act2.gibmirtext(act1)));
					execs.add(new Exec("Duell", e -> act2.gibmirduell(act1)));
					execs.add(new Exec("Ende", e -> {}));
					new FolgeZiel(ziel.marked, 2, new Nachfrage(ziel.marked, execs)).an((KChara) fokus.marked);
				}
				else
					new FolgeZiel(ziel.marked, 2).an((KChara) fokus.marked);
			}
			else if(XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked))
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y), 0).an((KChara) fokus.marked);
			ziel.existent = false;
		}
	}
}