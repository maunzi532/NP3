package z.np.transfer;

public interface EnergieTransferer
{
	boolean requestEnergie(long menge, boolean real);

	long acceptEnergie(long menge, boolean real);

	default Long zeigeEnergie()
	{
		return null;
	}

	default long maxEnergie()
	{
		return 0;
	}

	default boolean versende(EnergieTransferer an, long menge)
	{
		menge -= an.acceptEnergie(menge, false);
		return menge > 0 && requestEnergie(menge, true) && acceptEnergie(menge, true) == 0;
	}
}