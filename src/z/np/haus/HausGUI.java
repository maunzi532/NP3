package z.np.haus;

import interf.*;

public class HausGUI extends Clickbar
{
	Haus h;

	public HausGUI(Haus h)
	{
		super(0, 0, 1, 0, 1, 1, 1, 1, 1);
		this.h = h;
		in.add(new ItemArea<>(h.innenRaum().charas, e -> new MenuItem(e.toString(), true, 0), 4, 5, 0, 0, 1, 0, 1, 1, 3, 1, 2));
	}

	@Override
	public void onClick(boolean r)
	{
		weg = 1;
	}
}