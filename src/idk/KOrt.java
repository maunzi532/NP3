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

	public int abstand(KOrt k)
	{
		int xa = 0;
		if(k.x + k.xg <= x)
			xa = x - k.x - k.xg + 1;
		else if(x + xg <= k.x)
			xa = k.x - x - xg + 1;
		int ya = 0;
		if(k.y + k.yg <= y)
			ya = y - k.y - k.yg + 1;
		else if(y + yg <= k.y)
			ya = k.y - y - yg + 1;
		return xa * xa + ya * ya;
	}

	public int abstand(int xd, int yd)
	{
		int xa = 0;
		if(xd < x)
			xa = x - xd;
		else if(x + xg <= xd)
			xa = xd - x - xg + 1;
		int ya = 0;
		if(yd < y)
			ya = y - yd;
		else if(y + yg <= yd)
			ya = yd - y - yg + 1;
		return xa * xa + ya * ya;
	}
}