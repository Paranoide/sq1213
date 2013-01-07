package main;

public class Main
{
	public static void main(String[] args)
	{
		boolean a, b, c, d;

		a = true;
		b = true;
		c = false;
		d = false;
		
		if (a || b)
		{
			System.out.println("a == true");
		}
		
		while ((a && b) || (c && d))
		{
			System.out.println("(a && b) || (c && d)");
			a = false;
		}
		
		for (int t = 0; t < 100; t++)
		{
			if (t > 10)
			{
				System.out.println("t > 10");
			}
			
			if (t == 100)
			{
				System.out.println("t == 100");
			}
		}
	}
}
