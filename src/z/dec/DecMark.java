package z.dec;

import auftrag.*;
import idk.*;
import interf.*;
import java.util.*;
import karte.*;
import pfadfind.*;

public class DecMark extends Mark
{
	public Texthalter th;

	public DecMark()
	{
		super();
		Karte k = new DecKarte(10, 10);
		XKarte.aktuell = k;
		XKarte.karten.add(k);
		th = new Texthalter(0, 1, 0, 1, 1, 1, 1, 1);
		XKarte.gui.add(new UIAnschluss(0, 1, th));
		th.addText("hi");
		th.addText("wugu");
		/*XKarte.gui.add(new UIAnschluss(1, 1, new MultiOption(new String[]{"W1", "W2"}, 0, 2, 0, 1, 0, 1, 1, 5, 1, 20))
		{
			public void code(int re)
			{
				System.out.println(re);
			}
		});*/
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
				ArrayList<Exec> execs = new ArrayList<>();
				execs.add(new Exec("Gehe zu", e -> new FolgeZiel(ziel.marked, 2).an((KChara) fokus.marked)));
				execs.add(new Exec("Ende", e -> {}));
				/*XKarte.gui.add(new UIAnschluss(XKarte.aktuell, ziel.marked, 1, -1,
						new VariableOption(execs, 3, 2, 0, 1, 0, 1, 2, 1, 1, 2)));*/
				new FolgeZiel(ziel.marked, 2, new Nachfrage(ziel.marked, execs)).an((KChara) fokus.marked);
			}
			else if(XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked))
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y), 0).an((KChara) fokus.marked);
			ziel.existent = false;
		}
	}
}