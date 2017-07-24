package z.np;

import auftrag.*;
import idk.*;
import interf.*;
import karte.*;
import pfadfind.*;

public class NPMark extends Mark
{
	public NPMark()
	{
		Karte k = new NPKarte(10, 10);
		XKarte.aktuell = k;
		XKarte.karten.add(k);
		k.objekte.add(new Haus(0, 0, 2, 2, true, true, k));
		k.objekte.add(new NPChara(2, 2, 1, 1, true, true, k));
		k.objekte.add(new NPChara(0, 2, 1, 1, true, true, k));
		alleKarten = true;
	}

	@Override
	public void verarbeite()
	{
		if(XKarte.aktuell == null)
			return;
		/*if(fokus.existent && fokus.marked != null && ziel.existent)
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
		}*/
		if(fokus.existent && ziel.existent)
		{
			if(fokus.marked instanceof NPChara)
			{
				if(ziel.marked == fokus.marked)
					//Info
					XKarte.gui.add(new UIAnschluss(0, 0, new MenuItem("NPChara", false, 2, 0, 1, 0, 1, 1, 4, 1, 12)));
				else if(ziel.marked instanceof Haus)
				{
					//Hineingehen
					NPChara c = (NPChara) fokus.marked;
					Haus h = (Haus) ziel.marked;
					c.auf.objekte.remove(c);
				}
				else if(ziel.marked instanceof NPChara)
				{
					//Items tauschen
					//Fähigkeit einsetzen
				}
				else if(ziel.marked == null && XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked))
					new GeheZuZiel(new Koordinate(ziel.x, ziel.y), 0).an((KChara) fokus.marked);
			}
			if(fokus.marked instanceof Haus)
			{
				if(ziel.marked == fokus.marked)
					//Info
					XKarte.gui.add(new UIAnschluss(0, 0, new MenuItem("Haus", false, 2, 0, 1, 0, 1, 1, 4, 1, 12)));
				else if(ziel.marked instanceof Haus)
				{
					//Kabel verlegen
				}
				/*else if(ziel.marked instanceof NPChara)
				{
					//Energiestrahlen?
				}
				else if(ziel.marked == null)
				{
					//Fahren
				}*/
			}
			ziel.existent = false;
		}
	}
}