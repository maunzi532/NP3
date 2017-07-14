package interf;

public class MultiOption extends UITeil
{
	int lockon;

	public MultiOption(String[] options, int lockon, int cascade, int... location)
	{
		super(cascade, location);
		this.lockon = lockon;
		int xsh = lockon >= 0 ? (lockon % 2 == 0 ? -1 : 1) : 0;
		int ysh = lockon >= 0 ? (lockon / 2 == 0 ? -1 : 1) : 0;
		for(int i = 0; i < options.length; i++)
		{
			int ysh2;
			if(ysh == 0)
				ysh2 = i * 2 - options.length + 1;
			else if(ysh > 0)
				ysh2 = 1 + i * 2;
			else
				ysh2 = (i - options.length + 1) * 2 - 1;
			int[] location2 = new int[]{xsh, 1, ysh2, 1, 1, 1, 1, 1};
			in.add(new MenuItem(options[i], false, 2, location2));
		}
	}

	public int weg()
	{
		for(int i = 0; i < in.size(); i++)
			if(in.get(i).weg() > 0)
				weg = i + 1;
		return weg;
	}
}