package z.np.boden;

import idk.*;
import java.util.*;
import java.util.stream.*;
import karte.*;
import pfadfind.*;

public class NPKarte extends Karte<Bodenteil>
{
	public ArrayList<FluidG> pools;

	public NPKarte(int xw, int yw)
	{
		super(xw, yw);
		fliesen = new Bodenteil[xw][yw];
		pools = new ArrayList<>();
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
			{
				Bodenteil f = new Bodenteil(x, y);
				fliesen[x][y] = f;
				if(f.fluidG != null)
					pools.add(f.fluidG);
			}

	}

	@Override
	public Bodenteil ausserhalb()
	{
		return new Bodenteil();
	}

	@Override
	public void tick()
	{
		super.tick();
		for(int i = 0; i < pools.size(); i++)
			label68:
				for(Bodenteil b : pools.get(i).teile)
					for(int r = 0; r < 4; r++)
					{
						FluidG a1 = surroundcheckNewField(b.x, b.y, r);
						if(a1 != null && a1 != pools.get(i))
						{
							pools.add(pools.remove(i).verbinde(a1));
							pools.remove(a1);
							break label68;
						}
					}
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
				fliesen[x][y].tick();
	}

	public FluidG surroundcheckNewField(int x, int y, int r)
	{
		if(r < 3)
			x += r - 1;
		if(r > 0)
			y += r - 2;
		return fliesen[x][y].fluidG;
	}

	public void addCharaInArea(KChara chara, KChara von)
	{
		int minx = von.x - 1;
		if(minx < 0)
			minx = 0;
		int endx = von.x + von.xg + 1;
		if(endx > xw)
			endx = xw;
		int miny = von.y - 1;
		if(miny < 0)
			miny = 0;
		int endy = von.y + von.yg + 1;
		if(endy > yw)
			endy = yw;
		ArrayList<Bodenteil> auswahl = new ArrayList<>();
		for(int ix = minx; ix < endx; ix++)
			for(int iy = miny; iy < endy; iy++)
				auswahl.add(fliese(ix, iy));
		List<Bodenteil> auswahl2 = auswahl.stream().filter(e -> begehbar(new KOrt(e.x, e.y, 1, 1), chara)).collect(Collectors.toList());
		if(auswahl2.size() == 0)
			auswahl2 = auswahl;
		Bodenteil zielort = auswahl2.stream().min(Comparator.comparingInt(e -> chara.abstand(e.x, e.y))).get();
		chara.x = zielort.x;
		chara.y = zielort.y;
		chara.auf = this;
		add.add(chara);
	}
}