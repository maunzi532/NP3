package z.np.transfer;

import java.util.*;
import z.np.*;

public interface ItemTransferer
{
	boolean requestItem(Item item, boolean real);

	boolean acceptItem(Item item, boolean real);

	default List<Item> zeigeItems()
	{
		return null;
	}

	default long maxItems()
	{
		return 0;
	}

	default boolean versende(ItemTransferer an, Item item)
	{
		return requestItem(item, false) && an.acceptItem(item, true) && requestItem(item, true);
	}
}