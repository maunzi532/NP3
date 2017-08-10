package z.np.haus;

public abstract class InnenTeil
{
	InnenTeilTyp typ;
	Haus von;
	long anteil;
	boolean update;

	public InnenTeil(InnenTeilTyp typ, Haus von)
	{
		this.typ = typ;
		this.von = von;
	}

	public abstract void setAnteil(long anteil1);
}