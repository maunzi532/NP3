package z.dec;

import idk.*;
import karte.*;
import z.dec.duell.*;

public class DecMark extends Mark
{
	public static Backend duell;

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
	public void mdk1()
	{
		if(TA.take[201] == 2)
			fokus.marked = hover.marked;
		if(TA.take[203] == 2)
			ziel.marked = hover.marked;
	}

	@Override
	public void verarbeite()
	{
		/*if(duell != null)
			duell.tick();
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
					ArrayList<Exec<DecChara>> execs = new ArrayList<>();
					execs.add(new Exec<>("Labern", e -> e.gibmirtext(act1), act2));
					execs.add(new Exec<>("Duell", e -> e.gibmirduell(act1), act2));
					execs.add(new Exec<>("Ende", e -> {}, act2));
					new FolgeZiel(ziel.marked, 2, new Nachfrage(ziel.marked, execs)).an((KChara) fokus.marked);
				}
				else
					new FolgeZiel(ziel.marked, 2).an((KChara) fokus.marked);
			}
			else if(XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked))
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y), 0).an((KChara) fokus.marked);
			ziel.existent = false;
		}*/
	}
}