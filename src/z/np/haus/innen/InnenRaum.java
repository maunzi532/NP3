package z.np.haus.innen;

import java.util.*;
import z.np.*;
import z.np.haus.*;
import z.np.transfer.*;

public class InnenRaum extends InnenTeil implements CharaTransferer
{
	public ArrayList<NPChara> charas;
	public long charalimit;

	public InnenRaum(Haus von, int anteil)
	{
		super(InnenTeilTyp.RAUM, von);
		setAnteil(anteil);
	}

	@Override
	public void setAnteil(long anteil1)
	{
		anteil = anteil1;
		charalimit = anteil / 10;
		if(charas == null)
			charas = new ArrayList<>();
		while(charas.size() > charalimit)
			raus(charas.size() - 1);
	}

	public void raus(int num)
	{
		NPChara chara = charas.remove(num);
		if(!von.auf.acceptChara(chara, true))
			von.auf.portal.acceptChara(chara, true);
	}

	@Override
	public boolean requestChara(NPChara chara, boolean real)
	{
		if(!charas.contains(chara))
			return false;
		if(real)
		{
			chara.inHaus = null;
			charas.remove(chara);
		}
		return true;
	}

	@Override
	public boolean acceptChara(NPChara chara, boolean real)
	{
		if(charas.size() >= charalimit)
			return false;
		if(real)
		{
			chara.inHaus = von;
			charas.add(chara);
		}
		return true;
	}

	@Override
	public List<NPChara> zeigeCharas()
	{
		return charas;
	}

	@Override
	public long maxCharas()
	{
		return charalimit;
	}

	public static InnenRaum von(Haus h)
	{
		InnenTeil t = h.getInnenTeil(InnenTeilTyp.RAUM);
		return t == null ? null : (InnenRaum) t;
	}
}