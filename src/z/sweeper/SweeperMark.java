package z.sweeper;

import auftrag.*;
import idk.*;
import interf.*;
import pfadfind.*;

public class SweeperMark extends Mark
{
	@Override
	public void verarbeite()
	{
		if(hover.existent && XKarte.aktuell.fliese(hover.x, hover.y) instanceof SweeperFeld)
		{
			SweeperFeld sf = (SweeperFeld) XKarte.aktuell.fliese(hover.x, hover.y);
			if(TA.take[201] == 2)
				sf.aufdecken(0);
			if(TA.take[203] == 2)
				sf.aufdecken(1);
		}
		if(fokus.existent && ziel.existent)
		{
			if(ziel.marked != null)
			{
				XKarte.gui.add(new UIAnschluss(XKarte.aktuell, fokus.marked, 1, -1,
						new MenuItem("Wugutest", false, 2, 3, 1, 1, 1, 3, 1, 1, 1)));
				new FolgeZiel(ziel.marked, 2).an((KChara) fokus.marked);
			}
			else
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y),
						XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked) ? 0 : 2).an((KChara) fokus.marked);
			ziel.existent = false;
		}
	}
}