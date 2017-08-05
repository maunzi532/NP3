package z.np.haus;

import interf.*;
import java.awt.*;

public class HausGUI extends XArea
{
	Haus h;

	public HausGUI(Haus h)
	{
		super(7, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.h = h;
		in.add(new ItemArea<>(h.innenRaum().charas, e -> e.visual, 2, 3, 0, 0, 1, 0, 1, 1, 3, 1, 2));
	}
}