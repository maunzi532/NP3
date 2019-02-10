package z.np.transfer;

import java.util.*;
import z.np.boden.*;

public interface MaterieTransferer
{
	boolean requestMaterie(Materie mat, boolean real);

	Materie acceptMaterie(Materie mat, boolean real);

	default HashMap<MaterieTyp, Long> zeigeMaterie()
	{
		return null;
	}

	default long maxMaterie()
	{
		return 0;
	}

	default boolean versende(MaterieTransferer an, Materie mat)
	{
		mat = new Materie(mat.typ, mat.menge - an.acceptMaterie(mat, false).menge);
		return mat.menge > 0 && requestMaterie(mat, true) && acceptMaterie(mat, true).menge == 0;
	}
}