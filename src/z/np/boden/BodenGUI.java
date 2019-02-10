package z.np.boden;

import interf.*;
import java.awt.*;
import java.util.*;
import z.np.transfer.*;

public class BodenGUI extends TabXArea
{
	Bodenteil b;

	public BodenGUI(Bodenteil b)
	{
		super(6, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.b = b;
		ArrayList<TabTeil> views1 = new ArrayList<>();
		if(b.zeigeMaterie() != null)
			views1.add(new MaterieView(b));
		init(views1.toArray(new TabTeil[views1.size()]));
	}
}