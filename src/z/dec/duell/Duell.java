package z.dec.duell;

import idk.*;
import java.util.*;
import z.dec.*;

public class Duell
{
	public ArrayList<DecChara> team1 = new ArrayList<>();
	public ArrayList<DecChara> team2 = new ArrayList<>();
	public DuellStatus status = DuellStatus.NICHTS;

	public Duell(DecChara team1, DecChara team2, boolean duell)
	{
		if(duell)
		{
			this.team1.add(team1);
			this.team2.add(team2);
		}
	}

	public enum DuellStatus
	{
		NICHTS,
		INIT,
		AUSWAHL,
		AUSF,
		ENDE
	}

	public void tick()
	{
		switch(status)
		{
			case NICHTS:
				XKarte.th.addText("Initialisiere");
				status = DuellStatus.INIT;
				break;
			case INIT:
				status = DuellStatus.AUSWAHL;
				break;
			case AUSWAHL:
				status = DuellStatus.AUSF;
				break;
			case AUSF:
				//XKarte.th.addText("Wugu");
				status = DuellStatus.AUSWAHL;
				break;
			case ENDE:
				DecMark.duell = null;
				break;
		}
	}
}