package z.dec;

import auftrag.*;
import idk.*;
import interf.*;
import pfadfind.*;

public class DecMark extends Mark
{
	public DecMark()
	{
		super();
		XKarte.gui.add(new UIAnschluss(1, 1, new MultiOption(new String[]{"W1", "W2"}, 0, 2, 0, 1, 0, 1, 1, 5, 1, 20))
		{
			public void code(int re)
			{
				System.out.println(re);
			}
		});
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
				/*XKarte.gui.add(new UIAnschluss(XKarte.aktuell, fokus.marked, 1, -1,
						new MenuItem("Wugutest", false, 2, 3, 1, 1, 1, 3, 1, 1, 1)));*/
				new FolgeZiel(ziel.marked, 2).an((KChara) fokus.marked);
			}
			else if(XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked))
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y), 0).an((KChara) fokus.marked);
			ziel.existent = false;
		}
	}
}