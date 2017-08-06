package z.np.haus;

public class InnenTeil
{
	InnenTeilTyp typ;
	Haus von;
	int anteil;

	public InnenTeil(InnenTeilTyp typ, Haus von, int anteil)
	{
		this.typ = typ;
		this.von = von;
		this.anteil = anteil;
	}
}