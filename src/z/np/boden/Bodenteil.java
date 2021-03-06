package z.np.boden;

import idk.*;
import interf.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import karte.*;
import mark.*;
import z.np.*;
import z.np.transfer.*;

public class Bodenteil extends Feld implements MaterieTransferer, ItemTransferer
{
	public static final long fluidKapazitaet = 16;

	public FluidG fluidG;
	Item bItem;
	Materie wand;

	public Bodenteil(){}

	public Bodenteil(int x, int y)
	{
		super(x, y);
	}

	public boolean blockiert()
	{
		return wand != null;
	}

	public void tick()
	{
		if(fluidG != null)
			fluidG = fluidG.replace();
		if(bItem instanceof Schacht)
		{

		}
	}

	@Override
	public boolean begehbar(KObjekt wer)
	{
		return fluidG == null || fluidG.tiefe() <= 4;
	}

	@Override
	public Color farbe()
	{
		if(fluidG != null)
		{
			if(fluidG.tiefe() > 0)
				return new Color(80, 0, 120);
			return new Color(20, 20, 60);
		}
		return new Color(50, 50, 0);
	}

	@Override
	public File bild()
	{
		return null;
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		return fluidG != null && fluidG.requestMaterie(mat, real);
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		return fluidG != null ? fluidG.acceptMaterie(mat, real) : mat;
	}

	@Override
	public HashMap<MaterieTyp, Long> zeigeMaterie()
	{
		return fluidG != null ? fluidG.zeigeMaterie() : null;
	}

	@Override
	public long maxMaterie()
	{
		return fluidG != null ? fluidG.maxMaterie() : 0;
	}

	@Override
	public boolean requestItem(Item item, boolean real)
	{
		if(item != bItem)
			return false;
		if(real)
			bItem = null;
		return true;
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		return false;
	}

	@Override
	public List<Item> zeigeItems()
	{
		return Collections.singletonList(bItem);
	}

	@Override
	public long maxItems()
	{
		return 1;
	}

	@Override
	public ArrayList<int[]> tasten1()
	{
		ArrayList<int[]> re = new ArrayList<>();
		re.add(new int[]{77, 203});
		return re;
	}

	@Override
	public ArrayList<Exec> optionen1()
	{
		ArrayList<Exec> re = new ArrayList<>();
		re.add(new Exec("Info", b -> XKarte.gui.add(new UIAnschluss(0, 0, new BodenGUI(this))), this));
		return re;
	}

	@Override
	public ArrayList<int[]> tasten2(Markierbar m1)
	{
		return new ArrayList<>();
	}

	@Override
	public ArrayList<Exec> optionen2(Markierbar m1)
	{
		return new ArrayList<>();
	}
}