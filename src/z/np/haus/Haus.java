package z.np.haus;

import java.util.*;
import karte.*;
import pfadfind.*;
import z.np.*;
import z.np.boden.*;

public class Haus extends KChara implements Transferer
{
	NPKarte auf2;
	Bauplan plan;
	HashMap<InnenTeilTyp, InnenTeil> innen = new HashMap<>();

	ArrayList<LinkKabel> rein = new ArrayList<>();
	ArrayList<LinkKabel> raus = new ArrayList<>();

	long stabil;
	long maxStabil;

	public Haus(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, Karte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
		innen.put(InnenTeilTyp.RAUM, new InnenRaum(this, 150));
	}

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		InnenEnergie ie = InnenEnergie.von(this);
		return ie != null && ie.requestEnergie(menge, real);
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		InnenEnergie ie = InnenEnergie.von(this);
		return ie == null ? menge : ie.acceptEnergie(menge, real);
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		InnenMaterie im = InnenMaterie.von(this);
		return im != null && im.requestMaterie(mat, real);
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		InnenMaterie im = InnenMaterie.von(this);
		return im == null ? mat : im.acceptMaterie(mat, real);
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		InnenItems it = InnenItems.von(this);
		return it != null && it.acceptItem(item, real);
	}
}