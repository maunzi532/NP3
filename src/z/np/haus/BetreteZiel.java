package z.np.haus;

import auftrag.*;
import z.np.*;

public class BetreteZiel extends Auftrag
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
		{
			if(!ziel.existent || chara.abstand(ziel) > 2)
				return false;
			NPChara c = (NPChara) chara;
			c.auf.chg.add(c);
			InnenRaum.von(ziel).charas.add(c);
		}
		return null;
	}
}