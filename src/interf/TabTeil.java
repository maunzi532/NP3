package interf;

public abstract class TabTeil extends UITeil
{
	public TabTeil(int cascade, int... location)
	{
		super(cascade, location);
	}

	public TabTeil()
	{
		super();
	}

	public abstract String tabname();
}