package z.np.haus.innen;

import java.util.*;
import z.np.*;
import z.np.haus.*;
import z.np.transfer.*;

public class InnenItems extends InnenTeil implements ItemTransferer
{
	private List<Item> items = new ArrayList<>();
	private int itemlimit;

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
	public boolean requestItem(Item item, boolean real)
	{
		return real ? items.remove(item) : items.contains(item);
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

	@Override
	public List<Item> zeigeItems()
	{
		return items;
	}

	@Override
	public long maxItems()
	{
		return itemlimit;
	}

	public static InnenItems von(Haus h)
	{
		InnenTeil t = h.getInnenTeil(InnenTeilTyp.ITEMORDNER);
		return t == null ? null : (InnenItems) t;
	}
}