package z.dec.duell;

import idk.*;
import interf.*;
import z.dec.*;

public class DeinController implements Controller
{
	public Backend backend;
	public DuellStatus status = DuellStatus.INIT;

	public DeinController(Backend backend)
	{
		this.backend = backend;
	}

	public enum DuellStatus
	{
		INIT,
		IDLE,
		AUSWAHL,
		AUSF,
		ENDE
	}

	@Override
	public String sendDataIfReady()
	{
		switch(status)
		{
			case INIT:
				//XKarte.th.addText("Initialisiere");
				XKarte.gui.add(new UIAnschluss(1, 1, new MenuItem("Fertig", true, 2, -1, 6, -1, 6, 1, 6, 1, 6)
				{
					@Override
					public void onClick(boolean r)
					{
						int index = backend.team1.indexOf(XKarte.mark.fokus.marked);
						if(index >= 0)
						{
							status = DuellStatus.AUSWAHL;
							weg = 1;
						}
						else
							XKarte.th.addText("Nichts ausgewählt");
					}
				}));
				status = DuellStatus.IDLE;
				break;
			case AUSWAHL:
				//System.out.println("W");
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
		return null;
	}
}