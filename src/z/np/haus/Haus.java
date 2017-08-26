package z.np.haus;

import idk.*;
import interf.*;
import java.util.*;
import pfadfind.*;
import z.np.*;
import z.np.boden.*;
import z.np.haus.innen.*;
import z.np.transfer.*;

public class Haus extends KChara<NPKarte> implements EnergieTransferer, MaterieTransferer, ItemTransferer, CharaTransferer
{
	private Bauplan plan;
	private Map<InnenTeilTyp, InnenTeil> innen = new HashMap<>();

	private ArrayList<LinkKabel> rein = new ArrayList<>();
	private ArrayList<LinkKabel> raus = new ArrayList<>();

	public Haus(int x, int y, int xg, int yg, boolean sichtbar, boolean solide, NPKarte auf)
	{
		super(x, y, xg, yg, sichtbar, solide, auf);
		innen.put(InnenTeilTyp.RAUM, new InnenRaum(this, 150));
	}

	public InnenTeil getInnenTeil(InnenTeilTyp typ)
	{
		return innen.get(typ);
	}

	@Override
	public boolean requestEnergie(long menge, boolean real)
	{
		InnenEnergie ie = InnenEnergie.von(this);
		return ie != null && ie.requestEnergie(menge, real);
	}

	@Override
	public long acceptEnergie(long menge, boolean real)
	{
		InnenEnergie ie = InnenEnergie.von(this);
		return ie != null ? ie.acceptEnergie(menge, real) : menge;
	}

	@Override
	public Long zeigeEnergie()
	{
		InnenEnergie ie = InnenEnergie.von(this);
		return ie != null ? ie.zeigeEnergie() : null;
	}

	@Override
	public long maxEnergie()
	{
		InnenEnergie ie = InnenEnergie.von(this);
		return ie != null ? ie.maxEnergie() : 0;
	}

	@Override
	public boolean requestMaterie(Materie mat, boolean real)
	{
		InnenMaterie im = InnenMaterie.von(this);
		return im != null && im.requestMaterie(mat, real);
	}

	@Override
	public Materie acceptMaterie(Materie mat, boolean real)
	{
		InnenMaterie im = InnenMaterie.von(this);
		return im != null ? im.acceptMaterie(mat, real) : mat;
	}

	@Override
	public HashMap<MaterieTyp, Long> zeigeMaterie()
	{
		InnenMaterie im = InnenMaterie.von(this);
		return im != null ? im.zeigeMaterie() : null;
	}

	@Override
	public long maxMaterie()
	{
		InnenMaterie im = InnenMaterie.von(this);
		return im != null ? im.maxMaterie() : 0;
	}

	@Override
	public boolean requestItem(Item item, boolean real)
	{
		InnenItems it = InnenItems.von(this);
		return it != null && it.requestItem(item, real);
	}

	@Override
	public boolean acceptItem(Item item, boolean real)
	{
		InnenItems it = InnenItems.von(this);
		return it != null && it.acceptItem(item, real);
	}

	@Override
	public List<Item> zeigeItems()
	{
		InnenItems it = InnenItems.von(this);
		return it != null ? it.zeigeItems() : null;
	}

	@Override
	public long maxItems()
	{
		InnenItems it = InnenItems.von(this);
		return it != null ? it.maxItems() : 0;
	}

	@Override
	public boolean requestChara(NPChara chara, boolean real)
	{
		InnenRaum ir = InnenRaum.von(this);
		return ir != null && ir.requestChara(chara, real);
	}

	@Override
	public boolean acceptChara(NPChara chara, boolean real)
	{
		InnenRaum ir = InnenRaum.von(this);
		return ir != null && ir.acceptChara(chara, real);
	}

	@Override
	public List<NPChara> zeigeCharas()
	{
		InnenRaum ir = InnenRaum.von(this);
		return ir != null ? ir.zeigeCharas() : null;
	}

	@Override
	public long maxCharas()
	{
		InnenRaum ir = InnenRaum.von(this);
		return ir != null ? ir.maxCharas() : 0;
	}

	//TODO
	@Override
	public ArrayList<Integer> tasten1()
	{
		ArrayList<Integer> re = new ArrayList<>();
		re.add(77);
		return re;
	}

	@Override
	public ArrayList<Exec> optionen1()
	{
		ArrayList<Exec> re = new ArrayList<>();
		re.add(new Exec("Info", h -> XKarte.gui.add(new UIAnschluss(0, 0, new HausGUI((Haus) h))), this));
		return re;
	}

	@Override
	public ArrayList<Integer> tasten2(Markierbar m1)
	{
		return new ArrayList<>();
	}

	@Override
	public ArrayList<Exec> optionen2(Markierbar m1)
	{
		return new ArrayList<>();
	}
}