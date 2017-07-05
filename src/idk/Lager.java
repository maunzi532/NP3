package idk;

import java.awt.image.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import javax.imageio.*;

public class Lager
{
	static HashMap<File, String> text = new HashMap<>();
	static HashMap<File, BufferedImage> bild = new HashMap<>();

	public static String gibText(File f)
	{
		if(text.containsKey(f))
			return text.get(f);
		try
		{
			String re = new String(Files.readAllBytes(f.toPath()), Charset.forName("UTF-8"));
			text.put(f, re);
			return re;
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static BufferedImage gibBild(File f)
	{
		if(bild.containsKey(f))
			return bild.get(f);
		try
		{
			BufferedImage re = ImageIO.read(f);
			bild.put(f, re);
			return re;
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}