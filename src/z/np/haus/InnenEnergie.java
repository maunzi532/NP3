package z.np.haus;

import z.np.*;

public class InnenEnergie extends InnenTeil implements Transferer
{
	long energie;
	long maxenergie;

	public InnenEnergie(Haus von, int anteil)
	{
		super(InnenTeilTyp.ENERGIESPEICHER, von, anteil);
		maxenergie = anteil * anteil;
	}

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		if(energie < menge)
			return false;
		if(real)
			energie -= menge;
		return true;
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		long abzug = Math.min(menge, maxenergie - energie);
		if(real)
			energie += abzug;
		return menge - abzug;
	}

	public static InnenEnergie von(Haus h)
	{
		InnenTeil t = h.innen.get(InnenTeilTyp.ENERGIESPEICHER);
		return t == null ? null : (InnenEnergie) t;
	}
}