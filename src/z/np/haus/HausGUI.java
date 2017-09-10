package z.np.haus;

import interf.*;
import java.awt.*;
import java.util.*;
import z.np.transfer.*;

public class HausGUI extends TabXArea
{
	Haus h;

	public HausGUI(Haus h)
	{
		super(6, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.h = h;
		ArrayList<TabTeil> views1 = new ArrayList<>();
		if(h.zeigeCharas() != null)
			views1.add(new CharaView(h, e -> new HausGUIChara(h, e)));
		if(h.zeigeItems() != null)
			views1.add(new ItemView(h));
		if(h.zeigeMaterie() != null)
			views1.add(new MaterieView(h));
		if(h.zeigeEnergie() != null)
			views1.add(new EnergieView(h));
		init(views1.toArray(new TabTeil[views1.size()]));
	}
}