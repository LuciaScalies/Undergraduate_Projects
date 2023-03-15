//Dominic Scalies
class SortCompare
   {
   public static void main(String[] args)
      {
	  final boolean DISPLAY = true;		 // use to turn printing on/off
	  final boolean REPEAT = false;		 // use to turn repetition on/off
	  final int REPETITIONS = 10;	     // number of data collection repetitions
      int maxSize = 10;             	 // array size
	  
      PairArray arr;                 // reference to array
	  
	  int iterations = 0;
	  long insertTime = 0, selectTime = 0, insideOutTime = 0;
	  do {
		  arr = new PairArray(maxSize);  	 // create the array

		  // populate the array with random values
		  for (int i=0; i<maxSize; i++) {
			  arr.insert((int)(Math.random()*maxSize),(int)(Math.random()*maxSize));
		  }

		  if (DISPLAY) System.out.println("Before insertion sorting:");
		  if (DISPLAY) arr.display();                 // display items

		  long start = System.nanoTime();
		  arr.insertionSort();           // insertion-sort them
		  long end = System.nanoTime();
		  insertTime += (end-start);

		  if (DISPLAY) System.out.println("After insertion sorting:");
		  if (DISPLAY) arr.display();                 // display them again

		  arr.randomize();

		  if (DISPLAY) System.out.println("Before selection sorting:");
		  if (DISPLAY) arr.display();                 // display items

		  start = System.nanoTime();
		  arr.selectionSort();           // selection-sort them
		  end = System.nanoTime();
		  selectTime += (end-start);

		  if (DISPLAY) System.out.println("After selection sorting:");
		  if (DISPLAY) arr.display();                 // display them again
		  
		  arr.randomize();

		  if (DISPLAY) System.out.println("Before inside out sorting:");
		  if (DISPLAY) arr.display();                 // display items

		  start = System.nanoTime();
		  arr.insideOutSort();           // inside out-sort them
		  end = System.nanoTime();
		  insideOutTime += (end-start);

		  if (DISPLAY) System.out.println("After inside out sorting:");
		  if (DISPLAY) arr.display();             
		  if (REPEAT) iterations++; else iterations = REPETITIONS;
	  } while (iterations < REPETITIONS);
	  
	  if (!REPEAT) iterations = 1; 
	  System.out.println("Number of items: " + maxSize);
	  System.out.println("Insertion sort runtime: " + insertTime/iterations);
	  System.out.println("Selection sort runtime: " + selectTime/iterations);
	  System.out.println("Inside Out sort runtime: " + insideOutTime/iterations);
	  
      }  // end main()
   }  // end class ObjectSortApp
