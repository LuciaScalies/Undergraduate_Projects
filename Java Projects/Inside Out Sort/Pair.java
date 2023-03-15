//Dominic Scalies
class Pair
   {
   private int value1, value2;
   //-----------------------------------------------------------
   public Pair(int v1, int v2)
      {                               // constructor
		value1 = v1;
		value2 = v2;
      }
   //-----------------------------------------------------------
   public void displayPair()
      {
      System.out.println("   (" + value1 + ", " + value2 + ")");
      }
   //-----------------------------------------------------------
   public int getV1() { return value1; }
   public int getV2() { return value2; }
   //-----------------------------------------------------------
   // return true if this Pair is less than "number"
   public boolean compare(Pair number)
      { return ((value1 < number.getV1()) ||
			(value1==number.getV1() && value2 < number.getV2())); }
}