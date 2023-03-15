import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*; //Access to the Stack<E> class

//Dominic Scalies
//////////////////////////////////////////////////////////////////////////////
class BracketChecker{
	private String input;				//input string
	//-------------------------------------------------------------------------
	public BracketChecker(String in)	// constructor
	{ input = in;  }
	//-------------------------------------------------------------------------
	public void check()
	{
		int stackSize = input.length(); //get max stack size
		Stack<String> theStack = new Stack<String>();	//Pass the type String 
														//to the Stack<E> class, and call
														//call its constructor
		
		for(int j = 0; j < input.length(); j++) //get chars in turn
		{
			String ch = input.substring(j, j+1);	//Changed variable to a String, and made the method call match
			switch(ch)
			{
			case "{" : 					//Changed from single to double quotes for String checks to work
			case "[" :					//Changed from single to double quotes for String checks to work
			case "(" :					//Changed from single to double quotes for String checks to work
			case "<" :					//Added case to handle <, using the same format as the above cases
				theStack.push(ch);		//push them
				break;
				
										//closing symbols
			case "}" :					//Changed from single to double quotes for String checks to work
			case "]" :					//Changed from single to double quotes for String checks to work
			case ")" :					//Changed from single to double quotes for String checks to work
			case ">" :					//Added case to handle >, using the same format as the above cases
				if(!theStack.isEmpty() ) 	//if stack not empty
				{
					String chx = theStack.pop();	// pop and check, char to String variable type matching
					//for each ch== check, changed the == operator to a .equals() method call and the single
					//quotes to double quotes for syntax purposes
					if( (ch.equals("}")) &&  !(chx.equals("{")) ||
						(ch.equals("]")) &&  !(chx.equals("[")) ||
						(ch.equals(")")) &&  !(chx.equals("(")) || //added || operator to accommodate new condition
						(ch.equals(">")) &&  !(chx.equals("<")))   //added the conditional checks for > and < using the previous syntax
						System.out.println("Error: "+ch+" at " +j);
				}
				else
					System.out.println("Error: " +ch+ " at "+j);
				break;
			} //end switch
		} // end for
		//at this point, all characters have been processed
		if( !theStack.isEmpty())
			System.out.println("Error: missing right delimmiter");
	} // end check
}
public class BracketsApp {
	public static void main(String[] args) throws IOException
	{
		String input;
		while(true)
		{
			System.out.print("Enter string containing delimiteds: ");
			System.out.flush();
			input = getString(); //read a string from kbd
			if(input.equals("")) //quit if [Enter]
				break;
								 //make a BracketChecker
			BracketChecker theChecker = new BracketChecker(input);
			theChecker.check(); //check brackets
		} //end while
	} // end main
//-----------------------------------------------------------------------------
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
//-----------------------------------------------------------------------------
} //end class BracketsApp