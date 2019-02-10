package z.np.haus;

import z.np.*;
import z.np.boden.*;
import z.np.transfer.*;

public class LinkKabel implements EnergieTransferer, MaterieTransferer, ItemTransferer
{
	LinkKabelTyp typ;
	boolean request;
	EnergieTransferer startE;
	EnergieTransferer zielE;
	MaterieTransferer startM;
	MaterieTransferer zielM;
	ItemTransferer startI;
	ItemTransferer zielI;

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		return request && typ == LinkKabelTyp.ENERGIE && startE.requestEnergie(menge, real);
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		return !request && typ == LinkKabelTyp.ENERGIE ? zielE.acceptEnergie(menge, real) : menge;
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		return request && typ == LinkKabelTyp.MATERIE && startM.requestMaterie(mat, real);
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		return !request && typ == LinkKabelTyp.MATERIE ? zielM.acceptMaterie(mat, real) : mat;
	}

	@Override
	public boolean requestItem(Item item, boolean real)
	{
		return request && typ == LinkKabelTyp.ITEMS && startI.requestItem(item, real);
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		return !request && typ == LinkKabelTyp.ITEMS && zielI.acceptItem(item, real);
	}
}