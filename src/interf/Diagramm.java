package interf;

import java.util.*;
import java.util.function.*;
import z.np.transfer.*;

public class Diagramm<T> extends Clickbar
{
	private static final int scrPerItem = 10;

	long code;
	HashMap<T, Long> mat;
	BiFunction<T, Diagramm<T>, Balken> conv;
	int mincols = 3;
	int maxcols = 6;
	int tcols;
	int cols;
	int maxscroll;
	int tscroll;
	int scroll;

	public Diagramm(HashMap<T, Long> mat, BiFunction<T, Diagramm<T>, Balken> conv)
	{
		this.mat = mat;
		this.conv = conv;
		positionen(true);
		shark = true;
	}

	public void positionen(boolean n)
	{
		Set<Map.Entry<T, Long>> set = mat.entrySet();
		in = new ArrayList<>();
		int len = set.size();
		tcols = Math.max(Math.min(len, maxcols), mincols);
		if(len <= tcols)
			maxscroll = len - tcols;
		else
			maxscroll = len - tcols * 2;
		if(tscroll < 0)
			tscroll = 0;
		if(tscroll > maxscroll)
			tscroll = maxscroll;
		if(n)
		{
			cols = tcols * scrPerItem;
			scroll = tscroll * scrPerItem;
		}
		Iterator<Map.Entry<T, Long>> it = set.iterator();
		for(int i = 0; it.hasNext(); i++)
		{
			Map.Entry<T, Long> en = it.next();
			Balken b = conv.apply(en.getKey(), this);
			b.wert = en.getValue();
			b.location = position(i);
			in.add(b);
			//in.add(new Balken(this, en.getKey().name, Color.RED, Color.DARK_GRAY,
					//en.getValue(), transferer.maxMaterie(), transferer.maxMaterie(), position(i)));
		}
	}

	private int[] position(int i)
	{
		return new int[]{i * 2 * scrPerItem - scroll - cols + scrPerItem, cols, 0, 1, scrPerItem, cols, 3, 4};
	}

	@Override
	public int weg()
	{
		if(scroll != tscroll * scrPerItem)
		{
			scroll += scroll < tscroll * scrPerItem ? 1 : -1;
			for(int i = 0; i < in.size(); i++)
				in.get(i).location = position(i);
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
}