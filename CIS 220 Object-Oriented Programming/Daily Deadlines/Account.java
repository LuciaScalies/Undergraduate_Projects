// Account.java		Author: AMH
// Describe an account that may store a whole number of dollars and handles
// additions and removals from the account

public class Account 
{
   private int amount;	// amount in dollars currently in account

   // initialize Account to have given amount
   public Account(int value) 
   {
      amount = value;
   }

   // add the given amount in dollars to the account total,
   // returning the new total
   public int deposit(int value) 
   {
      amount += value;
      return amount;
   }

   // remove the given amount in dollars from the account total,
   // returning the new total
   public int withdrawal(int value) 
   {
      amount -= value;
      return amount;
   }
   
   //Returns the value of amount
   public int getAmount()
   {
	   return amount;
   }

   // print out the amount of money in the account as a plain int
   public String toString() 
   {
      return Integer.toString(amount);
   }
}