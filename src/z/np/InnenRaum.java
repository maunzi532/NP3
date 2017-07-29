package z.np;

import java.util.*;

public class InnenRaum extends InnenTeil
{
	ArrayList<NPChara> charas = new ArrayList<>();
	int charaLimit;

	public InnenRaum(Haus von, int anteil, int charaLimit)
	{
		super(von, anteil);
		this.charaLimit = charaLimit;
	}

	public boolean voll()
	{
		return charas.size() >= charaLimit;
	}
}