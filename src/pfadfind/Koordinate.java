package pfadfind;

public class Koordinate
{
	public final int x, y;

	public Koordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Koordinate that = (Koordinate) o;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode()
	{
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public String toString()
	{
		return "{" + x + "|" + y + "}";
	}

	//WUGUWUGUWUGUWUGUWUGUWUGUWUGUWUGUWUGUWUGUWUGUWUGUWUGU
	public Koordinate naechsteOption(int nummer, Koordinate t)
	{
		int ux = t.x - x;
		int uy = t.y - y;
		boolean ux1 = ux >= 0;
		boolean uy1 = uy >= 0;
		int ux2 = ux1 ? ux : -ux;
		int uy2 = uy1 ? uy : -uy;
		boolean u2xy = ux2 >= uy2;
		int lin = u2xy ? ux2 - uy2 : uy2 - ux2;
		int dia = u2xy ? uy2 : ux2;
		if(nummer < 4)
		{
			if((lin >= dia) == (nummer % 2 == 0))
			{
				if(nummer < 2)
					return new Koordinate(x, y).add(false, false, ux1, uy1, u2xy);//linM
				else
					return new Koordinate(x, y).add(false, false, ux1, uy1, u2xy).add(true, true, ux1, uy1, u2xy);//diaMIO
				//linM diaMO diaMIO linO
			}
			else
			{
				if(nummer < 2)
					return new Koordinate(x, y).add(false, false, ux1, uy1, u2xy).add(true, false, ux1, uy1, u2xy);//diaMO
				else
					return new Koordinate(x, y).add(true, false, ux1, uy1, u2xy);//linO
				//diaMO linM linO diaMIO
			}
		}
		else switch(nummer)
		{
			case 4: return new Koordinate(x, y).add(false, true, ux1, uy1, u2xy).add(true, false, ux1, uy1, u2xy);
			case 5: return new Koordinate(x, y).add(true, true, ux1, uy1, u2xy);
			case 6: return new Koordinate(x, y).add(false, true, ux1, uy1, u2xy);
			case 7: return new Koordinate(x, y).add(false, true, ux1, uy1, u2xy).add(true, true, ux1, uy1, u2xy);
			//diaIMO linIO linIM diaIMIO
		}
		throw new RuntimeException();
	}

	public Koordinate add(boolean other, boolean inverse, boolean ux1, boolean uy1, boolean u2xy)
	{
		if(u2xy != other)
			return new Koordinate(x + (ux1 != inverse ? 1 : -1), y);
		else
			return new Koordinate(x, y + (uy1 != inverse ? 1 : -1));
	}
}