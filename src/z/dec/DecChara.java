package z.dec;

import idk.*;
import interf.*;
import java.util.*;
import karte.*;
import pfadfind.*;
import z.dec.duell.*;

public class DecChara extends KChara
{
	S4I s4i;

	public DecChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
		s4i = new S4I(0, 0, 0, 1, 0, 0, 0);
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
		if(DecMark.duell == null)
			DecMark.duell = new Backend(mir, this, true);
		else
			XKarte.th.addText("Nicht möglich");
	}
}