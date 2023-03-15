import java.util.Scanner;

public class Exercise
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a temperature: ");
		int temp = scan.nextInt();
		scan.nextLine();
		
		//go running if the temperature entered is between 25 and 80 degrees
		//inclusive
		//If it is colder than 25 degrees they are told to go to the gym
		//If it is warmer than 80 degrees they are told to go swimming
		if((temp >= 25) && (temp <= 80))
		{
			System.out.println("Go running.");
		}
		else
		{
			if(temp < 25)
			{
				System.out.println("Go to the gym.");
			}
			else
			{
				System.out.println("Go swimming.");
			}
		}
	}
}