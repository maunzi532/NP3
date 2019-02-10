package z.np.boden;

public class Schacht extends BodenItem
{
	int level;

	@Override
	public boolean platzierbar(boolean infg, int tiefe)
	{
		return !infg && tiefe <= level;
	}
}