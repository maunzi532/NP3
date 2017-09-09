package z.np.transfer;

import interf.*;
import java.util.function.*;
import z.np.*;

public class ItemView extends TView1<Item>
{
	ItemTransferer transferer;

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
}