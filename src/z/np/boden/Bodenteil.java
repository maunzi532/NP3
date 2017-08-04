package z.np.boden;

import java.awt.*;
import java.io.*;
import karte.*;

public class Bodenteil extends Feld
{
	FluidG fluidG;

	public Bodenteil(){}

	public Bodenteil(int x, int y)
	{
		super(x, y);
	}

	public void tick()
	{
		if(fluidG != null)
			fluidG = fluidG.replace();
	}

	@Override
	public boolean begehbar(KObjekt wer)
	{
		return fluidG == null || fluidG.tiefe() <= 4;
	}

	@Override
	public Color farbe()
	{
		return null;
	}

	@Override
	public File bild()
	{
		return null;
	}
}