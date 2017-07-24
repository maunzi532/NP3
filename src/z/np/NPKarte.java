package z.np;

import karte.*;

public class NPKarte extends Karte
{
	public NPKarte(int xw, int yw)
	{
		super(xw, yw);
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
				fliesen[x][y] = Feld.NFELD;
	}
}