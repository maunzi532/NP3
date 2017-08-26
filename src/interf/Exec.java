package interf;

import idk.*;
import java.util.function.*;

public class Exec
{
	public String name;
	public Consumer<Markierbar> exec;
	public BiConsumer<Markierbar, Markierbar> exec2;
	public Markierbar wer;
	public Markierbar wer2;

	public Exec(String name, Consumer<Markierbar> exec, Markierbar wer)
	{
		this.name = name;
		this.exec = exec;
		this.wer = wer;
	}

	public Exec(String name, BiConsumer<Markierbar, Markierbar> exec2, Markierbar wer, Markierbar wer2)
	{
		this.name = name;
		this.exec2 = exec2;
		this.wer = wer;
		this.wer2 = wer2;
	}

	public void los()
	{
		if(exec != null)
			exec.accept(wer);
		else
			exec2.accept(wer, wer2);
	}
}