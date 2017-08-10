package interf;

import java.awt.*;
import java.util.*;
import java.util.function.*;

public class ItemArea<T> extends Clickbar
{
	private static final int scrPerItem = 20;

	ArrayList<T> list;
	int cols, rows;
	Function<T, UITeil> conv;
	int maxscroll;
	int tscroll;
	int scroll;

	public ItemArea(ArrayList<T> list, Function<T, UITeil> conv, int cols, int rows, int cascade, int... location)
	{
		super(cascade, location);
		shark = true;
		this.list = list;
		this.cols = cols;
		this.rows = rows;
		this.conv = conv;
		positionen();
	}

	public void positionen()
	{
		in = new ArrayList<>();
		for(int i = 0; i < list.size(); i++)
		{
			in.add(conv.apply(list.get(i)));
			position(i);
		}
		if(cols * rows < list.size())
			maxscroll = (list.size() - 1) / cols + 1 - rows;
		else
			maxscroll = 0;
		if(tscroll > maxscroll)
			tscroll = maxscroll;
	}

	private void position(int i)
	{
		int xo = i % cols;
		int yo = i / cols;
		in.get(i).location = new int[]{1 - cols + xo * 2, cols, (1 - rows + yo * 2) * scrPerItem - scroll * 2, rows * scrPerItem, 1, cols, 1, rows};
	}

	@Override
	public int weg()
	{
		if(scroll != tscroll * scrPerItem)
		{
			scroll += scroll < tscroll * scrPerItem ? 1 : -1;
			for(int i = 0; i < in.size(); i++)
				position(i);
		}
		return super.weg();
	}

	@Override
	public void onClick(int n){}

	@Override
	public void onScroll(int n)
	{
		if(n < 0 && tscroll < maxscroll)
			tscroll++;
		if(n > 0 && tscroll > 0)
			tscroll--;
	}

	@Override
	public void aufzeichnen(Graphics2D gd, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		aufzeichnen1(gd, xn, yn, xr2, yr2);
		for(UITeil t : in)
			t.aufzeichnen(gd, xn, yn, xr2, yr2);
		aufzeichnen2(gd, xn, yn, xr2, yr2);
		gd.setClip(null);
	}

	@Override
	public void aufzeichnen1(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		gd.setClip(xn - xr, yn - yr, xr * 2, yr * 2);
	}

	public void aufzeichnen2(Graphics2D gd, int xn, int yn, int xr, int yr)
	{
		if(scroll > 0)
		{
			int sca = 255;
			if(scroll < scrPerItem)
				sca = 255 * scroll / scrPerItem;
			gd.setPaint(new GradientPaint(xn, yn - yr, new Color(0, 0, 0, sca), xn, yn - yr + yr / rows, new Color(0x0, true)));
			gd.fillRect(xn - xr, yn - yr, xr * 2, yr / rows);
		}
		if(scroll < maxscroll * scrPerItem)
		{
			int sca = 255;
			if(scroll > maxscroll * scrPerItem - scrPerItem)
				sca = 255 * (maxscroll * scrPerItem - scroll) / scrPerItem;
			gd.setPaint(new GradientPaint(xn, yn + yr, new Color(0, 0, 0, sca), xn, yn + yr - yr / rows, new Color(0x0, true)));
			gd.fillRect(xn - xr, yn + yr - yr / rows, xr * 2, yr / rows);
		}
	}
}