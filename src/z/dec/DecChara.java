package z.dec;

import idk.*;
import interf.*;
import java.util.*;
import karte.*;
import pfadfind.*;

public class DecChara extends KChara
{
	public DecChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
	}

	public ArrayList<Exec> execs(DecChara fuer)
	{
		ArrayList<Exec> execs = new ArrayList<>();
		execs.add(new Exec("Ende", e -> {}));
		return execs;
	}

	public void gibmirtext(DecChara mir)
	{
		XKarte.th.addText("Nicht implementiert");
	}

	public void gibmirduell(DecChara mir)
	{
		XKarte.th.addText("Noch nicht implementiert");
	}
}