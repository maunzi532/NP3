package z.np;

import auftrag.*;
import idk.*;
import interf.*;
import pfadfind.*;

public class NPMark extends Mark
{
	public NPMark()
	{
		super();
		alleKarten = true;
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