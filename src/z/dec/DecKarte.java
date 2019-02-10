package z.dec;

import karte.*;

public class DecKarte extends Karte<Feld>
{
	public DecKarte(int xw, int yw)
	{
		super(xw, yw);
		fliesen = new Feld[xw][yw];
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
				fliesen[x][y] = Feld.NFELD;
		objekte.add(new DecChara(0, 0, 2, 2, true, true, this));
		objekte.add(new DecChara(2, 2, 1, 1, true, true, this));
	}

	@Override
	public Feld ausserhalb()
	{
		return Feld.LEERE;
	}
}