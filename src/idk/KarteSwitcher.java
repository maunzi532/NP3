package idk;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import karte.*;

public class KarteSwitcher
{
	public static final int maxD = 20;
	public static final int abstand = 40;
	public static final int s2 = 100;
	public static final int shd = 15;

	public static ArrayList<Karte> toSwitch;
	public static int distance;
	public static int cursor;
	public static int ctarget;
	public static BufferedImage img;
	public static Graphics2D gd1;

	public static void switchto(Karte k, boolean unten)
	{
		if(toSwitch == null)
		{
			distance = 0;
			cursor = unten ? 0 : abstand;
			ctarget = unten ? 1 : 0;
			toSwitch = new ArrayList<>();
			if(!unten)
				toSwitch.add(k);
			toSwitch.add(XKarte.aktuell);
			if(unten)
				toSwitch.add(k);
		}
		else
		{
			int idx = toSwitch.indexOf(k);
			if(idx >= 0)
				ctarget = idx;
			else if(unten)
			{
				ctarget = toSwitch.size();
				toSwitch.add(k);
			}
			else
			{
				ctarget = 0;
				cursor += abstand;
				toSwitch.add(0, k);
			}
		}
	}

	public static void tick()
	{
		if(toSwitch == null)
			return;
		XKarte.aktuell = null;
		if(cursor == ctarget * abstand)
		{
			distance--;
			if(distance <= 0)
			{
				XKarte.aktuell = toSwitch.get(ctarget);
				toSwitch = null;
			}
		}
		else if(distance < maxD)
			distance++;
		else if(cursor < ctarget * abstand)
			cursor++;
		else if(cursor > ctarget * abstand)
			cursor--;
	}

	public static void aufzeichnen(Graphics2D gd, int xw, int yw)
	{
		if(toSwitch == null)
			XKarte.aufzeichnen(gd, XKarte.aktuell, true);
		else
		{
			if(img == null || img.getWidth() != xw || img.getHeight() != yw)
			{
				img = new BufferedImage(xw, yw, BufferedImage.TYPE_INT_RGB);
				gd1 = img.createGraphics();
			}
			gd.setColor(Color.BLACK);
			gd.fillRect(0, 0, xw, yw);
			int xwk = xw / 2 * (s2 - distance) / s2;
			int ywk = yw / 2 * (s2 - distance * 2) / s2;
			for(int i = 0; i < toSwitch.size(); i++)
			{
				XKarte.aufzeichnen(gd1, toSwitch.get(i), false);
				int ys = ywk * (i * abstand - cursor) / shd;
				gd.drawImage(img, xw / 2 - xwk, yw / 2 - ywk + ys, xwk * 2, ywk * 2, null);
			}
		}
	}
}