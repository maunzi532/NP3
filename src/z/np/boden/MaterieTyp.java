package z.np.boden;

public enum MaterieTyp
{
	ERDE1(4, 8, 20, 1),
	STEIN(8, 16, 20, 2),
	LAVA(16, 18, 8),
	WASSER(16, 10, 1),
	GSL(8, 8, 3);

	public final boolean fluid;
	public final int tpb;
	public final int invspc;
	public final int tiefe;
	public final int temp;

	MaterieTyp(int tpb, int invspc, int tiefe, int temp)
	{
		fluid = false;
		this.tpb = tpb;
		this.invspc = invspc;
		this.tiefe = tiefe;
		this.temp = temp;
	}

	MaterieTyp(int invspc, int tiefe, int temp)
	{
		fluid = true;
		this.tpb = 0;
		this.invspc = invspc;
		this.tiefe = tiefe;
		this.temp = temp;
	}
}