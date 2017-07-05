package karte;

import idk.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public abstract class Feld
{
	public abstract boolean begehbar();

	public abstract Color farbe();

	public abstract File bild();

	public void zeichne(Graphics2D gd, int x, int y, int w, int h)
	{
		File bildf = bild();
		if(bildf != null)
		{
			BufferedImage bild = Lager.gibBild(bildf);
			gd.drawImage(bild, x - w / 2, y - h / 2, x + w / 2, y + h / 2,
					0, 0, bild.getWidth(), bild.getHeight(), null);
		}
		else
		{
			gd.setColor(farbe());
			gd.fillRect(x - w / 2, y - h / 2, w, h);
		}
	}

	public static Feld LEERE = new Feld()
	{
		@Override
		public boolean begehbar()
		{
			return false;
		}

		@Override
		public Color farbe()
		{
			return Color.BLACK;
		}

		@Override
		public File bild()
		{
			return null;
		}
	};

	public static Feld NFELD = new Feld()
	{
		@Override
		public boolean begehbar()
		{
			return true;
		}

		@Override
		public Color farbe()
		{
			return Color.DARK_GRAY;
		}

		@Override
		public File bild()
		{
			return null;
		}
	};
}