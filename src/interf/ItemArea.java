package interf;

import java.util.*;
import java.util.function.*;

public class ItemArea<T> extends UITeil
{
	ArrayList<T> list;
	int cols, rows;
	Function<T, UITeil> conv;

	public ItemArea(ArrayList<T> list, Function<T, UITeil> conv, int cols, int rows, int cascade, int... location)
	{
		super(cascade, location);
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
			UITeil teil = conv.apply(list.get(i));
			int xo = i % cols;
			int yo = i / cols;
			teil.location = new int[]{1 - cols + xo * 2, cols, 1 - rows + yo * 2, rows, 1, cols, 1, rows};
			in.add(teil);
		}
	}
}