package z.np.transfer;

import interf.*;
import java.awt.*;
import z.np.boden.*;

public class MaterieView extends TabTeil
{
	MaterieTransferer transferer;
	Diagramm<MaterieTyp> diagramm;

	public MaterieView(MaterieTransferer transferer)
	{
		this.transferer = transferer;
		diagramm = new Diagramm<>(transferer.zeigeMaterie(), (e, d) -> new Balken(d, e.name, Color.RED, Color.DARK_GRAY,
				0, transferer.maxMaterie(), transferer.maxMaterie()));
		in.add(diagramm);
	}

	@Override
	public String tabname()
	{
		return "Materie";
	}
}