package idk;

import java.awt.*;
import karte.*;

public class Marker
{
	public Marker(Color farbe)
	{
		this.farbe = farbe;
	}

	public Karte auf;
	public KObjekt marked;
	public int x, y;
	public Color farbe;
	public boolean existent;

	public Karte getAuf()
	{
		if(marked != null)
			return marked.auf;
		return auf;
	}
}