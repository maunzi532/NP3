package z.np.haus;

import interf.*;
import z.np.*;

public class HausGUIChara extends Clickbar
{
	Haus h;
	NPChara wer;

	public HausGUIChara(Haus h, NPChara wer)
	{
		in.add(wer.visual);
		this.h = h;
		this.wer = wer;
	}

	@Override
	public void onClick(int n)
	{
		if(n == 3)
		{
			if(!h.versende(h.auf, wer))
				h.versende(h.auf.schichten.portal, wer);
		}
	}
}