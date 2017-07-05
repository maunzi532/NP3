package z.dec;

import idk.*;
import interf.*;

public class DecMark extends Mark
{
	public DecMark()
	{
		XKarte.gui.add(new UIAnschluss(1, 1, new MultiOption(new String[]{"W1", "W2"}, 0, 2, 0, 1, 0, 1, 1, 5, 1, 20))
		{
			public void code(int re)
			{
				System.out.println(re);
			}
		});
	}

	@Override
	public void verarbeite()
	{

	}
}