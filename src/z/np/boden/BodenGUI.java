package z.np.boden;

import interf.*;
import java.awt.*;
import z.np.transfer.*;

public class BodenGUI extends XArea
{
	Bodenteil b;

	public BodenGUI(Bodenteil b)
	{
		super(7, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.b = b;
		if(b.zeigeMaterie() != null)
			in.add(new MaterieView(b));
	}
}