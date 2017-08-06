package z.np.haus;

import z.np.*;
import z.np.boden.*;

public class LinkKabel implements Transferer
{
	LinkKabelTyp typ;
	boolean request;
	Transferer start;
	Transferer ziel;

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		return request && typ == LinkKabelTyp.ENERGIE && start.requestEnergie(menge, real);
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		return !request && typ == LinkKabelTyp.ENERGIE ? ziel.acceptEnergie(menge, real) : menge;
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		return request && typ == LinkKabelTyp.MATERIE && start.requestMaterie(mat, real);
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		return !request && typ == LinkKabelTyp.MATERIE ? ziel.acceptMaterie(mat, real) : mat;
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		return !request && typ == LinkKabelTyp.ITEMS && ziel.acceptItem(item, real);
	}
}