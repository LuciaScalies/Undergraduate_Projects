//Code edited by Dominic Scalies, provided by AMH
//Sources: Lafore pg. 555-560
import java.util.Scanner;
import java.io.*;

public class WordsRank {
	public static int size = 709970;
	public static HashTable dic = new HashTable(size);	// collection of dictionary words
	public static int best = 0;		// number of dictionary words in best segmentation found
	public static String bestSeg = null;	// best segmentation found
	
	// support method that stores all words in words.txt in dic
	private static void storeDic() {
		String hold = "";
		Link newLink;
		try {
			int wc = 0;
			Scanner filescan = new Scanner(new File("words.txt"));
			while (filescan.hasNext()) {
				hold = filescan.nextLine();
				newLink = new Link(dic.hashFunction(hash(hold)), hold);
				dic.insert(newLink);
				wc++;
			}
			System.out.println(wc + " words stored");
		} catch (IOException e) {System.out.println(e);}
	}
	
	//creates the key for hashing
	public static long hash(String input)
	{
		input = input.toLowerCase();
		long hugeNumber = 0;
		int temp = 0;
		for(int i = 0; i < input.length(); i++)
		{
			temp = (int)(input.charAt(i)) - 34;
			hugeNumber += (temp * Math.pow(40, i)) % (size);
		}
		return hugeNumber;
	}
	
	// support method that returns true if word appears in dic
	private static boolean checkDic(String word) {
		int key = dic.hashFunction(hash(word));
		if(dic.find(key))
		{
			return true;
		}
		return false;
	}
	
	public static void split(String head, String in) {	
		// head + " " + in is a segmentation 
		String segment = head + " " + in;
		// System.out.println(segment);

		// count number of dictionary words within the segmentation
		// update best and bestSeg if new best segmentation has been found
		String[] tokens = segment.split(" ");
		int count = 0;
		for (int i=0; i<tokens.length; i++) {
			if (checkDic(tokens[i]) && (tokens[i].length() != 1 || tokens[i].equals("a") || 
										tokens[i].equals("i")))
			{
					count++;
			}
		}
		if (count > best) {
			best = count;
			bestSeg = segment;
		}
		// recursive calls
		for (int i=1; i<in.length(); i++) {
			split(head+" "+in.substring(0,i), in.substring(i,in.length()));
		}	
	}

	public static void main (String[] args) {
		// get input string from user
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String input = scan.next();
		System.out.println();
		
		// preload all dictionary words into array
		storeDic();
		
		// recursively generate all segmentations of input
		long start = System.nanoTime();
		split("", input);
		long end = System.nanoTime();
		
		// print out best segmentation found including number of dictionary
		// words within that segmentation
		System.out.println("Best Segmentation: " + bestSeg + " with "
							+ best + " words");
		System.out.println("Time to Segment: " + (end-start));
	}
}

//Link class for separate chaining probe
class Link {
	private int iData; // key for the word
	private String word;
	public Link next; // next link in list

	public Link(int it, String myWord) // constructor
	{
		iData = it;
		word = myWord;
	}

	public int getKey() {
		return iData;
	}

	public void displayLink() // display this Link
	{
		System.out.print(iData + " ");
	}
	
	public void setNext(Link n)
	{
		next = n;
	}
	
	public String getWord()
	{
		return word;
	}
}

// Sorted Linked List for separate chaining probe
class SortedList {
	private Link first; // first list item

	public void SortedList() // constructor
	{
		first = null;
	}

	public void insert(Link theLink) // insert link, in order
	{
		int key = theLink.getKey();
		Link previous = null; // start at first
		Link current = first;

		while (current != null && key > current.getKey()) {
			previous = current;
			current = current.next; // go to next item
		}
		if (previous == null) // if at the beginning of the list
			first = theLink; // first --> newLink
		else // not at the beginning,
			previous.next = theLink; // prev --> newLink
		theLink.next = current; // new link --> current
	} // end insert()

	public Link find(int key) // find Link
	{
		Link current = first; // start at first
								// until end of list
		while (current != null && current.getKey() <= key) {
			if (current.getKey() == key) // check if found
				return current; // return found link
			current = current.next; // go to next item
		}
		return null; // not found
	}

	// to be deleted later, useful for testing purposes
	public void displayList() {
		System.out.print("List (first-->last: ");
		Link current = first; // start at the beginning of the list
		while (current != null) // until the end of the list,
		{
			current.displayLink(); // print data
			current = current.next; // move to next Link
		}
		System.out.println("");
	}
}

class HashTable {
	private SortedList[] hashArray; // array of lists
	private int arraySize;

	public HashTable(int size) // constructor
	{
		arraySize = size;
		hashArray = new SortedList[arraySize]; // create array
		for (int j = 0; j < arraySize; j++) // fill array
		{
			hashArray[j] = new SortedList(); // with lists
		}
	}

	public void insert(Link theLink)
	{
		hashArray[theLink.getKey()].insert(theLink);
	}
	
	public boolean find(int key)
	{
		if(hashArray[key] != null)
		{
				return true;
		}
		else
		{
			return false;
		}
	}
	public int getHashArrayLength()
	{
		return hashArray.length;
	}
	
	public void displayTable() {
		for (int j = 0; j < arraySize; j++) // for each cell
		{
			System.out.print(j + ", "); // display cell number
			hashArray[j].displayList(); // display list
		}
	}

	// the important method
	public int hashFunction(long key) // hash function
	{
		return (int) (key % arraySize);
	}
}