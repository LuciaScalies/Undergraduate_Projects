/*
 * Created by Dominic Scalies for Homework 1
 * Creates the Unordered Array Data Structure for Unclaimed Accounts
 */
public class AccountDataArray {
	private UAccount[] Accounts; //Array of UAccount objects
	private int numItems; //number of items in the array
	
	public AccountDataArray(int n) {
		Accounts = new UAccount[n];
		numItems = 0;
	}
	
	//Insert method accepts data and creates a new account object to add to the array
	public void insert(String last, String first, double balance, String address, 
			String city, String transDate, String bankName) {
		Accounts[numItems] = new UAccount(last, first, balance, address, city, transDate,
				bankName);
		numItems++;
	}
	
	//Find method
	public UAccount find(String last, String first) 
	{
		int index = numItems;
		for(int i = 0; i < numItems; i++)
		{
			if(Accounts[i].compareName(last, first) == 0)
			{
				index = i;
				i = numItems;
				
			}
		}
		if(index >= numItems)
		{
			return null;
		}
		else
		{
			return Accounts[index];
		}
	}
	
	//Deletion method
	public boolean delete(String last, String first)
	{
		int index = numItems;
		for(int i = 0; i < numItems; i++)
		{
			if(Accounts[i].compareName(last, first) == 0)
			{
				index = i;
				i = numItems;
				
			}
		}
		if(index < numItems)
		{
			Accounts[index] = Accounts[numItems-1];
			numItems--;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Displays a fullPrint of all items in the Array; for error checking
	public void displayAll()
	{
		for(int i = 0; i < numItems; i++)
		{
			System.out.println(Accounts[i].fullPrint());
		}
	}
}
