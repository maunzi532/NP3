package z.np.haus.innen;

import z.np.haus.*;
import z.np.transfer.*;

public class InnenEnergie extends InnenTeil implements EnergieTransferer
{
	private long energie;
	private long maxenergie;

	public InnenEnergie(Haus von, int anteil)
	{
		super(InnenTeilTyp.ENERGIESPEICHER, von);
		setAnteil(anteil);
	}

	@Override
	public void setAnteil(long anteil1)
	{
		anteil = anteil1;
		maxenergie = anteil * anteil;
		if(energie > maxenergie)
			energie = maxenergie;
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

	@Override
	public Long zeigeEnergie()
	{
		return energie;
	}

	@Override
	public long maxEnergie()
	{
		return maxenergie;
	}

	public static InnenEnergie von(Haus h)
	{
		InnenTeil t = h.getInnenTeil(InnenTeilTyp.ENERGIESPEICHER);
		return t == null ? null : (InnenEnergie) t;
	}
}