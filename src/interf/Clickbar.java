package interf;

import idk.*;

public abstract class Clickbar extends UITeil
{
	boolean clickbar;

	public Clickbar(int cascade, int... location)
	{
		super(cascade, location);
		clickbar = true;
	}

	public Clickbar registerClick2(int xn, int yn, int xr, int yr, int cx, int cy)
	{
		if(clickbar && cx >= xn - xr && cy >= yn - yr && cx < xn + xr && cy < yn + yr)
			return this;
		return null;
	}

	public void onFokus()
	{
		if(TA.take[202] == 2)
			onClick(2);
		else if(TA.take[201] == 2)
			onClick(1);
		else if(TA.take[203] == 2)
			onClick(3);
		if(TA.take[210] > 0)
			onScroll(-1);
		else if(TA.take[211] > 0)
			onScroll(1);
	}

	public abstract void onClick(int n);

	public void onScroll(int n){}
}