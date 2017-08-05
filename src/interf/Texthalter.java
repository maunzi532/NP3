package interf;

import java.awt.*;

public class Texthalter extends UITeil
{
	public Texthalter(int... location)
	{
		super(0, location);
	}

	public void addText(String text)
	{
		in.add(new Textbox(text, 0, 1, 0, 1, 1, 1, 1, 3));
	}

	public void aufzeichnen(Graphics2D gd, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		aufzeichnen1(gd, xn, yn, xr2, yr2);
		if(in.size() > 0)
			in.get(0).aufzeichnen(gd, xn, yn, xr2, yr2);
	}

	public Clickbar registerClick(int cx, int cy, int... bloc)
	{
		int xn = bloc[0] + bloc[2] * location[0] / location[1];
		int yn = bloc[1] + bloc[3] * location[2] / location[3];
		int xr2 = bloc[2] * location[4] / location[5];
		int yr2 = bloc[3] * location[6] / location[7];
		if(in.size() > 0)
		{
			Clickbar cl = in.get(0).registerClick(cx, cy, xn, yn, xr2, yr2);
			if(cl != null)
				return cl;
		}
		return registerClick2(cx, cy, xn, yn, xr2, yr2);
	}

	public int weg()
	{
		if(in.size() > 0 && in.get(0).weg() > 0)
			in.remove(0);
		return weg;
	}
}