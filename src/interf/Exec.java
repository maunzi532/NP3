package interf;

import java.util.function.*;

public class Exec<T>
{
	public String name;
	public Consumer<T> exec;
	public T wer;

	public Exec(String name, Consumer<T> exec, T wer)
	{
		this.name = name;
		this.exec = exec;
		this.wer = wer;
	}

	public void los()
	{
		exec.accept(wer);
	}

	public String name()
	{
		return name;
	}
}