/* 
 * UnclaimedAccounts.java		Author: AMH, Dominic Scalies
 * Processes a list of unclaimed bank account information, storing it in an
 * array data structure and exhibiting that appropriate find and delete actions
 * can be performed on it.
 * Runtime for Unordered Array:
 * 1. 2247041543 ns
 * 2. 1989032822 ns
 * 3. 1952129118 ns
 * 4. 1994318412 ns
 * 5. 2043013601 ns
 * 6. 1972023246 ns
 * 7. 1977269155 ns
 * 8. 1943796380 ns
 * 9. 2010236186 ns
 * 10. 2141462316 ns
 * Runtime for Ordered Array
 * 1. 4408882447 ns
 * 2. 3658636098 ns
 * 3. 3638723210 ns
 * 4. 3411508832 ns
 * 5. 3501998454 ns
 * 6. 3488119793 ns
 * 7. 3542345579 ns
 * 8. 4214637308 ns
 * 9. 4179287527 ns
 * 10. 3408338080 ns
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UnclaimedAccounts {
	public static void main (String[] args) throws IOException {

		// create an AccountDataArray object to store all unclaimed bank
		// account information, set to be able to store information on at
		// most 15,000 accounts
		//AccountDataArray accounts = new AccountDataArray(15000);
		 AccountDataSorted accounts = new AccountDataSorted(15000);
		
		// scan through provided tab delimited text file to import data,
		// assume the first line contains headers
		Scanner fileScan = new Scanner(new File("Unclaimed_bank_accounts.txt"));
		fileScan.nextLine();
		
		// record the system time before and after the complete set of insertions
		// and report time elapsed
		long startTime, endTime;
		startTime = System.nanoTime();
		
		while (fileScan.hasNext()){
			// read in a row's worth of data
			String rowData = fileScan.nextLine();
			Scanner accountScan = new Scanner(rowData);
			accountScan.useDelimiter("\t");
			
			String newLast, newFirst, newAddress, newCity, newTransDate, newBankName;
			double newBalance;
			
			// there may be empty fields in the data causing particular problems
			// that need to be addressed as special cases if the first or last
			// field in a row is empty
			
			// TODO; Fixed
			/* add needed code to parse rowData using accountScan and store the
			sequence of tokens in the six String variables and one double just
			declared be attentive to what happens if a column is left blank,
			particularly if it is the first or last column - make sure your code
			makes sensible choices to handle these cases
			this is the only change you need to, or should, make to the code in
			this file
			*/
			if(accountScan.hasNext()) {
				newLast = accountScan.next();
			}
			else {
				newLast = "";
			}
			if(accountScan.hasNext()) {
				newFirst = accountScan.next();
			}
			else {
				newFirst = "";
			}
			if(accountScan.hasNextDouble()) {
				newBalance = accountScan.nextDouble();
			}
			else {
				newBalance = 0.0;
			}
			if(accountScan.hasNext()) {
				newAddress = accountScan.next();
			}
			else {
				newAddress = "";
			}
			if(accountScan.hasNext()) {
				newCity = accountScan.next();
			}
			else {
				newCity = "";
			}
			if(accountScan.hasNext()) {
				newTransDate = accountScan.next();
			}
			else {
				newTransDate = "";
			}
			if(accountScan.hasNext()) {
				newBankName = accountScan.next();
			}
			else {
				newBankName = "";
			}
			accountScan.close();
			
			// insert the new UAccount object in the array structure
			accounts.insert(newLast, newFirst, newBalance, newAddress, newCity,
					newTransDate, newBankName);			
		}
		endTime = System.nanoTime();
		
		System.out.println("Time for insertion: " + (endTime-startTime) + " ns");
		System.out.println();
		// for testing only; comment out in submission and when checking against
		// sample output in assignment
		// accounts.displayAll();

		// test find method, passing it names to look up in
		// (last name, first name) order
		System.out.println("Looking for Lucy Drake...");
		UAccount lucy = accounts.find("Drake", "Lucy");
		if (lucy == null) System.out.println("Lucy not found");
		else System.out.println(lucy.fullPrint());

		System.out.println("Looking for Nait Forest Technology Club...");
		UAccount tech = accounts.find("Nait Forest Technology Club", "");
		if (tech == null) System.out.println("Tech club not found");
		else System.out.println(tech.fullPrint());
		
		System.out.println("Looking for Bonnie Brown...");
		UAccount bonnie = accounts.find("Brown", "Bonnie");
		if (bonnie == null) System.out.println("Bonnie not found");
		else System.out.println(bonnie.fullPrint());
		System.out.println();
		// test delete method
		System.out.println("Deleting Lucy Drake...");
		if (accounts.delete("Drake", "Lucy")) System.out.println("Lucy deleted");
		else System.out.println("Lucy not found for deletion");
		System.out.println("Looking for Lucy Drake...");
		lucy = accounts.find("Drake", "Lucy");
		if (lucy == null) System.out.println("Lucy not found");
		else System.out.println(lucy.fullPrint());
		System.out.println("Deleting Lucy Drake...");
		if (accounts.delete("Drake", "Lucy")) System.out.println("Lucy deleted");
		else System.out.println("Lucy not found for deletion");
		fileScan.close();
	}
}