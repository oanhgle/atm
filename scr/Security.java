/*
*@author Oanh Le
*/

import java.util.Scanner;
public class Security {
      
	/**
     * Method Name: verifysavingsBalance
     * Description: Ensure that the card owner's savings account has enough in balance
     * @param: customer, ATMCards, requestMoney
     */
	

	public boolean verifysavingsBalance( ATMCard customer, ATMCard[] ATMcards, float requestMoney) {

		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				// if savings account does not exist
				if (ATMcards[i].savingsBalance == -1) {
					System.out.println("TRANSACTION CAN NOT BE COMPLETED");
					System.out.println("Your saving account does not exist. ");
					return(false);

				} 
				else if (requestMoney <= (ATMcards[i].savingsBalance)) {
					// if savings account exists and has enough money in balance
					System.out.println("Sufficient funds available.");
					return(true);

				} 
				else {
					// if savings account exists and doesn't have enough money in balance
					System.out.println("Sorry :( You have insufficient funds to complete this transaction.");
					return(false);
				}

			}

		}
		return true;

	}

	/**
	 * Method Name: verifycheckingBalance Description: Ensure that the card owner's
	 * checking account has enough in balance
	 * 
	 * @param: customer, ATMCards, requestMoney
	 */

	public boolean verifycheckingBalance( ATMCard customer, ATMCard[] ATMcards, float requestMoney) {
		
		
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				if (requestMoney <= (ATMcards[i].checkingBalance)) {
					
					// if checking account exists and has enough money in balance
					System.out.println("Sufficient funds available.");
					return(true);

				} 
				else {
					// if checking account exists and does not have enough money in balance
					System.out.println("Sorry :( You have insufficient funds to complete this transaction.");
					return(false);
				}

			}
		}

		return true;

	}

	/**
	 * Method Name: verifyMachineBalance 
	 * Description: Ensure that ATM machine has enough money to withdraw
	 *
	 * @param: customer, ATMCards, account, dispense, requestMoney
	 */
	public int verifyMachineBalance( ATMCard customer, ATMCard[] ATMcards, float account,
			Dispense dispense, float requestMoney, float machine_initialAmount) {
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {

				if (requestMoney <= (ATMcards[i].savingsBalance) || requestMoney <= (ATMcards[i].checkingBalance)) {
					if (requestMoney > machine_initialAmount) {
						// if the entered money exceeds the available amount of ATM machine
						System.out.println("Not enough money - cannot complete transaction");

					} else {
						System.out.println("Your request is approved!");
						System.out.println("TRANSACTION COMPLETE");
						System.out.println("You have successfully withdrawn:");
						dispense.dispenseMoney(requestMoney, machine_initialAmount); // ATM dispenses the requested
																						// money
						
						break;
					}
				}
			}
		}

		return 1;

	}

	// Method Name: checkBalance
	// Description: Check balance in either savings or checking account
	// @param: customer, ATMCards, availableBalance, options

	public int checkBalance( ATMCard customer, ATMCard[] ATMcards, float availableBalance,
			 String options) {
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				if (Float.compare(availableBalance, -1) == 0) { // if account does not exist
					System.out.println("Your " + options + " account does not exist. Please select another account.");
				}

				else {
					System.out.println("Account: "+ options);
					System.out.println("Available Balance= $" + availableBalance);

				}
				return (0);
			}

		}
		return (1);
	}

	// Method Name: savingsTransactions
	// Description: Keep track of the number of savings account transactions have
	// been performed this month and charge the customer $1.00 per transaction once they have done 5.
	// @param: customer, ATMCards, cont

	public boolean savingsTransactions( ATMCard customer, ATMCard[] ATMcards, boolean cont) {
		for (int i = 0; i < ATMcards.length; i++) {
			if (customer.cardNum.equals(ATMcards[i].cardNum)) {
				if (ATMcards[i].savingsActivity >= 5) {
					System.out.println(
							"You has exceeded the change limits to your savings account this month.");
					System.out.println("Bank of America will charge a fee of $1.00 into your savings account for this transaction. ");
					System.out.println("Do you wish to continue? (y/n)");
					final Scanner scanner = new Scanner(System.in);
					final String answer = scanner.next();
						if (answer.equals("y") || answer.equals("Y")) {
							ATMcards[i].savingsBalance -= 1.00;
							cont = true;
						}
						else {
							cont = false;
						}
					}
					else if (ATMcards[i].savingsActivity < 5) {
						cont = true;
					}
				}
	 }
		return cont;
  
 
	 }
}
