package z.np.haus;

import java.util.*;
import karte.*;
import pfadfind.*;
import z.np.*;

public class Haus extends KChara implements Einheit
{
	Bauplan plan;
	HashMap<InnenTeilTyp, InnenTeil> innen = new HashMap<>();

	ArrayList<LinkKabel> rein = new ArrayList<>();
	ArrayList<LinkKabel> raus = new ArrayList<>();
	long kabellimit;
	long transferlimit;
	long itemlimit;

	long stabil;
	long maxStabil;

	public Haus(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
		innen.put(InnenTeilTyp.RAUM, new InnenRaum(this, 200));
	}

	public InnenRaum innenRaum()
	{
		return (InnenRaum) innen.get(InnenTeilTyp.RAUM);
	}
}