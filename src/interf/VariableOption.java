package interf;

import java.util.*;

public class VariableOption extends MultiOption
{
	ArrayList<Exec> options;

	public VariableOption(ArrayList<Exec> options, int lockon, int cascade, int... location)
	{
		super(options.stream().map(Exec::name).toArray(e -> new String[options.size()]), lockon, cascade, location);
		this.options = options;
	}

	public int weg()
	{
		for(int i = 0; i < in.size() && weg == 0; i++)
			if(in.get(i).weg() > 0)
			{
				options.get(i).exec.accept(null);
				weg = 1;
			}
		return weg;
	}
}