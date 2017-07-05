package z.np;

import java.util.*;
import karte.*;
import pfadfind.*;

public class NPChara extends KChara implements Einheit
{
	NTyp typ;

	Haus inHaus;

	long energie;
	long maxEnergie;

	long leben;
	long maxLeben;

	long[] align;

	ArrayList<long[]> effects;

	public NPChara(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
	}
}