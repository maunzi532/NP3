package karte;

import idk.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import mark.*;

public abstract class Feld implements Markierbar
{
	public int x, y;

	public Feld(){}

	public Feld(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public abstract boolean begehbar(KObjekt wer);

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

	@Override
	public KOrt ort()
	{
		return new KOrt(x, y, 1, 1);
	}

	@Override
	public Karte auf(Karte w)
	{
		return w;
	}

	@Override
	public boolean weg()
	{
		return false;
	}

	public static Feld LEERE = new Feld()
	{
		@Override
		public boolean weg()
		{
			return true;
		}

		@Override
		public boolean begehbar(KObjekt wer)
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
		public boolean begehbar(KObjekt wer)
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