package auftrag;

import pfadfind.*;

public class GeheZuZiel extends Auftrag<KChara>
{
	public Koordinate ziel;
	public int abstand;

	public GeheZuZiel(Koordinate ziel, int abstand)
	{
		this.ziel = ziel;
		this.abstand = abstand;
	}

	public GeheZuZiel(Koordinate ziel, int abstand, Auftrag danach)
	{
		super(danach);
		this.ziel = ziel;
		this.abstand = abstand;
	}

	@Override
	public Boolean los(boolean bewegt, boolean abbruch)
	{
		if(!bewegt)
		{
			if((abstand == 0 && chara.x == ziel.x && chara.y == ziel.y) ||
					(abstand > 0 && chara.abstand(ziel.x, ziel.y) <= abstand))
				return true;
			else
			{
				if(abbruch || chara.pfad.isEmpty())
				{
					chara.pfad(ziel, abstand);
					if(chara.pfad.isEmpty())
						return false;
				}
				if(chara.pfad.size() > 0)
					chara.declaregtf(chara.pfad.remove(0), 20);
			}
		}
		return null;
	}

	@Override
	public void an(KChara chara)
	{
		super.an(chara);
		chara.pfad.clear();
	}
}