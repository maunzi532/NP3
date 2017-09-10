package z.np.chara;

import interf.*;
import java.awt.*;
import java.util.*;
import z.np.transfer.*;

public class CharaGUI extends TabXArea
{
	NPChara chara;

	public CharaGUI(NPChara chara)
	{
		super(6, 8, new DeadArea(Color.GRAY, Color.BLACK));
		this.chara = chara;
		ArrayList<TabTeil> views1 = new ArrayList<>();
		views1.add(new ItemView(chara));
		views1.add(new MaterieView(chara));
		views1.add(new EnergieView(chara));
		init(views1.toArray(new TabTeil[views1.size()]));
	}
}