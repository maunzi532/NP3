package z.np.boden;

public class Materie
{
	public final MaterieTyp typ;
	public final long menge;

	public Materie(MaterieTyp typ, long menge)
	{
		this.typ = typ;
		this.menge = menge;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof Materie)) return false;

		Materie materie = (Materie) o;

		return typ == materie.typ;
	}

	@Override
	public int hashCode()
	{
		return typ.hashCode();
	}
}