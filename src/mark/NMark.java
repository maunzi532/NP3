package mark;

import auftrag.*;
import idk.*;
import interf.*;
import java.util.*;
import pfadfind.*;

public class NMark extends Mark
{
	@Override
	public void mdk1()
	{
		if(fokus.marked != null && TA.take[203] == 2)
			fokus.marked = null;
		if(hover.marked != null)
		{
			if(fokus.marked != null && fokus.marked != hover.marked)
			{
				ArrayList<Integer> t1 = fokus.marked.tasten2(hover.marked);
				int i = 0;
				for(; i < t1.size(); i++)
					if(t1.get(i) != null && TA.take[t1.get(i)] == 2)
						break;
				if(i < t1.size() || TA.take[201] == 2)
				{
					ArrayList<Exec> optionen = fokus.marked.optionen2(hover.marked);
					if(i < t1.size())
					{
						Exec exec = optionen.get(i);
						if(exec != null)
						{
							exec.los();
							fokus.marked = null;
							return;
						}
					}
					if(TA.take[201] == 2)
					{
						optionen.removeIf(Objects::isNull);
						if(optionen.size() > 0 && fokus.marked instanceof KChara)
							new Nachfrage(hover.marked, optionen).an((KChara) fokus.marked);
						fokus.marked = null;
					}
				}
			}
			else
			{
				ArrayList<Integer> t1 = hover.marked.tasten1();
				int i = 0;
				for(; i < t1.size(); i++)
					if(t1.get(i) != null && TA.take[t1.get(i)] == 2)
						break;
				if(i < t1.size() || TA.take[201] == 2 || TA.take[202] == 2)
				{
					ArrayList<Exec> optionen = hover.marked.optionen1();
					if(i < t1.size())
					{
						Exec exec = optionen.get(i);
						if(exec != null)
						{
							exec.los();
							return;
						}
					}
					if(TA.take[201] == 2 || TA.take[202] == 2)
					{
						fokus.marked = hover.marked;
						if(TA.take[201] == 2)
						{
							optionen.removeIf(Objects::isNull);
							if(optionen.size() > 0 && hover.marked instanceof KChara)
								new Nachfrage(optionen).an((KChara) hover.marked);
						}
					}
				}
			}
		}
	}

	@Override
	public void verarbeite(){}

	@Override
	public boolean fso()
	{
		return false;
	}
}