package z.np.haus;

import auftrag.*;
import z.np.*;

public class BetreteZiel extends Auftrag<NPChara>
{
	public Haus ziel;

	public BetreteZiel(Haus ziel)
	{
		this.ziel = ziel;
	}

	public BetreteZiel(Haus ziel, Auftrag danach)
	{
		super(danach);
		this.ziel = ziel;
	}

	@Override
	public Boolean los(boolean bewegt, boolean abbruch)
	{
		if(!bewegt)
			return ziel.existent && chara.abstand(ziel) <= 2 && chara.auf.versende(ziel, chara);
		return null;
	}
}