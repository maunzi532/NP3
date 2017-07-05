package z.np;

import java.util.*;

public interface Einheit
{
	int MATERIEFORMEN = 0;

	default long leben()
	{
		return 0;
	}

	default long lebenLimit()
	{
		return 0;
	}

	default long energie()
	{
		return 0;
	}

	default long energieLimit()
	{
		return 0;
	}

	default long energieMaxTransfer()
	{
		return 0;
	}

	default long[] materie()
	{
		return new long[MATERIEFORMEN];
	}

	default long materieLimit()
	{
		return 0;
	}

	default long materieMaxTransfer()
	{
		return 0;
	}

	default ArrayList<Item> items()
	{
		return new ArrayList<>();
	}

	default long itemLimit()
	{
		return 0;
	}

	default long itemMaxTransfer()
	{
		return 0;
	}
}