// UAccount.java		Author: AMH, Dominic Scalies
// Describes an unclaimed bank account object
// Assumes that if only one name is given for the account it is treated as
// the last name

import java.text.NumberFormat;

public class UAccount {
	private String last;	// last name or business name on the account
	private String first; 	// first name on account, if exists
	private double balance;	// amount of funds in the unclaimed account
	private String address, city;	// location information associated with
							// the account, if known
	private String lastTransaction;	// the date of the last transaction on
							// the account
	private String bankName;	// bank holding the unclaimed account

	// Constructors - initialize new object given known arguments
	// note that for empty arguments, it is expected the null string is passed
	public UAccount(String l, String f, double b, String a, String c,
			String lT, String bN) {
		last = l.trim();
		first = f.trim();
		balance = b;
		address = a.trim();
		city = c.trim();
		lastTransaction = lT.trim();
		bankName = bN.trim();
	}
	
	// compares the argument account holder to this account holder, returning
	// 0 if they are they same, -1 if this account holder comes before the
	// argument account holder lexicographically, 1 if this account holder
	// comes after the argument account holder lexicographically
	public int compareName (String lastName, String firstName) {
		int result;
		if (last.compareToIgnoreCase(lastName) < 0) result = -1;
		else if (last.compareToIgnoreCase(lastName) > 0) result = 1;
		else if (first.compareToIgnoreCase(firstName) < 0) result = -1;
		else if (first.compareToIgnoreCase(firstName) > 0) result = 1;
		else result = 0;
		return result;
	}

	// returns a String formatted presentation of all information in the object
	public String fullPrint() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		String output = "Account " + last + ", " + first + " (" + address +
			", " + city + ") holding " + fmt.format(balance) + " at " +
			bankName + ", last transaction " + lastTransaction;
		return output;		
	}
	
	// returns a String formatted summary of the object 
	public String toString() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		String output = "Account " + last + " holding " + fmt.format(balance) +
			" at " + bankName;
		return output;
	}
	
}