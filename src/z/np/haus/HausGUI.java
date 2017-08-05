package z.np.haus;

import interf.*;
import java.awt.*;

public class HausGUI extends Clickbar
{
	Haus h;

	public HausGUI(Haus h)
	{
		super(0, 0, 1, 0, 1, 1, 1, 1, 1);
		this.h = h;
		in.add(new DeadArea(Color.GRAY, Color.BLACK, 0, 0, 1, 0, 1, 1, 2, 2, 3));
		in.add(new ItemArea<>(h.innenRaum().charas, e -> e.visual, 2, 5, 0, 0, 1, 0, 1, 1, 3, 1, 2));
	}

	@Override
	public void onClick(int n)
	{
		weg = 1;
	}
}