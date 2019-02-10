package auftrag;

import karte.*;
import pfadfind.*;

public class FolgeZiel extends Auftrag<KChara>
{
	public KObjekt ziel;
	public Koordinate lZiel;
	public Karte lKarte;
	public int abstand;

	public FolgeZiel(KObjekt ziel, int abstand)
	{
		this.ziel = ziel;
		this.abstand = abstand;
	}

	public FolgeZiel(KObjekt ziel, int abstand, Auftrag danach)
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
			if(!ziel.existent)
				return false;
			if(chara.abstand(ziel) <= abstand)
				return true;
			else
			{
				Koordinate lz2 = new Koordinate(ziel.x, ziel.y);
				if(abbruch || ziel.auf != lKarte || !lz2.equals(lZiel))
				{
					lZiel = lz2;
					lKarte = ziel.auf;
					chara.pfad(ziel, abstand);
					if(chara.pfad.isEmpty())
						return false;
				}
				if(chara.pfad.size() > 0)
					chara.declaregtf((Koordinate) chara.pfad.remove(0), 20);
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