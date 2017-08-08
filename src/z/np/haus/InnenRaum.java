package z.np.haus;

import java.util.*;
import z.np.*;
import z.np.boden.*;

public class InnenRaum extends InnenTeil
{
	ArrayList<NPChara> charas;
	long charaLimit;

	public InnenRaum(Haus von, int anteil)
	{
		super(InnenTeilTyp.RAUM, von);
		setAnteil(anteil);
	}

	public boolean voll()
	{
		return charas.size() >= charaLimit;
	}

	public boolean rein(NPChara chara)
	{
		if(voll())
			return false;
		chara.auf.rem.add(chara);
		charas.add(chara);
		return true;
	}

	@Override
	public void setAnteil(long anteil1)
	{
		anteil = anteil1;
		charaLimit = anteil / 10;
		if(charas == null)
			charas = new ArrayList<>();
		while(charas.size() > charaLimit)
			raus(charas.size() - 1);
	}

	public void raus(NPChara chara)
	{
		raus(charas.indexOf(chara));
	}

	public void raus(int num)
	{
		System.out.println(num);
		if(num < 0 || num >= charas.size())
			return;
		NPChara chara = charas.remove(num);
		if(chara.auf == von.auf && von.auf2 != null)
			von.auf2.addCharaInArea(chara, von);
		else
			((NPKarte)von.auf).addCharaInArea(chara, von);
	}

	public static InnenRaum von(Haus h)
	{
		InnenTeil t = h.innen.get(InnenTeilTyp.RAUM);
		return (InnenRaum) t;
	}
}