package z.np.haus;

import interf.*;
import java.awt.*;
import z.np.transfer.*;

public class HausGUI extends XArea
{
	Haus h;

	public HausGUI(Haus h)
	{
		super(7, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.h = h;
		if(h.zeigeCharas() != null)
			in.add(new CharaView(h, e -> new HausGUIChara(h, e)));
	}
}