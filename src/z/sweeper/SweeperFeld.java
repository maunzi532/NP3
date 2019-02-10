package z.sweeper;

import java.awt.*;
import java.io.*;
import karte.*;

public class SweeperFeld extends Feld
{
	public static final Font SWEEPER2 = new Font("Ubuntu Mono", Font.PLAIN, 50);

	int x, y;
	SweeperKarte sk;
	int type;
	int state;
	int max;

	public SweeperFeld(int x, int y, SweeperKarte sk, int type)
	{
		super(x, y);
		this.sk = sk;
		this.type = type;
		state = -1;
	}

	public SweeperFeld()
	{
		state = -3;
	}

	@Override
	public void zeichne(Graphics2D gd, int x, int y, int w, int h)
	{
		gd.setFont(SWEEPER2);
		gd.setColor(farbe());
		gd.fillRect(x - w / 2, y - h / 2, w, h);
		if(state >= 0)
		{
			if(state > 0)
			{
				gd.setColor(Color.BLACK);
				gd.drawString(String.valueOf(state), x - w / 2 + 2, y + h / 2 - 2);
				if(state != max)
				{
					gd.setColor(Color.GRAY);
					gd.drawString(" /" + max, x - w / 2 + 2, y + h / 2 - 2);
				}
			}
			else if(max > 0)
			{
				gd.setColor(Color.BLUE);
				gd.drawString(String.valueOf(max), x - w / 2 + 2, y + h / 2 - 2);
			}
		}
	}

	public boolean aufdecken(int weg)
	{
		if(state == -1)
		{
			if(type == 0)
			{
				state = 0;
				sk.aktualisieren(x, y);
				if(weg == 1)
					return true;
			}
			if(type == 1)
			{
				state = -2;
				sk.aktualisieren(x, y);
				if(weg == 0)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean begehbar(KObjekt wer)
	{
		return state >= 0;
	}

	@Override
	public Color farbe()
	{
		switch(state)
		{
			case -1:
				return Color.DARK_GRAY;
			case -2:
				return Color.RED;
			case -3:
				return Color.BLACK;
			default:
				return Color.LIGHT_GRAY;
		}
	}

	@Override
	public File bild()
	{
		return null;
	}
}