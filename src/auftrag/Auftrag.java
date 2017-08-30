package auftrag;

import pfadfind.*;

public abstract class Auftrag<T extends KChara>
{
	public T chara;
	public Auftrag danach;

	public Auftrag(){}

	public Auftrag(Auftrag danach)
	{
		this.danach = danach;
	}

	public abstract Boolean los(boolean bewegt, boolean abbruch);

	public void an(T chara)
	{
		this.chara = chara;
		if(chara.auftrag != null)
			chara.auftrag.ende();
		chara.auftrag = this;
	}

	public void ende(){}
}