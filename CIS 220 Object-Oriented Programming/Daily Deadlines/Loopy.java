//********************************************************************
//  Loopy.java       Author: Dominic Scalies
//
//  Loop practice
//********************************************************************

public class Loopy
{
	public static void main(String[] args)
	{
		//while loop that prints out 0 through 10
		System.out.println("Loop 1");
		int i = 0;
		while(i <= 10)
		{
			System.out.println(i);
			i++;
		}
		//i is 10
		System.out.println();
		
		//do loop that prints out 0 through 10
		System.out.println("Loop 2");
		int j = 0;
		do
		{
			System.out.println(j);
			j++;
		}while(j <= 10);
		System.out.println();
		
		//for loop that prints out 0 through 10
		System.out.println("Loop 3");
		for(int k = 0; k <= 10; k++)
		{
			System.out.println(k);
		}
		System.out.println();
		
		//while loop that prints out the numbers 20 through 0 by 2s
		System.out.println("Loop 4");
		int l = 20;
		while(l >= 0)
		{
			System.out.println(l);
			l -= 2;
		}
		System.out.println();
		
		//do loop that prints out the numbers 20 through 0 by 2s
		System.out.println("Loop 5");
		int m = 20;
		do
		{
			System.out.println(m);
			m -= 2;
		}while(m > -1);
		System.out.println();
		
		//for loop that prints out the numbers 20 through 0 by 2s
		System.out.println("Loop 6");
		for(int n = 20; n >= 0; n -= 2)
		{
			System.out.println(n);
		}
		System.out.println();
		
		//while loop that prints out the powers of 2 from 2^0 to 2^10
		System.out.println("Loop 7");
		int o = 0;
		while(o <= 10)
		{
			System.out.println(Math.pow(2, o));
			o++;
		}
		System.out.println();
		
		//do loop that prints out the powers of 2 from 2^0 to 2^10
		System.out.println("Loop 8");
		int p = 0;
		do
		{
			System.out.println(Math.pow(2, p));
			p++;
		}while(p <= 10);
	}
}