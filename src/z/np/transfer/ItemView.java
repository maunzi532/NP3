package z.np.transfer;

import interf.*;
import java.util.*;
import java.util.function.*;
import z.np.*;

public class ItemView extends UITeil
{
	ItemTransferer transferer;
	long code;
	ItemArea<Item> area;

	public ItemView(ItemTransferer transferer, Function<Item, UITeil> conv)
	{
		this.transferer = transferer;
		List<Item> items = transferer.zeigeItems();
		if(items == null)
			items = new ArrayList<>();
		code = items.hashCode();
		area = new ItemArea<>(items, conv, 2, 3, 0, 0, 1, 0, 1, 1, 3, 1, 2);
		in.add(area);
	}

	@Override
	public int weg()
	{
		List<Item> items1 = transferer.zeigeItems();
		if(items1 != null && code != items1.hashCode())
		{
			code = items1.hashCode();
			area.list = items1;
			area.positionen();
		}
		return super.weg();
	}
}