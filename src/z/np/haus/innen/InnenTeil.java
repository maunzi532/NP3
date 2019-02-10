package z.np.haus.innen;

import z.np.haus.*;

public abstract class InnenTeil
{
	protected InnenTeilTyp typ;
	protected Haus von;
	protected long anteil;

	public InnenTeil(InnenTeilTyp typ, Haus von)
	{
		this.typ = typ;
		this.von = von;
	}

	public abstract void setAnteil(long anteil1);
}