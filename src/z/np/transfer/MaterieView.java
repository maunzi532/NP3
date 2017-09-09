package z.np.transfer;

import interf.*;
import java.awt.*;
import java.util.*;
import z.np.boden.*;

public class MaterieView extends UITeil
{
	private static final int scrPerItem = 10;

	MaterieTransferer transferer;
	long code;
	HashMap<MaterieTyp, Long> mat;
	int mincols = 3;
	int maxcols = 6;
	int tcols;
	int cols;
	int maxscroll;
	int tscroll;
	int scroll;

	public MaterieView(MaterieTransferer transferer)
	{
		this.transferer = transferer;
		mat = transferer.zeigeMaterie();
		positionen(true);
	}

	public void positionen(boolean n)
	{
		Set<Map.Entry<MaterieTyp, Long>> set = mat.entrySet();
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
		Iterator<Map.Entry<MaterieTyp, Long>> it = set.iterator();
		for(int i = 0; it.hasNext(); i++)
		{
			Map.Entry<MaterieTyp, Long> en = it.next();
			in.add(new Balken(this, en.getKey().name, Color.RED, Color.DARK_GRAY,
					en.getValue(), transferer.maxMaterie(), transferer.maxMaterie(), position(i)));
		}
	}

	private int[] position(int i)
	{
		return new int[]{i * 2 * scrPerItem - scroll - cols + scrPerItem, cols, 0, 1, scrPerItem, cols, 3, 4};
	}

	/*private void position(int i, List<UITeil> in1)
	{
		in1.get(i).location = new int[]{1 - cols, cols, (1 - rows + i * 2) * scrPerItem - scroll * 2, rows * scrPerItem, 1, cols, 1, rows};
	}*/
}