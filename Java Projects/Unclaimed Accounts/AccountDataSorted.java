/*
 * Created by Dominic Scalies for Homework 1
 * Creates the Ordered Array Data Structure for Unclaimed Accounts
 * Sources: Lafore p.59-61
 */

public class AccountDataSorted {
	private UAccount[] Accounts; // Array of UAccount objects
	private int numItems; // number of items in the array

	// Constructor
	public AccountDataSorted(int n) {
		Accounts = new UAccount[n];
		numItems = 0;
	}

	public int size() {
		return numItems;
	}

	// local method used to find an item's index
	private int findIndex(String last, String first) {
		int lowerBound = 0;
		int upperBound = numItems - 1;
		int index;
		while (true) {
			index = (lowerBound + upperBound) / 2;
			if (Accounts[index].compareName(last, first) == 0) {
				return index; // found it
			} else {
				if (lowerBound > upperBound) {
					return numItems;
				} else {
					if (Accounts[index].compareName(last, first) == -1) {
						lowerBound = index + 1;
					} else {
						upperBound = index - 1;
					}
				}
			}
		}
	}

	// conducts a binary search and finds the requested data item; returns null
	// if item
	// cannot be found
	public UAccount find(String last, String first) {
		int lowerBound = 0;
		int upperBound = numItems - 1;
		int curIn;
		while (true) {
			curIn = (lowerBound + upperBound) / 2;
			if (Accounts[curIn].compareName(last, first) == 0) {
				return Accounts[curIn]; // found it
			} else {
				if (lowerBound > upperBound) {
					return null; // can't find it
				} else {
					if (Accounts[curIn].compareName(last, first) == -1) {
						lowerBound = curIn + 1;
					} else {
						upperBound = curIn - 1;
					}
				}
			}
		}
	}

	// puts an element into an array
	public void insert(String last, String first, double balance, String address, String city, String transDate,
			String bankName) {
		int index = numItems;
		for (int j = 0; j < numItems; j++) {
			if (Accounts[j].compareName(last, first) == 1) {
				index = j;
				j = numItems;
			}
		}
		for (int k = numItems; k > index; k--) {
			Accounts[k] = Accounts[k - 1];
		}
		Accounts[index] = new UAccount(last, first, balance, address, city, transDate, bankName);
		numItems++;
	}

	// deletes an element from the array
	public boolean delete(String last, String first) {
		int index = findIndex(last, first); // finds the requested item for
											// deletion
		if (index == numItems) // check to see if item was not found
		{
			return false; // item not found
		} else // item found
		{
			// for every item after the item to be deleted, shift it to the left
			// one index
			for (int i = index; i < numItems - 1; i++) {
				Accounts[i] = Accounts[i + 1];
			}
			Accounts[numItems - 1] = null; // remove the last value to prevent
											// duplicates
			numItems--; // reduce the number to match the number of items
						// currently stored
			return true; // let driver know deletion was successful
		}
	}

	// Displays the entire array
	public void displayAll() {
		for (int i = 0; i < numItems; i++) {
			System.out.println(Accounts[i].fullPrint());
		}
	}
}
