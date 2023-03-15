//Dominic Scalies
//Sources: Lafore's section on Linked Lists pg. 185-217
import java.lang.Runnable;
import java.util.Scanner;
import java.io.*;


class Student {
	private String name;
	private int year;
	private double gpa;
	private int priority;
	private final int CURRYEAR = 2017;
	
	public Student(String n, int y, double g) {
		name = n;
		year = y;
		gpa = g;
		priority = calcPriority();
	}
	
	private int calcPriority() {
		return (int)(((year-2017)*50)-(gpa*10));
	}
	
	public int getPriority() {return priority;}
	
	public String toString() {
		return name + " (" + year + "), " + gpa;
	}
}

class Link{
	private Student studentData; 	//data item
	private Link next;	//the next link in the list
	
	public Link(Student s) //constructor
	{
		studentData = s;
	}
	
	//studentData setter method
	public void setStudent(Student s)
	{
		studentData = s;
	}
	
	//studentData getter method
	public Student getStudent()
	{
		return studentData;
	}
	
	//next setter method
	public void setNext(Link l)
	{
		next = l;
	}
	
	//next getter method
	public Link getNext()
	{
		return next;
	}
}

//creates the LinkList class
class LinkList{
	private Link first; //refers to the first Link in a list
	
	public LinkList() //constructor
	{
		first = null;
	}
	
	public boolean isEmpty() //checks if the LinkList is Empty and returns the result
	{
		return (first == null);
	}
	
	public void insertFirst(Student s) //inserts the first student
	{
		Link newLink = new Link(s);
		newLink.setNext(first);
		first = newLink;
	}
	
	public Link deleteFirst() //deletes the first item in the queue
	{
		Link temp = first;
		first = first.getNext();
		return temp;
	}
	
	public void insert(Student key) //insert, in order with priority
	{
		Link newLink = new Link(key); //make new Link
		Link previous = null; //start at first
		Link current = first; 
		//until the end of the data list or key > current
		while(current != null && key.getPriority() > current.getStudent().getPriority())
		{
			previous = current;
			current = current.getNext();
		}
		if(previous == null) //at beginning of list
			first = newLink; //first --> newLink
		else                                //not at beginning
		{
			previous.setNext(newLink);  //old prev --> newLink
			newLink.setNext(current);   //newLink --> old current
		}
	}
	
	public Student getFirst() //first Student getter method
	{ return first.getStudent();	}
}

class StudentQ {
	// ascending order priority queue returning smallest key first
	//private int maxSize; //used only in the array implementation
	//private Student[] queArray;
	private LinkList queLink; //replaces functionality of above code with a LinkedList
	//private int nItems; //part of array implementation

	public StudentQ(int s)          // constructor
	{
		//maxSize = s; //part of array implementation
		//queArray = new Student[maxSize]; //part of array implementation
		//nItems = 0;
		queLink = new LinkList();
	}

	public void insert(Student item)    // insert item
	{
		queLink.insert(item);
		//part of the array implementation
		/*int j; 

		if(nItems==0)                  // if no items,
		queArray[nItems++] = item;    // insert at 0
		else
		{
			for(j=nItems-1; j>=0; j--)         // start at end,
			{
				if( item.getPriority() > queArray[j].getPriority() )      // if new item larger,
					queArray[j+1] = queArray[j]; // shift upward
				else                          // if smaller,
					break;                     // done shifting
			}  // end for
			queArray[j+1] = item;            // insert it
			nItems++;
		}  // end else (nItems > 0)
	*/	
	}  // end insert()

	public Student remove()             // remove minimum item
	{ return queLink.deleteFirst().getStudent(); } //removes first Link
	//{ return queArray[--nItems]; }  //part of array implementation

	public Student peekMin()            // peek at minimum item
	{ return queLink.getFirst(); } //peeks at the minimum item
	//{ return queArray[nItems-1]; }  //part of array implementation

	public boolean isEmpty()         // true if queue is empty
	{ return queLink.isEmpty(); }
	//{ return (nItems==0); }       //part of array implementation

	//public boolean isFull()          // true if queue is full
	//{ return (nItems == maxSize); }  //part of array implementation

}

class StudentQApp {
	private static StudentQ students;
	private static long time = 200000000;
	private static boolean inserted = false;
	
	private static class ReaderThread implements Runnable {

		public ReaderThread() { }

		public void run() {
			try {
				Scanner filescan = new Scanner(new File("students.txt"));
				while (filescan.hasNext()) {
					String line = filescan.nextLine();
					Scanner linescan = new Scanner(line);
					linescan.useDelimiter("\t");
					String name = linescan.next();
					int year = linescan.nextInt();
					double gpa = linescan.nextDouble();
					Student newStudent = new Student(name, year, gpa);
					System.out.println("\tInserting " + name + "....");
					students.insert(newStudent);
					
					long start = System.nanoTime();
					long end;
					do {
						end = System.nanoTime();
					} while (end-start < time);
				}
			} catch (IOException e) {System.out.println(e);};
			
			inserted = true;
			System.out.println("All students entered into priority queue");
		}
	}

	public static void main(String[] args) {

		// Create priority queue of sufficient size to store 200 items at once
		students = new StudentQ(200);

		// use a thread to automate populating the priority queue from the
		// provided text file
		long startTime = System.nanoTime();
		Thread reader = new Thread(new ReaderThread());
		reader.start();
		
		int registered = 0;
		// simulate selecting the first 50 students to let in to register
		while (registered < 50) {
			if (!students.isEmpty()) {
				Student nextStudent = students.remove();
				System.out.println("Registering now: " + nextStudent);
				registered++;
			}
			
			// set pause between removals to be two and a half times as long as
			// pause between insertions
			long start = System.nanoTime();
			long end;
			do {
				end = System.nanoTime();
			} while (end-start < time*2.5);
		}
		
		// enter loop that waits until all of the names from the file have
		// been inserted; after it terminates report back which student is
		// at the top of the priority queue
		do {
			long start = System.nanoTime();
			long end;
			do {
				System.out.print("");
				end = System.nanoTime();
			} while (end-start < time);
		} while (!inserted);
		long endTime = System.nanoTime();
		System.out.println("First student to register tomorrow: " + students.peekMin());
		System.out.println(endTime - startTime);
	}  // end main()
}  // end class StudentQApp