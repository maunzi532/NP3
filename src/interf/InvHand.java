package interf;

import java.awt.*;
import java.util.*;
import java.util.function.*;

public class InvHand<T> extends UITeil
{
	T hand;
	int x, y;
	Function<T, UITeil> conv;

	public InvHand(Function<T, UITeil> conv)
	{
		this.conv = conv;
	}

	@Override
	public boolean registerClick(ArrayList<Clickbar> alle, int cx, int cy, int... bloc)
	{
		x = cx;
		y = cy;
		return false;
	}

	@Override
	public void aufzeichnen(Graphics2D gd, int... bloc)
	{
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		aufzeichnen1(gd, x, y, xr2, yr2);
		for(UITeil t : in)
			t.aufzeichnen(gd, x, y, xr2, yr2);
	}
}