package z.np.haus;

import java.util.*;
import z.np.*;

public class InnenRaum extends InnenTeil
{
	ArrayList<NPChara> charas = new ArrayList<>();
	int charaLimit;

	public InnenRaum(Haus von, int anteil)
	{
		super(InnenTeilTyp.RAUM, von, anteil);
		charaLimit = anteil / 10;
	}

	public boolean voll()
	{
		return charas.size() >= charaLimit;
	}

	public static InnenRaum von(Haus h)
	{
		InnenTeil t = h.innen.get(InnenTeilTyp.RAUM);
		return (InnenRaum) t;
	}
}