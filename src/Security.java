/**
 * @author Oanh Le
 * */

import java.util.Scanner;
public class Security {
	String emoji_crossmark = "\u274C";
	String emoji_checkmark = "\u2705";
	String emoji_dog = "\uD83D\uDC36";
	String emoji_letter = "\uD83D\uDC8C";

	/**
     * Method Name: verifysavingsBalance
     * Description: Ensure that the card owner's savings account has enough in balance
     * @param: customer, ATMCards, requestMoney
     */
	
	public boolean verifysavingsBalance( ATMCard customer, ATMCard[] ATMcards, float requestMoney) {
		boolean ok = true;
	
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				/* if savings account does not exist */
				if (ATMcards[i].savingsBalance == -1) {
					System.out.println(emoji_crossmark);
					System.out.println("Your saving account does not exist - cannot complete the transaction");
					ok = false;
				} 
				/* if savings account exists and has enough money in balance */
				else if (requestMoney <= (ATMcards[i].savingsBalance)) {
					System.out.println(emoji_checkmark);
					System.out.println("Sufficient funds available");
					ok = true;
				} 
				/* if savings account exists and doesn't have enough money in balance */
				else {
					System.out.println(emoji_crossmark);
					System.out.println("Sorry! You have insufficient funds to complete this transaction");
					ok = false;
				}
			}
		}//end for loop
		return ok;
	}

	/**
	 * Method Name: verifycheckingBalance 
	 * Description: Ensure that the card owner's checking account has enough in balance
	 * @param: customer, ATMCards, requestMoney
	 */

	public boolean verifycheckingBalance( ATMCard customer, ATMCard[] ATMcards, float requestMoney) {
		boolean ok = true;
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				/* if checking account exists and has enough money in balance */
				if (requestMoney <= (ATMcards[i].checkingBalance)) {
					System.out.println(emoji_checkmark);
					System.out.println("Sufficient funds available");
					ok = true;
				} 
				/* if checking account exists and does not have enough money in balance */
				else {
					System.out.println(emoji_crossmark);
					System.out.println("Sorry! You have insufficient funds to complete this transaction");
					ok = false;
				}
			}
		}//end for loop
		return ok;
	}

	/**
	 * Method Name: verifyMachineBalance 
	 * Description: Ensure that ATM machine has enough money to withdraw
	 * @param: customer, ATMCards, dispense, requestMoney, machine_balance, bill, availableAmount 
	 */

	public void verifyMachineBalance( ATMCard customer, ATMCard[] ATMcards, Dispense dispense, float requestMoney, 
	float machine_balance, int[] bill, int[] availableAmount) {

		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				if (requestMoney <= (ATMcards[i].savingsBalance) || requestMoney <= (ATMcards[i].checkingBalance)) {
					
					/* if the entered money exceeds the available amount of ATM machine */
					if (requestMoney > machine_balance) {
						System.out.println(emoji_crossmark);
						System.out.println("Not enough money in machine - cannot complete transaction");
					} 
					else {
						System.out.println(emoji_checkmark);
						System.out.println("Your request is approved!");
						System.out.println("TRANSACTION COMPLETED");
						System.out.println(emoji_letter);
						System.out.println("You have successfully withdrawn:");

						/* ATM dispenses the requested money */
						dispense.dispenseMoney(requestMoney, machine_balance, bill, availableAmount);
						System.out.println("Total: $" + requestMoney);
					}
				}
			}
		}//end for loop
	}

	/**
	 * Method Name: checkBalance
	 * Description: Check balance for either savings or checking account
	 * @param: customer, ATMCards, availableBalance, options
	 */

	public void checkBalance( ATMCard customer, ATMCard[] ATMcards, float availableBalance, String option) {
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				/* check if account does not exist */
				if (Float.compare(availableBalance, -1) == 0) { 
					System.out.println(emoji_crossmark + "\nYour " + option + " account does not exist. \nPlease select another option");
				}
				else {
					System.out.println(emoji_letter);
					System.out.println("Account summary:");
					System.out.println(option+ " balance = $" + availableBalance);
				}
			}
		}//end for loop
	}

	/**
	 * Method Name: savingsTransactions
	 * Description: Keep track of the number of savings account transactions have
	 * been performed this month and charge the customer $1.00 per transaction once they have done 5.
	 * 
	 * @param: customer, ATMCards, cont
	 */

	public boolean savingsTransactions( ATMCard customer, ATMCard[] ATMcards) {
		boolean cont = true;
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				if (ATMcards[i].savingsActivity >= 5) {
					System.out.println(emoji_letter);
					System.out.println("You has exceeded the change limits to your savings account this month");
					System.out.println("Bank of Canine will charge a fee of $1.00 into your savings account for this transaction");
					System.out.print("Do you wish to continue? (y/n) ");
					Scanner scanner = new Scanner(System.in);
					String answer = scanner.next();
					boolean doAgain = true;

					while(doAgain){
						if (answer.equals("y") || answer.equals("Y")) {
							ATMcards[i].savingsBalance -= 1.00;
							doAgain = false; 
							cont = true;
						}
						else if (answer.equals("N") || answer.equals("n")){
							doAgain = false;
							cont = false;
						}
						else {
							System.out.println("Please type y or n");
							answer = scanner.next();
							doAgain = true;
						}
					}
				}
				else if (ATMcards[i].savingsActivity < 5) {
					cont = true;
				}
			}
	 	}//end for loop
		return cont;
	}
}//end class
