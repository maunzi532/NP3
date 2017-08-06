package z.np.haus;

import java.util.*;
import z.np.*;

public class InnenItems extends InnenTeil implements Transferer
{
	ArrayList<Item> items = new ArrayList<>();
	int itemlimit;

	public InnenItems(Haus von, int anteil)
	{
		super(InnenTeilTyp.ITEMORDNER, von, anteil);
		itemlimit = anteil / 2;
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		if(items.size() >= itemlimit)
			return false;
		if(real)
			items.add(item);
		return true;
	}

	public static InnenItems von(Haus h)
	{
		InnenTeil t = h.innen.get(InnenTeilTyp.ITEMORDNER);
		return t == null ? null : (InnenItems) t;
	}
}