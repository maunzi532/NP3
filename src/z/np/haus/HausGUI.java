package z.np.haus;

import interf.*;
import java.awt.*;

public class HausGUI extends XArea
{
	Haus h;
	ItemArea charaArea;

	public HausGUI(Haus h)
	{
		super(7, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.h = h;
		charaArea = new ItemArea<>(InnenRaum.von(h).charas, e -> new HausGUIChara(h, e), 2, 3, 0, 0, 1, 0, 1, 1, 3, 1, 2);
		in.add(charaArea);
	}

	@Override
	public int weg()
	{
		if(InnenRaum.von(h).update)
			charaArea.positionen();
		for(InnenTeil it : h.innen.values())
			it.update = false;
		return super.weg();
	}
}