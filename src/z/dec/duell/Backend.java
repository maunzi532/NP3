package z.dec.duell;

import java.util.*;
import z.dec.*;

public class Backend
{
	public ArrayList<DecChara> team1 = new ArrayList<>();
	public ArrayList<DecChara> team2 = new ArrayList<>();
	public Controller d1;
	public Controller d2;

	public Backend(DecChara team1, DecChara team2, boolean duell)
	{
		d1 = new DeinController(this);
		d2 = new Controller1();
		if(duell)
		{
			this.team1.add(team1);
			this.team2.add(team2);
		}
	}

	public void tick()
	{
		d1.sendDataIfReady();
		d2.sendDataIfReady();
	}
}