package z.np.boden;

public class MaterieTyp
{
	public static final MaterieTyp ERDE1 = new MaterieTyp("Erde v1", 0, 4, 8, 20, 1);
	public static final MaterieTyp STEIN1 = new MaterieTyp("Stein v1", 1, 8, 16, 20, 2);
	public static final MaterieTyp LAVA = new MaterieTyp("Lava", 2, 16, 18, 8);
	public static final MaterieTyp WASSER = new MaterieTyp("Wasser", 3, 16, 10, 1);
	public static final MaterieTyp GSL = new MaterieTyp("GSL", 4, 8, 8, 3);

	public final String name;
	public final int num;
	public final boolean fluid;
	public final int tpb;
	public final int invspc;
	public final int tiefe;
	public final int temp;

	MaterieTyp(String name, int num, int tpb, int invspc, int tiefe, int temp)
	{
		this.name = name;
		this.num = num;
		fluid = false;
		this.tpb = tpb;
		this.invspc = invspc;
		this.tiefe = tiefe;
		this.temp = temp;
	}

	MaterieTyp(String name, int num, int invspc, int tiefe, int temp)
	{
		this.name = name;
		this.num = num;
		fluid = true;
		this.tpb = 0;
		this.invspc = invspc;
		this.tiefe = tiefe;
		this.temp = temp;
	}
}