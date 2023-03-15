// import statements needed
import java.util.Scanner;
import java.io.*;

public class FileReading
{
	public static void main(String[] args)
	{
		String sentence = "";
		// a try-catch block is required for sections of code reading from
		// a file to catch IOExceptions
		try 
		{
			// set up the Scanner to read from a file input.txt
			Scanner filescan = new Scanner(new File("input.txt"));
			
			// read in all of the words in the file, constructing a sentence
			// out of them all in uppercase
			
			
			while (filescan.hasNext())
			{
				String word = filescan.next();
				word = word.toUpperCase();
				sentence += word + " ";
			}
			
			/*
			// read in first word
			word1 = filescan.next();
			// read in second word
			word2 = filescan.next();
			
			// capitalize each word
			word1 = word1.toUpperCase();
			word2 = word2.toUpperCase();
			*/
			
			
		} catch (IOException e) {System.out.println(e);};
		// output the results
		System.out.println(sentence);
	}
}