public class Quiz
{
	public static void main(String[] args)
	{
		int i = 34;
		while (i%10 != 0) 
		{
			do 
			{
				i = i*3;
			} while(i/10 < 100);
			i = i%17;
			System.out.println(i);
		}
		System.out.println(i);
	}
}