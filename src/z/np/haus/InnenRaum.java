package z.np.haus;

import java.util.*;
import z.np.*;

public class InnenRaum extends InnenTeil
{
	ArrayList<NPChara> charas = new ArrayList<>();
	int charaLimit;

	public InnenRaum(Haus von, int anteil)
	{
		super(von, anteil);
		charaLimit = anteil / 10;
	}

	public boolean voll()
	{
		return charas.size() >= charaLimit;
	}
}