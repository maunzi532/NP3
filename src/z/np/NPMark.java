package z.np;

import auftrag.*;
import idk.*;
import interf.*;
import java.util.*;
import karte.*;
import pfadfind.*;
import z.np.haus.*;

public class NPMark extends Mark
{
	//private static final int dticks = 10;
	//private int dtick = 10;

	@Override
	public void mdk1()
	{
		if(fokus.marked != null && TA.take[203] == 2)
			fokus.marked = null;
		if(hover.marked != null)
		{
			if(fokus.marked != null)
			{
				ArrayList<Integer> t1 = fokus.marked.tasten2(hover.marked);
				int i = 0;
				for(; i < t1.size(); i++)
					if(t1.get(i) != null && TA.take[t1.get(i)] == 2)
						break;
				if(i < t1.size() || TA.take[201] == 2)
				{
					ArrayList<Exec> optionen = fokus.marked.optionen2(hover.marked);
					if(i < t1.size())
					{
						Exec exec = optionen.get(i);
						if(exec != null)
						{
							exec.los();
							fokus.marked = null;
							return;
						}
					}
					if(TA.take[201] == 2)
					{
						optionen.removeIf(Objects::isNull);
						if(optionen.size() > 0 && fokus.marked instanceof KChara)
							new Nachfrage(hover.marked, optionen).an((KChara) fokus.marked);
						fokus.marked = null;
					}
				}
			}
			else
			{
				ArrayList<Integer> t1 = hover.marked.tasten1();
				int i = 0;
				for(; i < t1.size(); i++)
					if(t1.get(i) != null && TA.take[t1.get(i)] == 2)
						break;
				if(i < t1.size() || TA.take[201] == 2 || TA.take[202] == 2)
				{
					ArrayList<Exec> optionen = hover.marked.optionen1();
					if(i < t1.size())
					{
						Exec exec = optionen.get(i);
						if(exec != null)
						{
							exec.los();
							return;
						}
					}
					if(TA.take[201] == 2 || TA.take[202] == 2)
					{
						fokus.marked = hover.marked;
						if(TA.take[201] == 2)
						{
							optionen.removeIf(Objects::isNull);
							if(optionen.size() > 0 && hover.marked instanceof KChara)
								new Nachfrage(optionen).an((KChara) hover.marked);
						}
					}
				}
			}
		}
	}

	@Override
	public void verarbeite()
	{
		/*if(XKarte.aktuell == null)
			return;
		if(ziel.marked == null)
			dtick = dticks;
		else
		{
			if(dtick > 0)
				dtick--;
			if(dtick <= 0)
			{
				if(fokus.marked != null)
					verarbeite2();
				ziel.marked = null;
			}
		}*/
	}

	@Override
	public boolean fso()
	{
		return false;
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
				new FolgeZiel((KObjekt) ziel.marked, 2).an(c);
			}
			else if(ziel.marked != null && XKarte.aktuell.begehbar(mitFokus(ziel), (KObjekt) fokus.marked))
				new GeheZuZiel(new Koordinate(ziel.marked.ort().x, ziel.marked.ort().y), 0).an((KChara) fokus.marked);
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