package auftrag;

import idk.*;
import interf.*;
import java.util.*;
import karte.*;

public class Nachfrage extends Auftrag
{
	KObjekt ziel;
	ArrayList<Exec> optionen;

	public Nachfrage(KObjekt ziel, ArrayList<Exec> optionen)
	{
		this.ziel = ziel;
		this.optionen = optionen;
	}

	public Nachfrage(KObjekt ziel, ArrayList<Exec> optionen, Auftrag danach)
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