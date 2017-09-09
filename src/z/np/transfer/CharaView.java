package z.np.transfer;

import interf.*;
import java.util.function.*;
import z.np.*;

public class CharaView extends TView1<NPChara>
{
	CharaTransferer transferer;

	public CharaView(CharaTransferer transferer, Function<NPChara, UITeil> conv)
	{
		this.transferer = transferer;
		init(transferer.zeigeCharas(), conv);
	}

	@Override
	public int weg()
	{
		weg1(transferer.zeigeCharas());
		return super.weg();
	}

	@Override
	public String tabname()
	{
		return "Charas";
	}
}