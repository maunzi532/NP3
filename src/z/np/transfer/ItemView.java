package z.np.transfer;

import interf.*;
import java.util.function.*;
import karte.*;
import z.np.*;

public class ItemView extends TView1<Item>
{
	ItemTransferer transferer;

	public ItemView(ItemTransferer transferer)
	{
		this.transferer = transferer;
		init(transferer.zeigeItems(), e -> new KVis(1, 1));
	}

	public ItemView(ItemTransferer transferer, Function<Item, UITeil> conv)
	{
		this.transferer = transferer;
		init(transferer.zeigeItems(), conv);
	}

	@Override
	public int weg()
	{
		weg1(transferer.zeigeItems());
		return super.weg();
	}

	@Override
	public String tabname()
	{
		return "Items";
	}
}