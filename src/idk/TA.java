package idk;

import java.awt.event.*;
import javax.swing.*;

public class TA
{
	public static final int MP = 200;
	public static boolean[] keys;
	public static int[] take;

	public static void einbau(JFrame fenster)
	{
		keys = new boolean[300];
		take = new int[keys.length];
		fenster.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent keyEvent)
			{
				x(true, false, keyEvent.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent keyEvent)
			{
				x(false, false, keyEvent.getKeyCode());
			}
		});
		fenster.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent mouseEvent)
			{
				x(true, true, mouseEvent.getButton());
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent)
			{
				x(false, true, mouseEvent.getButton());
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent)
			{
				x(false, true, mouseEvent.getButton());
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent)
			{
				x(false, true, mouseEvent.getButton());
			}
		});
	}

	private static void x(boolean set, boolean m, int n)
	{
		if(m)
			n += MP;
		if(n >= 0 && n < keys.length)
			keys[n] = set;
	}

	public static void bereit()
	{
		int[] t2 = new int[take.length];
		for(int i = 0; i < t2.length; i++)
		{
			if(take[i] > 0 == keys[i])
				t2[i] = keys[i] ? 1 : 0;
			else
				t2[i] = keys[i] ? 2 : -1;
		}
		take = t2;
	}
}