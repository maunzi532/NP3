package idk;

public class PlD
{
	public int xw, yw, fwx, fwy, sichtx, sichty, kamx, kamy;
	public int xdfw, ydfw, xkm, ykm, xks, yks, xks2, yks2;

	public void calc()
	{
		xdfw = xw / 2 / sichtx;
		ydfw = yw / 2 / sichty;
		xkm = (kamx + fwx / 2) / fwx;
		ykm = (kamy + fwy / 2) / fwy;
		xks = (kamx - xkm * fwx) * xdfw / fwx;
		yks = (kamy - ykm * fwy) * ydfw / fwy;
		xks2 = xw / 2 - xks;
		yks2 = yw / 2 - yks;
	}

	public int xort(int x, int sx)
	{
		return xks2 + xdfw * (x - xkm) + sx * xdfw / fwx;
	}

	public int yort(int y, int sy)
	{
		return yks2 + ydfw * (y - ykm) + sy * ydfw / fwy;
	}

	public int xort2(int x, int xg, int sx)
	{
		return xks2 + xdfw * (x + xg - xkm) - xdfw / 2 + sx * xdfw / fwx;
	}

	public int yort2(int y, int yg, int sy)
	{
		return yks2 + ydfw * (y + yg - ykm) - ydfw / 2 + sy * ydfw / fwy;
	}

	public int xcal(int xp)
	{
		return (xp - xw / 2) * fwx * 2 * sichtx / xw + kamx + fwx / 2;
	}

	public int ycal(int yp)
	{
		return (yp - yw / 2) * fwy * 2 * sichty / yw + kamy + fwy / 2;
	}
}