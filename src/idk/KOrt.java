package idk;

public class KOrt
{
	public int x, y, xg, yg;

	public KOrt(int x, int y, int xg, int yg)
	{
		this.x = x;
		this.y = y;
		this.xg = xg;
		this.yg = yg;
	}

	public KOrt(KOrt k)
	{
		this.x = k.x;
		this.y = k.y;
		this.xg = k.xg;
		this.yg = k.yg;
	}
}