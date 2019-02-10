package z.np.transfer;

import interf.*;
import java.awt.*;

public class EnergieView extends TabTeil
{
	EnergieTransferer transferer;
	Balken balken;

	public EnergieView(EnergieTransferer transferer)
	{
		this.transferer = transferer;
		balken = new Balken(this, "Energie", Color.YELLOW, Color.BLACK, e(transferer.zeigeEnergie(), 0),
				e(transferer.maxEnergie(), 0), e(transferer.maxEnergie(), 1), 0, 1, 0, 1, 1, 3, 1, 3);
	}

	private long e(Long l, long z)
	{
		if(l == null)
			return z;
		return Math.max(l, z);
	}

	@Override
	public int weg()
	{
		balken.wert = e(transferer.zeigeEnergie(), 0);
		return super.weg();
	}

	@Override
	public String tabname()
	{
		return "Energie";
	}
}