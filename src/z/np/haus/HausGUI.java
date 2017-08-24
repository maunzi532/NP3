package z.np.haus;

import interf.*;
import java.awt.*;
import java.util.List;
import z.np.*;

public class HausGUI extends XArea
{
	Haus h;
	ItemArea charaArea;

	public HausGUI(Haus h)
	{
		super(7, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.h = h;
		List<NPChara> charas = h.zeigeCharas();
		if(charas != null)
		{
			charaArea = new ItemArea<>(charas, e -> new HausGUIChara(h, e), 2, 3, 0, 0, 1, 0, 1, 1, 3, 1, 2);
			in.add(charaArea);
		}
	}

	@Override
	public int weg()
	{
		if(charaArea != null)
			charaArea.positionen();
		return super.weg();
	}
}