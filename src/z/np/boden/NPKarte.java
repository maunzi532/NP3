package z.np.boden;

import idk.*;
import java.util.*;
import karte.*;
import pfadfind.*;
import z.np.*;
import z.np.transfer.*;

public class NPKarte extends Karte<Bodenteil> implements CharaTransferer
{
	int level;
	public ArrayList<FluidG> pools;
	public List<NPChara> charas;
	public Portal portal;

	public NPKarte(int level, int xw, int yw, Portal portal)
	{
		super(xw, yw);
		this.portal = portal;
		fliesen = new Bodenteil[xw][yw];
		pools = new ArrayList<>();
		charas = new ArrayList<>();
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

	public boolean addCharaInArea(NPChara chara, KChara von)
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
		Optional<Bodenteil> zielort1 = auswahl.stream()
				.filter(e -> begehbar(new KOrt(e.x, e.y, 1, 1), chara))
				.min(Comparator.comparingInt(e -> chara.abstand(e.x, e.y)));
		if(zielort1.isPresent())
		{
			Bodenteil zielort = zielort1.get();
			chara.x = zielort.x;
			chara.y = zielort.y;
			chara.auf = this;
			add.add(chara);
			charas.add(chara);
			return true;
		}
		return false;
	}

	@Override
	public boolean requestChara(NPChara chara, boolean real)
	{
		if(!objekte.contains(chara))
			return false;
		if(real)
		{
			charas.remove(chara);
			rem.add(chara);
		}
		return true;
	}

	@Override
	public boolean acceptChara(NPChara chara, boolean real)
	{
		KChara von = chara.inHaus;
		Optional<Bodenteil> zielort1;
		if(von != null)
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
			Collection<Bodenteil> auswahl = new ArrayList<>();
			for(int ix = minx; ix < endx; ix++)
				for(int iy = miny; iy < endy; iy++)
					auswahl.add(fliese(ix, iy));
			zielort1 = auswahl.stream()
					.filter(e -> begehbar(new KOrt(e.x, e.y, 1, 1), chara))
					.min(Comparator.comparingInt(e -> chara.abstand(e.x, e.y)));
		}
		else if(begehbar(new KOrt(chara.x, chara.y, 1, 1), chara))
			zielort1 = Optional.of(fliese(chara.x, chara.y));
		else
			zielort1 = Optional.empty();
		if(real && zielort1.isPresent())
		{
			Bodenteil zielort = zielort1.get();
			chara.x = zielort.x;
			chara.y = zielort.y;
			chara.inHaus = null;
			chara.auf = this;
			charas.add(chara);
			add.add(chara);
		}
		return zielort1.isPresent();
	}

	@Override
	public List<NPChara> zeigeCharas()
	{
		return charas;
	}

	@Override
	public long maxCharas()
	{
		return -1;
	}
}