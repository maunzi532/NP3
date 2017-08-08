package z.np.haus;

import java.util.*;
import z.np.*;

public class InnenItems extends InnenTeil implements Transferer
{
	List<Item> items = new ArrayList<>();
	int itemlimit;

	public InnenItems(Haus von, int anteil)
	{
		super(InnenTeilTyp.ITEMORDNER, von);
		setAnteil(anteil);
	}

	@Override
	public void setAnteil(long anteil1)
	{
		anteil = anteil1;
		itemlimit = (int)(anteil / 2);
		if(items.size() > itemlimit)
			items = items.subList(0, itemlimit);
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