package z.np.boden;

public enum Materie
{
	ERDE1(4, 8, 20, 1),
	STEIN(8, 16, 20, 2),
	LAVA(16, 18, 8),
	WASSER(16, 10, 1),
	GSL(8, 8, 3);

	final boolean fluid;
	final int tpb;
	final int invspc;
	final int tiefe;
	final int temp;

	Materie(int tpb, int invspc, int tiefe, int temp)
	{
		fluid = false;
		this.tpb = tpb;
		this.invspc = invspc;
		this.tiefe = tiefe;
		this.temp = temp;
	}

	Materie(int invspc, int tiefe, int temp)
	{
		fluid = true;
		this.tpb = 0;
		this.invspc = invspc;
		this.tiefe = tiefe;
		this.temp = temp;
	}
}