package z.np.transfer;

import interf.*;
import java.util.*;
import java.util.function.*;

public abstract class TView1<T> extends UITeil
{
	long code;
	ItemArea<T> area;

	protected void init(List<T> t, Function<T, UITeil> conv)
	{
		if(t == null)
			t = new ArrayList<>();
		code = t.hashCode();
		area = new ItemArea<>(t, conv, 2, 3, 0, 0, 1, 0, 1, 1, 3, 1, 2);
		in.add(area);
	}

	protected void weg1(List<T> t1)
	{
		if(t1 != null && code != t1.hashCode())
		{
			code = t1.hashCode();
			area.list = t1;
			area.positionen();
		}
	}
}