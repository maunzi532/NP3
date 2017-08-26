package mark;

import idk.*;
import interf.*;
import java.util.*;
import karte.*;

public interface Markierbar
{
	KOrt ort();

	Karte auf(Karte w);

	boolean weg();

	default ArrayList<int[]> tasten1()
	{
		return new ArrayList<>();
	}

	default ArrayList<Exec> optionen1()
	{
		return new ArrayList<>();
	}

	default ArrayList<int[]> tasten2(Markierbar m1)
	{
		return new ArrayList<>();
	}

	default ArrayList<Exec> optionen2(Markierbar m1)
	{
		return new ArrayList<>();
	}
}