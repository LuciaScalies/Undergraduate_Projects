//Dominic Scalies with debugging help and stupidity check by Maximilian 
class PairArray
   {
   private Pair[] a;               	// ref to array a
   private int nElems;              // number of data items
//--------------------------------------------------------------
   public PairArray(int max)         // constructor
      {
      a = new Pair[max];               	 // create the array
      nElems = 0;                        // no items yet
      }
//--------------------------------------------------------------
   public void insert(int v1, int v2)
      {
      a[nElems] = new Pair(v1, v2);
      nElems++;                          // increment size
      }
//--------------------------------------------------------------
   public void display()             // displays array contents
      {
      for(int j=0; j<nElems; j++)       // for each element,
         a[j].displayPair();          // display it
      }
//--------------------------------------------------------------
   private void swap(int one, int two)
      {
      Pair temp = a[one];
      a[one] = a[two];
      a[two] = temp;
      }
//--------------------------------------------------------------
   public void insertionSort()
      {
      int in, out;

      for(out=1; out<nElems; out++)
         {
         Pair temp = a[out];       // out is dividing line
         in = out;                   // start shifting at out

         while(in>0 &&               // until smaller one found,
               temp.compare(a[in-1]))
            {
            a[in] = a[in-1];         // shift item to the right
            --in;                    // go left one position
            }
         a[in] = temp;               // insert marked item
         }  // end for
      }  // end insertionSort()
//--------------------------------------------------------------
   public void selectionSort()
      {
      int out, in, min;
	  Pair temp;

      for(out=0; out<nElems-1; out++)   // outer loop
         {
         min = out;                     // minimum
         for(in=out+1; in<nElems; in++) // inner loop
            if(a[in].compare(a[min]) )         // if min greater,
                min = in;               // we have a new min
		 temp = a[out];
		 a[out] = a[min];
		 a[min] = temp;
         }  // end for(out)
      }  // end selectionSort()
//--------------------------------------------------------------
   public void insideOutSort()
   {
	   int low = 0; //lowest unsorted item
	   int high = nElems-1; //highest unsorted item
	   int in; //to be used in the pseudo-insertion sort
	   for(int i = 1; low<high; i++)
	   {
		   if(a[i].compare(a[low])) //true if a[i] < a[low]		   
		   {
			   Pair temp;
			   temp = a[i];       
			   in = i;                   

			   while(in>0 &&               // until smaller one found,
			    		   temp.compare(a[in-1]))
			   {
			    	   a[in] = a[in-1];         // shift item to the right
			    	   --in;                    // go left one position
	    	   }
			   a[in] = temp;               // insert marked item
			   low++;
		   }
		   else if(a[high].compare(a[i]))
		   {
			   swap(i, high-1);
			   Pair temp;
			   temp = a[high-1];      
			   in = high-1;                  
			   while(in<nElems-1 &&               
					  a[in+1].compare(temp))
			   {
				   a[in] = a[in+1];         // shift item to the right
				   ++in;                    // go left one position
			   }
			   a[in] = temp;
			   high--;
			   i--;
		   }
		   else
		   {
			   low++;
		   }
	   }
   }
//--------------------------------------------------------------
	public void randomize() {
		for (int i=0; i<nElems*2; i++) {
			int index1 = (int)(Math.random()*nElems);
			int index2 = (int)(Math.random()*nElems);
			swap(index1, index2);
		}
	}
//--------------------------------------------------------------
   }  // end class ArrayInOb