package z.np.transfer;

import java.util.*;
import z.np.*;

public interface CharaTransferer
{
	boolean requestChara(NPChara chara, boolean real);

	boolean acceptChara(NPChara chara, boolean real);

	default List<NPChara> zeigeCharas()
	{
		return null;
	}

	default long maxCharas()
	{
		return 0;
	}

	default boolean versende(CharaTransferer an, NPChara chara)
	{
		return requestChara(chara, false) && an.acceptChara(chara, true) && requestChara(chara, true);
	}
}