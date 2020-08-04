/**
 * @author Oanh Le
 * */

import java.util.Scanner; 
public class CardServices{
	String emoji_crossmark = "\u274C";
	String emoji_checkmark = "\u2705";
	String emoji_dog = "\uD83D\uDC36";
	String emoji_letter = "\uD83D\uDC8C";

   /**
    * Method Name: insertCard
    * Description: Check to see if the inserted card's ID number is recognized.
    *
    * @param: customer, ATMcards, pin
	*/

	public boolean insertCard (ATMCard customer, ATMCard[] ATMcards, PIN pin){
		boolean found = false;
		boolean doAgain = true;
		boolean cont = true; 
		boolean eat = true;

		/* prompt customer to input card ID */
		System.out.println("Enter Card: "); 
		Scanner scanner = new Scanner(System.in); 
		customer.cardNum = scanner.nextLine();

		while (doAgain){
			for(int i = 0; i < ATMcards.length; i++){
				if (customer.cardNum.equals(ATMcards[i].cardNum)) {
					System.out.println(emoji_checkmark + "\nCard ID found!");
					found = true;
					doAgain = false;
				}
			}
			if(!found){
				System.out.println(emoji_crossmark + "\nUnrecognized Card!\nPlease enter again: ");
				customer.cardNum = scanner.nextLine();
				doAgain = true;
			}
			else {
				eat = pin.eatCard(customer, ATMcards);
				if(eat){
					cont = false;
				}
				else {
					cont = true;
				}
			}
		}//end while loop
		return cont;
    }
   
	/**
	 * Method Name: returnCard
	 * Description: Return card to the user
	 */

    public void returnCard() {
		System.out.println(emoji_letter);
		System.out.println("Thank you!");
		System.out.println("Your card is returned");
		System.out.println("----------------------");
    }
    
    /** 
     * Method Name: transferFunds
     * Description: transfer funds from savings to checking account or vice versa
     * @param: customer, ATMcards, verify, account
     */
    
    public boolean transferFunds (ATMCard customer, ATMCard[] ATMcards, Security verify, int account){
    	System.out.println(emoji_dog + "\nEnter the amount to transfer:");
    	Scanner scan = new Scanner(System.in);
		float transferAmount = scan.nextFloat();
		boolean complete = true;

		for (int i = 0; i < ATMcards.length; i++) {
			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
				switch(account) {
					/* from savings to checking */
					case 1: 
						boolean saving_ok = true;
						saving_ok = verify.verifysavingsBalance(customer, ATMcards, transferAmount);
						if (saving_ok) {
							ATMcards[i].checkingBalance += transferAmount;
							ATMcards[i].savingsBalance -= transferAmount;
							System.out.println(emoji_checkmark + "\nTRANSACTION COMPLETED");
							System.out.println("You have transferred $"+ transferAmount + " from savings account to checking account");
							System.out.println(emoji_letter);
							System.out.println("Account Summary:");
							System.out.println("Checking Balance = $"+ ATMcards[i].checkingBalance);
							System.out.println("Saving Balance = $"+ ATMcards[i].savingsBalance);
							complete = true;
						}
						else {
							complete = false;
						}
						break;

					/* from checking to savings */
					case 2:
						boolean checking_ok = true;
						checking_ok= verify.verifycheckingBalance(customer, ATMcards, transferAmount);
						if (checking_ok) {
							if (ATMcards[i].savingsBalance == -1) {
								System.out.println(emoji_crossmark + "\nNo account to transfer to");
								break;
							}
							ATMcards[i].checkingBalance -= transferAmount;
							ATMcards[i].savingsBalance +=transferAmount;
							System.out.println(emoji_checkmark + "\nTRANSACTION COMPLETED");
							System.out.println("You have transferred $"+ transferAmount + " from checking account to savings account");
							System.out.println(emoji_letter);
							System.out.println("Account Summary:");
							System.out.println("Saving Balance = $"+ ATMcards[i].savingsBalance);
							System.out.println("Checking Balance = $"+ ATMcards[i].checkingBalance);
							complete = true;
						}
						else {
							complete = false;
						}
						break;
				}//end switch
			}//end if
		}//end for loop
		return complete;
    }
   
	/**
	 * Method Name: deposit
	 * Description: deposit money from account to checking or vice versa
	 * @param: customer, ATMcards, machine_balance, account
	 */

    public float deposit (ATMCard customer, ATMCard[] ATMcards, float machine_balance, int account) {
		System.out.println(emoji_dog);
    	System.out.println("Please insert your envelop to deposit");
    	Scanner scan = new Scanner(System.in);
    	float depositAmount = scan.nextFloat();
    	
    	for (int i =0; i< ATMcards.length;i++) {
			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
				switch(account) {
					/* to savings */
					case 1: 
						ATMcards[i].savingsBalance += depositAmount;
						machine_balance += depositAmount;
						System.out.println(emoji_checkmark);
						System.out.println("TRANSACTION COMPLETED");
						System.out.println(emoji_letter + " Account summary " + emoji_letter);
						System.out.println("+ $" + depositAmount);
						System.out.println("Savings balance = $"+ ATMcards[i].savingsBalance);
						break;

					/* to checking */
					case 2:
						ATMcards[i].checkingBalance += depositAmount;
						machine_balance += depositAmount;
						System.out.println(emoji_checkmark);
						System.out.println("TRANSACTION COMPLETED");
						System.out.println(emoji_letter + " Account summary " + emoji_letter);
						System.out.println("+ $" + depositAmount);
						System.out.println("Checking balance = $"+ ATMcards[i].checkingBalance);
						break;

				}//end switch
			}//end if
    	}//end for
		return depositAmount;
	}

}//end class



    
    
