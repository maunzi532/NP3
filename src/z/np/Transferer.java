package z.np;

import z.np.boden.*;

public interface Transferer
{
	default boolean requestEnergie(long menge, boolean real)
	{
		return false;
	}

	default long acceptEnergie(long menge, boolean real)
	{
		return menge;
	}

	default boolean requestMaterie(Materie mat, boolean real)
	{
		return false;
	}

	default Materie acceptMaterie(Materie mat, boolean real)
	{
		return mat;
	}

	default boolean acceptItem(Item item, boolean real)
	{
		return false;
	}
}