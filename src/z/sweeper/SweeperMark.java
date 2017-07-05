package z.sweeper;

import auftrag.*;
import idk.*;
import interf.*;
import karte.*;
import pfadfind.*;

public class SweeperMark extends Mark
{
	public SweeperMark()
	{
		Karte k = new SweeperKarte(40, 40);
		XKarte.aktuell = k;
		XKarte.karten.add(k);
		k.objekte.add(new KChara(0, 0, 2, 2, true, true, k));
		k.objekte.add(new KChara(2, 2, 1, 1, true, true, k));
		//kamLock = aktuell.objekte.get(1);
		XKarte.gui.add(new UIAnschluss(1, 1, new MultiOption(new String[]{"W1", "W2"}, 0, 2, 0, 1, 0, 1, 1, 5, 1, 20))
		{
			public void code(int re)
			{
				System.out.println(re);
				if(re == 1)
					XKarte.aktuell = null;
			}
		});
	}

	@Override
	public void verarbeite()
	{
		if(XKarte.aktuell == null)
			return;
		if(hover.existent && XKarte.aktuell.fliese(hover.x, hover.y) instanceof SweeperFeld)
		{
			SweeperFeld sf = (SweeperFeld) XKarte.aktuell.fliese(hover.x, hover.y);
			if(TA.take[201] == 2)
				sf.aufdecken(0);
			if(TA.take[203] == 2)
				sf.aufdecken(1);
		}
		if(fokus.existent && fokus.marked != null && ziel.existent)
		{
			if(ziel.marked != null)
			{
				XKarte.gui.add(new UIAnschluss(XKarte.aktuell, fokus.marked, 1, -1,
						new MenuItem("Wugutest", false, 2, 3, 1, 1, 1, 3, 1, 1, 1)));
				new FolgeZiel(ziel.marked, 2).an((KChara) fokus.marked);
			}
			else
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y), XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked) ? 0 : 2).an((KChara) fokus.marked);
			ziel.existent = false;
		}
	}
}