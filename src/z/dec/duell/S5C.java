package z.dec.duell;

public class S5C
{
	S4I von;
	int hp;
	int hpbalken;
	int toughness;
	int sharpness;
	int coolness;
	int resist;
	int armor;
	int psychic;
	int deception;

	public S5C(S4I von, int anzG)
	{
		this.von = von;
		toughness = von.toughness;
		sharpness = von.sharpness;
		coolness = von.coolness;
		resist = von.resist;
		armor = von.armor;
		psychic = von.psychic;
		deception = von.deception;
		hpbalken = 1260 * (3 + anzG);
		hp = hpbalken * 4;
	}

	int hpbalkenanz()
	{
		return hp / hpbalken;
	}
}