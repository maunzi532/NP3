package interf;

import java.util.function.*;

public class Exec
{
	public String name;
	public Consumer exec;

	public Exec(String name, Consumer exec)
	{
		this.name = name;
		this.exec = exec;
	}

	public String name()
	{
		return name;
	}
}