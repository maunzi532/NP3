package z.np.transfer;

import interf.*;
import java.util.*;
import java.util.function.*;
import z.np.*;

public class CharaView extends UITeil
{
	CharaTransferer transferer;
	long code;
	ItemArea<NPChara> area;

	public CharaView(CharaTransferer transferer, Function<NPChara, UITeil> conv)
	{
		this.transferer = transferer;
		List<NPChara> charas = transferer.zeigeCharas();
		if(charas == null)
			charas = new ArrayList<>();
		area = new ItemArea<>(charas, conv, 2, 3, 0, 0, 1, 0, 1, 1, 3, 1, 2);
		in.add(area);
	}

	@Override
	public int weg()
	{
		List<NPChara> charas1 = transferer.zeigeCharas();
		if(charas1 != null && code != charas1.hashCode())
		{
			code = charas1.hashCode();
			area.list = charas1;
			area.positionen();
		}
		return super.weg();
	}
}