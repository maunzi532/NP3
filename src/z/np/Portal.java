package z.np;

import idk.*;
import java.util.*;
import karte.*;
import z.np.boden.*;
import z.np.transfer.*;

public class Portal extends KObjekt implements CharaTransferer
{
	public Portal(int x, int y, int xg, int yg)
	{
		super(x, y, xg, yg, true, false, null);
	}

	@Override
	public boolean requestChara(NPChara chara, boolean real)
	{
		return false;
	}

	@Override
	public boolean acceptChara(NPChara chara, boolean real)
	{
		if(!real)
			return true;
		NPKarte auf1 = (NPKarte) auf;
		KOrt mitte = new KOrt(x + xg / 2, y + yg / 2, 1, 1);
		ArrayList<Bodenteil> auswahl = new ArrayList<>();
		for(int ix = x; ix < x + xg; ix++)
			for(int iy = y; iy < y + yg; iy++)
				auswahl.add(auf1.fliese(ix, iy));
		Bodenteil zielort = auswahl.stream().filter(e -> auf1.begehbar(new KOrt(e.x, e.y, 1, 1), chara))
				.min(Comparator.comparingInt(e -> mitte.abstand(e.x, e.y))).orElseGet(() -> auf1.fliese(mitte.x, mitte.y));
		chara.inHaus = null;
		chara.x = zielort.x;
		chara.y = zielort.y;
		chara.auf = auf1;
		chara.existent = true;
		auf1.charas.add(chara);
		auf1.add.add(chara);
		return true;
	}
}