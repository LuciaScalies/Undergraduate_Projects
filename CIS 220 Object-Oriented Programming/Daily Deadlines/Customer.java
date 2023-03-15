/*
   Customer.java		Author: AMH, LO, LJS
   Describe an customer that may store a whole number of dollars in both a 
   checking and a savings Account. Calls methods from Account.java to handle
   additions and removals from the accounts
*/

public class Customer 
{
   private Account checking;	// checking Account for this Customer
   private Account savings;		// savings Account for this Customer
   private int checkDepSum;		// sum of checking deposits
   private int savDepSum;		// sum of savings deposits
   private int numCheckDeposits;// number of checking deposits
   private int numSavDeposits; 	// number of savings deposits
   private int checkWithSum;	// sum of checking withdrawals
   private int savWithSum;		// sum of savings withdrawals
   private int numCheckWithdrawals; // number of checking withdrawals
   private int numSavWithdrawals; 	// number of savings withdrawals

   //Constructor
   public Customer(int inChecking, int inSavings)
   {
	   checking = new Account(inChecking);
	   savings = new Account(inSavings);
	   checkDepSum = 0;
	   savDepSum = 0;
	   numCheckDeposits = 0;
	   numSavDeposits = 0;
	   checkWithSum = 0;
	   savWithSum = 0;
	   numCheckWithdrawals = 0;
	   numSavWithdrawals = 0;
   }
   
   //Deposits int num dollars in the checking account
   public int checkingDeposit(int num)
   {
	   checkDepSum += num;
	   numCheckDeposits++;
	   return checking.deposit(num);
   }
   
   //Deposits int num dollars in the savings account
   public int savingsDeposit(int num)
   {
	   savDepSum += num;
	   numSavDeposits++;
	   return savings.deposit(num);
   }
   
   //Withdraws int num dollars from the checking account
   public int checkingWithdrawal(int num)
   {
	   checkWithSum += num;
	   numCheckWithdrawals++;
	   return checking.withdrawal(num);
   }
   
   //Withdraws int num dollars from the savings account
   public int savingsWithdrawal(int num)
   {
	   savWithSum += num;
	   numSavWithdrawals++;
	   return savings.withdrawal(num);
   }
   
   //Calculates and returns the total funds available to the Customer
   public int getTotalFunds()
   {
	   int total = checking.getAmount() + savings.getAmount();
	   return total;
   }
   
   //Returns the customer's average checking deposit
   public int getAvgCheckDep()
   {
	   int avg = (int)((double)checkDepSum/numCheckDeposits);
	   return avg;
   }
   
   //Returns the customer's average savings deposit
   public int getAvgSavDep()
   {
	   int avg = (int)((double)savDepSum/numSavDeposits);
	   return avg;
   }
   
   //Returns the customer's average checking withdrawal
   public int getAvgCheckWith()
   {
	   int avg = (int)((double)checkWithSum/numCheckWithdrawals);
	   return avg;
   }
   
   //Returns the customer's average savings withdrawal
   public int getAvgSavWith()
   {
	   int avg = (int)((double)savWithSum/numSavWithdrawals);
	   return avg;
   }
   
   //Defines the toString() method
   public String toString()
   {
	   return "Checking: "+checking+" Savings: "+savings;
   }
}