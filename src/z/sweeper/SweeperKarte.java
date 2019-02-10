package z.sweeper;

import java.util.*;
import karte.*;

public class SweeperKarte extends Karte<SweeperFeld>
{
	public SweeperKarte(int xw, int yw)
	{
		super(xw, yw);
		fliesen = new SweeperFeld[xw][yw];
		Random r = new Random();
		for(int x = 0; x < xw; x++)
			for(int y = 0; y < yw; y++)
				fliesen[x][y] = (x > 2 || y > 2) && r.nextInt(6) == 0 ? new SweeperFeld(x, y, this, 1) : new SweeperFeld(x, y, this, 0);
		fliesen[1][1].type = 0;
		fliesen[1][1].state = 0;
		aktualisieren(1, 1);
	}

	@Override
	public SweeperFeld ausserhalb()
	{
		return new SweeperFeld();
	}

	int[] ortNum(int x, int y)
	{
		int[] re = new int[2];
		for(int x1 = x - 1; x1 <= x + 1; x1++)
			for(int y1 = y - 1; y1 <= y + 1; y1++)
				if((x1 != x || y1 != y))
				{
					SweeperFeld f1 = fliese(x1, y1);
					if(f1 != null)
					{
						if(f1.type == 1 && f1.state == -1)
							re[0]++;
						if(f1.type == 1)
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
				SweeperFeld f1 = fliese(x2, y2);
				if(f1 != null && f1.type == 0 && f1.state >= 0)
				{
					int[] re = ortNum(x2, y2);
					f1.state = re[0];
					f1.max = re[1];
					if(f1.state == 0)
						for(int x1 = x2 - 1; x1 <= x2 + 1; x1++)
							for(int y1 = y2 - 1; y1 <= y2 + 1; y1++)
								if((x1 != x2 || y1 != y2))
								{
									SweeperFeld f2 = fliese(x1, y1);
									if(f2 != null && f2.state == -1)
										f2.aufdecken(0);
								}
				}
			}
	}
}