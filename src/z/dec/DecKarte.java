package z.dec;

import karte.*;
import pfadfind.*;

public class DecKarte extends Karte
{
	public DecKarte(int xw, int yw)
	{
		super(xw, yw);
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
				fliesen[x][y] = Feld.NFELD;
		objekte.add(new KChara(0, 0, 2, 2, true, true, this));
		objekte.add(new KChara(2, 2, 1, 1, true, true, this));
	}
}