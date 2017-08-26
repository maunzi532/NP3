package interf;

import java.util.*;

public class VariableOption extends MultiOption
{
	List<Exec> options;

	public VariableOption(List<Exec> options, int lockon, int cascade, int... location)
	{
		super(options.stream().map(e -> new MenuItem(e.name, false)).toArray(MenuItem[]::new), lockon, cascade, location);
		this.options = options;
	}

	@Override
	public int weg()
	{
		for(int i = 0; i < in.size() && weg == 0; i++)
			if(in.get(i).weg() != 0)
			{
				if(in.get(i).weg() > 0)
					options.get(i).los();
				weg = 1;
				return weg;
			}
		return weg;
	}
}