package interf;

import idk.*;
import java.util.*;

public abstract class Clickbar extends UITeil
{
	public boolean clickbar;
	public boolean shark;

	public Clickbar()
	{
		clickbar = true;
	}

	public Clickbar(int cascade, int... location)
	{
		super(cascade, location);
		clickbar = true;
	}

	@Override
	public boolean registerClick(ArrayList<Clickbar> alle, int cx, int cy, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		if(shark && registerClick2(cx, cy, xn, yn, xr2, yr2))
			alle.add(this);
		for(int i = in.size() - 1; i >= 0; i--)
			if(in.get(i).registerClick(alle, cx, cy, xn, yn, xr2, yr2))
				return true;
		if(!shark && registerClick2(cx, cy, xn, yn, xr2, yr2))
		{
			alle.add(this);
			return true;
		}
		return false;
	}

	public boolean registerClick2(int cx, int cy, int xn, int yn, int xr, int yr)
	{
		return clickbar && cx >= xn - xr && cy >= yn - yr && cx < xn + xr && cy < yn + yr;
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