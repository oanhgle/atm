/**
 * @author Oanh Le
 * */

import java.util.Scanner;
public class Account {
	String emoji_dog = "\uD83D\uDC36";
	String emoji_crossmark = "\u274C";

	/**
		* Method Name: select
		* Description: Select account to process the transaction 
		*
		* @param: customer, ATMCards
		*/

	public int select(ATMCard customer, ATMCard[] ATMcards) {
		System.out.println(emoji_dog);
		System.out.println("Please select an account: "); 
		System.out.println("(1) Savings");
		System.out.println("(2) Checking");
		Scanner scan = new Scanner(System.in);
		int answer = scan.nextInt();
		if (answer != 1 && answer != 2){
			System.out.println(emoji_crossmark + "\nPlease enter 1 or 2: ");
			answer = scan.nextInt();
		}
		return answer;
	}

	/** 
		* Method Name: typeOfTransaction
		* Description: Customers choose transaction to whether check balance, transfer funds, withdraw money or deposit money.
		* @param: customer, ATMCards
		*/
	   
	public int typeOfTransaction(ATMCard customer, ATMCard[] ATMcards){
		for (int i = 0; i < ATMcards.length;i++) {
			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
				String welcome = "Welcome to Bank of Canine";
				String hello = "Hello ";

				System.out.print(emoji_dog);
				for (int j = 0; j < welcome.length() + 2; j++){
					System.out.print("-");
				}
				System.out.println(emoji_dog);

				System.out.print("|");
				float total_space = (welcome.length() + 4) - (hello.length() + ATMcards[i].Name.length());
				for (float s = 0; s < total_space/2; s++){
					System.out.print(" ");
				}
				System.out.print(hello + ATMcards[i].Name);
				for (float s = 0; s < (total_space/2) - 1; s++){
					System.out.print(" ");
				}
				System.out.println("|");

				System.out.println("|  "+ welcome + " |");
				System.out.print(emoji_dog);
				for (int j = 0; j < welcome.length() + 2; j++){
					System.out.print("-");
				}
				System.out.println(emoji_dog);

				System.out.println("  Please choose a transaction:");
				System.out.println("(1) Check Balance");
				System.out.println("(2) Transfer Funds");
				System.out.println("(3) Withdraw Money");
				System.out.println("(4) Deposit Money");

				Scanner scan= new Scanner(System.in);
				int option = scan.nextInt();
				return option;
			}
		}
		return 0;
	}	
}//end class
	
	
	
