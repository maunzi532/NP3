package z.np;

import auftrag.*;
import idk.*;
import interf.*;
import pfadfind.*;
import z.np.haus.*;

public class NPMark extends Mark
{
	private static final int dticks = 10;
	private int dtick = 10;

	@Override
	public void verarbeite()
	{
		if(XKarte.aktuell == null)
			return;
		if(!ziel.existent)
			dtick = dticks;
		else
		{
			if(dtick > 0)
				dtick--;
			if(dtick <= 0)
			{
				if(fokus.existent)
					verarbeite2();
				ziel.existent = false;
			}
		}
	}

	public void verarbeite2()
	{
		if(fokus.marked instanceof NPChara)
		{
			NPChara c = (NPChara) fokus.marked;
			if(ziel.marked == c)
				//Info
				XKarte.gui.add(new UIAnschluss(c.auf, c, 1, -1,
						new InfoTimed("NPChara", false, 100, 2, 2, 1, 1, 2, 2, 1, 1, 2)));
			else if(ziel.marked instanceof Haus)
			{
				//Hineingehen
				Haus h = (Haus) ziel.marked;
				if(h.zeigeCharas().size() >= h.maxCharas())
					XKarte.gui.add(new UIAnschluss(h.auf, h, 1, -1,
							new InfoTimed("Haus voll", false, 100, 2, 2, 1, 1, 2, 2, 1, 1, 2)));
				else
					new FolgeZiel(h, 2, new BetreteZiel(h)).an(c);
			}
			else if(ziel.marked instanceof NPChara)
			{
				//Items tauschen
				//Fähigkeit einsetzen
				new FolgeZiel(ziel.marked, 2).an(c);
			}
			else if(ziel.marked == null && XKarte.aktuell.begehbar(mitFokus(ziel), fokus.marked))
				new GeheZuZiel(new Koordinate(ziel.x, ziel.y), 0).an((KChara) fokus.marked);
		}
		if(fokus.marked instanceof Haus)
		{
			Haus h = (Haus) fokus.marked;
			if(ziel.marked == h)
				//Info
				/*XKarte.gui.add(new UIAnschluss(h.auf, h, 1, -1,
						new InfoTimed("Haus", false, 100, 2, 2, 1, 1, 2, 2, 1, 1, 2)));*/
				XKarte.gui.add(new UIAnschluss(0, 0, new HausGUI(h)));
			/*else if(ziel.marked instanceof Haus)
			{
				//Kabel verlegen
			}*/
		/*else if(ziel.marked instanceof NPChara)
		{
			//Energiestrahlen?
		}
		else if(ziel.marked == null)
		{
			//Fahren
		}*/
		}
	}
}