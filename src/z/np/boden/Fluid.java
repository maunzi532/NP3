package z.np.boden;

public class Fluid
{
	final Materie mat;
	int menge;

	public Fluid(Materie mat, int menge)
	{
		this.mat = mat;
		this.menge = menge;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof Fluid)) return false;

		Fluid fluid = (Fluid) o;

		return mat == fluid.mat;
	}

	@Override
	public int hashCode()
	{
		return mat.hashCode();
	}
}