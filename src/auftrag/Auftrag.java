package auftrag;

import pfadfind.*;

public abstract class Auftrag
{
	public KChara chara;
	public Auftrag danach;

	public Auftrag(){}

	public Auftrag(Auftrag danach)
	{
		this.danach = danach;
	}

	public abstract Boolean los(boolean bewegt, boolean abbruch);

	public void an(KChara chara)
	{
		this.chara = chara;
		chara.auftrag = this;
	}
}