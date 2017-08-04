package z.np.boden;

import java.util.*;
import karte.*;

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
}