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
				Bodenteil f = new Bodenteil();
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
	}

	public FluidG surroundcheckNewField(int x, int y, int r)
	{
		FluidG hier = fliesen[x][y].fluidG;
		return null;
	}
}