package z.sweeper;

import idk.*;
import java.util.*;
import karte.*;

public class SweeperKarte extends Karte
{
	public SweeperKarte(int xw, int yw)
	{
		super(xw, yw);
		Random r = new Random();
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
				fliesen[x][y] = (x > 2 || y > 2) && r.nextInt(6) == 0 ? new SweeperFeld(x, y, this, 1) : new SweeperFeld(x, y, this, 0);
		SweeperFeld f = (SweeperFeld) fliesen[1][1];
		f.type = 0;
		f.state = 0;
		aktualisieren(1, 1);
	}

	@Override
	public void tick(Mark mark)
	{
		if(mark.hover.existent && fliese(mark.hover.x, mark.hover.y) instanceof SweeperFeld)
		{
			SweeperFeld sf = (SweeperFeld) fliese(mark.hover.x, mark.hover.y);
			if(TA.take[201] == 2)
				sf.aufdecken(0);
			if(TA.take[203] == 2)
				sf.aufdecken(1);
		}
		super.tick(mark);
	}

	int[] ortNum(int x, int y)
	{
		int[] re = new int[2];
		for(int x1 = x - 1; x1 <= x + 1; x1++)
			for(int y1 = y - 1; y1 <= y + 1; y1++)
				if((x1 != x || y1 != y))
				{
					Feld f1 = fliese(x1, y1);
					if(f1 instanceof SweeperFeld)
					{
						SweeperFeld f2 = (SweeperFeld) f1;
						if(f2.type == 1 && f2.state == -1)
							re[0]++;
						if(f2.type == 1)
							re[1]++;
					}
				}
		return re;
	}

	void aktualisieren(int x, int y)
	{
		for(int x2 = x - 1; x2 <= x + 1; x2++)
			for(int y2 = y - 1; y2 <= y + 1; y2++)
			{
				Feld f1 = fliese(x2, y2);
				if(f1 instanceof SweeperFeld)
				{
					SweeperFeld f2 = (SweeperFeld) f1;
					if(f2.type == 0 && f2.state >= 0)
					{
						int[] re = ortNum(x2, y2);
						f2.state = re[0];
						f2.max = re[1];
						if(f2.state == 0)
							for(int x1 = x2 - 1; x1 <= x2 + 1; x1++)
								for(int y1 = y2 - 1; y1 <= y2 + 1; y1++)
									if((x1 != x2 || y1 != y2))
									{
										Feld f3 = fliese(x1, y1);
										if(f3 instanceof SweeperFeld)
										{
											SweeperFeld f4 = (SweeperFeld) f3;
											if(f4.state == -1)
												f4.aufdecken(0);
										}
									}
					}
				}
			}
	}
}