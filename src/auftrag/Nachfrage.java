package auftrag;

import idk.*;
import interf.*;
import java.util.*;

public class Nachfrage extends Auftrag
{
	Markierbar ziel;
	Markierbar marked;
	ArrayList<Exec> optionen;
	boolean added;
	UITeil ender;

	public Nachfrage(ArrayList<Exec> optionen)
	{
		this.optionen = optionen;
	}

	public Nachfrage(Markierbar ziel, ArrayList<Exec> optionen)
	{
		this.ziel = ziel;
		this.optionen = optionen;
	}

	@Override
	public Boolean los(boolean bewegt, boolean abbruch)
	{
		if(!added)
		{
			marked = ziel != null ? ziel : chara;
			XKarte.mark.ziel.marked = marked;
			XKarte.mark.ziel.taken = this;
			ender = new VariableOption(optionen, 3, 2, 0, 1, 0, 1, 2, 1, 1, 2);
			XKarte.gui.add(new UIAnschluss(XKarte.aktuell, ziel != null ? ziel.ort() : chara.ort(), 1, -1, ender));
			added = true;
		}
		if(XKarte.mark.ziel.taken != this)
		{
			ender.weg = 1;
			return false;
		}
		if(ender.weg > 0)
		{
			XKarte.mark.ziel.marked = null;
			return true;
		}
		return null;
	}
}