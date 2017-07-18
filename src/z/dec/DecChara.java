package z.dec;

import interf.*;
import java.util.*;

public class DecChara
{
	public ArrayList<Exec> execs(DecChara fuer)
	{
		ArrayList<Exec> execs = new ArrayList<>();
		execs.add(new Exec("Ende", e -> {}));
		return execs;
	}
}