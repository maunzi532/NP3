package auftrag;

import idk.*;
import interf.*;
import java.util.*;
import karte.*;

public class Nachfrage<T extends KObjekt> extends Auftrag
{
	T ziel;
	ArrayList<Exec<T>> optionen;

	public Nachfrage(T ziel, ArrayList<Exec<T>> optionen)
	{
		this.ziel = ziel;
		this.optionen = optionen;
	}

	public Nachfrage(T ziel, ArrayList<Exec<T>> optionen, Auftrag danach)
	{
		super(danach);
		this.ziel = ziel;
		this.optionen = optionen;
	}

	@Override
	public Boolean los(boolean bewegt, boolean abbruch)
	{
		XKarte.gui.add(new UIAnschluss(XKarte.aktuell, ziel, 1, -1,
				new VariableOption(optionen, 3, 2, 0, 1, 0, 1, 2, 1, 1, 2)));
		return true;
	}
}