//Lucia Scalies

import java.util.Scanner;
import java.util.Random;

public class AddressLJS
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Random gen = new Random();
		String email, afterAt, range, domain, user, userSwap;
		int lastPeriod, atSymbol, periodAfter, rangeLength, domainLength;
		int userLength, ranDomMax, overallMax, ranDomMin, overallMin, swapLimit;
		int swapStart;
		
		System.out.println("Enter your e-mail address: ");
		email = input.nextLine();
		input.close();
		lastPeriod = email.lastIndexOf('.');
		atSymbol = email.indexOf('@');
		afterAt = email.substring(atSymbol + 1);
		periodAfter = afterAt.indexOf('.');
		
		range = email.substring(lastPeriod + 1);
		domain = afterAt.substring(0, periodAfter);
		user = email.substring(0, atSymbol);
		System.out.println("Range: " + range);
		System.out.println("Domain: " + domain);
		System.out.println("User: " + user);
		
		rangeLength = range.length();
		domainLength = domain.length();
		userLength = user.length();
		ranDomMax = Math.max(rangeLength, domainLength);
		overallMax = Math.max(ranDomMax, userLength);
		ranDomMin = Math.min(rangeLength, domainLength);
		overallMin = Math.min(ranDomMin, userLength);
		System.out.println("Ratio of shortest to longest: " + overallMin + ":"
			+ overallMax);
		
		swapLimit = userLength - 1;
		swapStart = gen.nextInt(swapLimit);
		userSwap = user.substring(0, swapStart) + 
			user.substring(swapStart+2, swapStart+3) + 
			user.substring(swapStart+1, userLength - 1) + 
			user.substring(swapStart, swapStart+1);
		System.out.println("Scrambled User: " + userSwap);
	}
}