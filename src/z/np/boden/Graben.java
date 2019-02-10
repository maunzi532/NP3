package z.np.boden;

import auftrag.*;
import z.np.chara.*;

public class Graben extends Auftrag<NPChara>
{
	Bodenteil teil;
	int ticks;

	public Graben(Bodenteil teil)
	{
		this.teil = teil;
	}

	public Graben(Bodenteil teil, Auftrag danach)
	{
		super(danach);
		this.teil = teil;
	}

	@Override
	public Boolean los(boolean bewegt, boolean abbruch)
	{
		if(!bewegt)
		{
			if(abbruch || teil.fluidG != null || chara.abstand(teil.x, teil.y) > 2)
				return false;
			ticks++;
			if(ticks >= 20)
			{
				teil.fluidG = new FluidG(teil);
				return true;
			}
		}
		return null;
	}
}