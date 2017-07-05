package pfadfind;

import java.util.*;

public class Pfadfind
{
	private Koordinate start;
	private boolean[][] map;
	private ArrayList<Pfad> ordner = new ArrayList<>();
	private HashMap<Koordinate, Pfad> alle = new HashMap<>();
	private int maxlen = 60;
	private Pfad ziel = null;
	private int dauer = 0;

	public Pfadfind(int sx, int sy, int abstand, int maxlen, boolean[][] map, ArrayList<Koordinate> targets)
	{
		start = new Koordinate(sx, sy);
		this.maxlen = maxlen;
		this.map = map;
		map[sx][sy] = true;
		if(abstand == 0)
			for(Koordinate target : targets)
			{
				Pfad n1 = new Pfad(target, start);
				alle.put(target, n1);
				ordneEinInit(n1);
				if(target.equals(start))
					ziel = n1;
			}
		else
			for(int ix = 0; ix < map.length; ix++)
				for(int iy = 0; iy < map[ix].length; iy++)
					if(map[ix][iy])
						for(Koordinate t1 : targets)
						{
							int dx = ix - t1.x;
							int dy = iy - t1.y;
							if(dx * dx + dy * dy <= abstand)
							{
								Koordinate target = new Koordinate(ix, iy);
								Pfad n1 = new Pfad(target, start);
								alle.put(target, n1);
								ordneEinInit(n1);
								if(target.equals(start))
									ziel = n1;
							}
						}
	}

	public ArrayList<Koordinate> los()
	{
		while(ziel == null)
		{
			if(ordner.size() <= 0)
				return new ArrayList<>();
			verarbeiteOption();
			dauer++;
		}
		ArrayList<Koordinate> re = new ArrayList<>();
		ziel.suche(alle, re, false);
		return re;
	}

	void verarbeiteOption()
	{
		Pfad f = ordner.get(ordner.size() - 1);
		Koordinate option = f.hier.naechsteOption(f.verarbeitung, start);
		f.verarbeitung++;
		if(f.verarbeitung >= 8)
			ordner.remove(ordner.size() - 1);
		if(option.x >= 0 && option.y >= 0 && option.x < map.length && option.y < map[0].length && map[option.x][option.y])
		{
			Pfad neu = new Pfad(f.hier, option, start);
			int nlen = neu.len(alle);
			if(nlen > maxlen)
				return;
			if(alle.containsKey(neu.hier))
			{
				Pfad alt = alle.get(neu.hier);
				if(nlen < alt.len(alle))
				{
					ordner.remove(alt);
					if(alt.verarbeitung < 8)
					{
						neu.verarbeitung = alt.verarbeitung;
						alle.put(neu.hier, neu);
						ordneEin(neu, nlen);
					}
				}
			}
			else
			{
				Koordinate vorh = neu.vor;
				for(int i = 1; i < 8; i++)
					if(alle.containsKey(neu.hier.naechsteOption(i, vorh)))
					{
						Pfad ersatz = new Pfad(neu, neu.hier.naechsteOption(i, vorh));
						if(ersatz.len(alle) < neu.len(alle))
							neu = ersatz;
					}
				alle.put(neu.hier, neu);
				ordneEin(neu, nlen);
			}
			if(neu.hier.equals(start))
				ziel = neu;
		}
	}

	void ordneEinInit(Pfad neu)
	{
		for(int i = 0; i < ordner.size(); i++)
			if(neu.abstandZ >= ordner.get(i).abstandZ)
			{
				ordner.add(i, neu);
				return;
			}
		ordner.add(neu);
	}

	void ordneEin(Pfad neu, int nlen)
	{
		for(int i = 0; i < ordner.size(); i++)
			if(nlen <= ordner.get(i).len(alle))
			{
				ordner.add(i, neu);
				return;
			}
		ordner.add(neu);
	}
}