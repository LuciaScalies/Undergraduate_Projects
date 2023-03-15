import java.util.Random;

public class NegativeTwoToFive
{
	public static void main(String[] args)
	{
		Random gen = new Random();
		int value;
		value = gen.nextInt(8) - 2;
		System.out.println(value);
	}
}