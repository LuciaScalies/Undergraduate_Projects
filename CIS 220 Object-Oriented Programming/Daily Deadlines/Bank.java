// Bank.java		Author: AMH
// Implement a bank program with a customer creating an account and making
// deposits and withdrawals

public class Bank 
{
   public static void main (String[] args) 
   {
      // Create a new bank customer Alice who has $100 in her
      // checking account and $500 in her savings account
      Customer alice = new Customer(100,500);
      // print out the status of both of Alice's bank accounts
      System.out.println(alice);
	  System.out.println("Total funds: " + alice.getTotalFunds());
      
      // deposit $20 in Alice's checking account
      alice.checkingDeposit(20);
      // print out the status of both of Alice's bank accounts	  
      System.out.println(alice);
      System.out.println("Total funds: " + alice.getTotalFunds());
	  
	  // withdraw $100 from Alice's savings account
      alice.savingsWithdrawal(100);
      // print out the status of both of Alice's bank accounts	  
      System.out.println(alice);
	  System.out.println("Total funds: " + alice.getTotalFunds());
	  
	  // withdraw $15 from Alice's checking account
	  alice.checkingWithdrawal(15);
	  // deposit $15 in Alice's savings account
	  alice.savingsDeposit(15);
      // print out the status of both of Alice's bank accounts	  
      System.out.println(alice);
	  System.out.println("Total funds: " + alice.getTotalFunds());
	  //prints out the average deposit and withdrawal sizes for each account
	  System.out.println("Average checking deposit: " + alice.getAvgCheckDep());
	  System.out.println("Average savings deposit: " + alice.getAvgSavDep());
	  System.out.println("Average checking withdrawal: " + 
		alice.getAvgCheckWith());
	  System.out.println("Average savings withdrawal: " + 
		alice.getAvgSavWith());
   }
}