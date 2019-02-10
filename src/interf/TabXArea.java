package interf;

import java.awt.*;
import java.util.*;

public class TabXArea extends Clickbar
{
	int loc1, loc2;
	DeadArea deadArea;
	MenuItem[] tabs;
	TabTeil[] views;
	int tab;

	public TabXArea(int loc1, int loc2, DeadArea deadArea)
	{
		super(0, 0, 1, 0, 1, 1, 1, 1, 1);
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.deadArea = deadArea;
		in.add(deadArea);
	}

	public void init(TabTeil... views)
	{
		this.views = views;
		tabs = new MenuItem[views.length];
		for(int i = 0; i < views.length; i++)
		{
			tabs[i] = new MenuItem(views[i].tabname(), true, 3,
					1 - views.length + i * 2, views.length, -11, 10, 1, views.length, 1, 10);
			in.add(tabs[i]);
		}
		if(views.length > 0)
		{
			tabs[tab].focused = true;
			in.add(views[tab]);
		}
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

	@Override
	public int weg()
	{
		for(int i = 0; i < views.length; i++)
			if(tabs[i].weg > 0)
			{
				tabs[i].weg = 0;
				tabs[tab].focused = false;
				in.remove(views[tab]);
				tab = i;
				tabs[tab].focused = true;
				in.add(views[tab]);
			}
		if(views.length > tab)
			views[tab].weg();
		return weg;
	}
}