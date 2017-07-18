package z.dec;

import java.util.*;

public class Initiierung
{
	ArrayList<DecChara> deinTeam = new ArrayList<>();
	ArrayList<DecChara> cpuTeam = new ArrayList<>();

	public Initiierung(DecChara deines, DecChara cpu, boolean nurdiese)
	{
		if(nurdiese)
		{
			deinTeam.add(deines);
			cpuTeam.add(cpu);
		}
	}
}