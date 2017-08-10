package interf;

import java.awt.*;
import java.util.*;

public class XArea extends Clickbar
{
	int loc1, loc2;
	DeadArea deadArea;

	public XArea(int loc1, int loc2, DeadArea deadArea)
	{
		super(0, 0, 1, 0, 1, 1, 1, 1, 1);
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.deadArea = deadArea;
		in.add(deadArea);
	}

	@Override
	public void onClick(int n)
	{
		weg = 1;
	}

	@Override
	public void aufzeichnen(Graphics2D gd, int... bloc)
	{
		int xn = bloc[0];
		int yn = bloc[1];
		int r2 = Math.min(bloc[2], bloc[3]) * loc1 / loc2;
		aufzeichnen1(gd, xn, yn, bloc[2], bloc[3]);
		for(UITeil t : in)
			t.aufzeichnen(gd, xn, yn, r2, r2);
	}

	@Override
	public boolean registerClick(ArrayList<Clickbar> alle, int cx, int cy, int... bloc)
	{
		int xn = bloc[0];
		int yn = bloc[1];
		int r2 = Math.min(bloc[2], bloc[3]) * loc1 / loc2;
		for(int i = in.size() - 1; i >= 0; i--)
			if(in.get(i).registerClick(alle, cx, cy, xn, yn, r2, r2))
				return true;
		alle.add(this);
		return true;
	}
}