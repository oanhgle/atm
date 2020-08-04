/**
 * @author Oanh Le
 * */

import java.util.Scanner;
public class ATMCard {
	String Name; //customer's name
	String cardNum; //card number
	String cardPin; //pin number
	float checkingBalance; //checking's balance
	float savingsBalance; //savings balance
	int savingsActivity; //savings activity made this month

	/* Constructor */
	public ATMCard(String name, String num, String pin, float checking, float savings, int savings_activity) {
		Name = name;
		cardNum = num;
		cardPin = pin;
		checkingBalance = checking;
		savingsBalance = savings;
		savingsActivity = savings_activity;
	}
	
	/* main */
	public static void main(String[] args) {

		/* create an array of 5 ATM cards */
		ATMCard[] ATMcards = new ATMCard[5];
		
		/* add 5 ATM cards to the array */
		ATMCard card = new ATMCard("","","",0,0,0);
		for (int i = 0; i < 5; i++) {
			card = new ATMCard("","","",0,0,0);
			ATMcards[i] = card;
		}
		ATMcards[0] = new ATMCard ("Shiba Inu", "123456789", "1234", 500, 200, 2);
		ATMcards[1] = new ATMCard ("Corgi", "100000001", "1000", 100, 700, 3);
		ATMcards[2] = new ATMCard("Husky", "101010101", "1010", 1500, 2500, 5);
		ATMcards[3] = new ATMCard("Pom", "111111111", "1111", 50, -1, 0);
		ATMcards[4] = new ATMCard ("Chihuahua", "121212121", "1212", 150, 250, 1);
		
		String emoji_dog = "\uD83D\uDC36";
		float machine_balance = 0;
		int[] bill = { 20, 10, 5, 1 };
		int[] availableAmount = { 25, 25, 40, 50 };
		for (int i = 0 ; i < bill.length ; i++){
			machine_balance += bill[i] * availableAmount[i]; 
		}

		boolean run = true;
		while (run) {
			ATMCard customer = new ATMCard("","","",0,0,0);
			CardServices cServices = new CardServices();
			PIN pin = new PIN(); 
			Security security = new Security();
			Account account = new Account();
			Dispense dispense = new Dispense();
			Money money = new Money();

			boolean yes = true;
			yes = cServices.insertCard(customer, ATMcards, pin); 
			if (!yes){
				cServices.insertCard(customer, ATMcards, pin);
			}

			String[] options = new String[2]; 
			options[0] = "savings";
			options[1] = "checking";
			String answer = "";
			
			/* processing the transaction */
			do {		
				for (int i = 0; i < 5; i++) {
					if (ATMcards[i].cardNum.equals(customer.cardNum)) {
						int option = account.typeOfTransaction(customer, ATMcards);
						switch(option) {
					
						/* check balance */
						case 1:
							int account_check = account.select(customer, ATMcards);

							/* savings */
							if (account_check == 1) {
								security.checkBalance(customer, ATMcards, ATMcards[i].savingsBalance, options[0]);
							}

							/* checking */
							else if (account_check == 2) {
								security.checkBalance(customer, ATMcards, ATMcards[i].checkingBalance, options[1]);
							}
							break;
							
						/* transfer funds */
						case 2: 
							int account_transfer = account.select(customer, ATMcards); 
							boolean cont = true;
							cont = security.savingsTransactions(customer, ATMcards);

							/* from savings to checking */
							if (account_transfer == 1) {
								if (cont){
									boolean complete = true;
									complete = cServices.transferFunds(customer, ATMcards, security, 1);
									if (complete) {
										ATMcards[i].savingsActivity++;
									}
								}
								else {
									break;
								}
							}
							/* from checking to savings */
							else if (account_transfer == 2) {
								if (cont) {
									boolean complete2 = true;
									complete2 = cServices.transferFunds(customer, ATMcards, security, 2);
									if (complete2) {
										ATMcards[i].savingsActivity++;
									}
								}
								else {
									break;
								}
							}
							break;
							
						/* request money to withdraw */
						case 3: 
							int account_withdraw = account.select(customer, ATMcards); 

							/* from savings account */
							if (account_withdraw == 1) {
								boolean ok = true;
								ok = security.savingsTransactions(customer, ATMcards);
								if (ok){
									float requestMoney = money.enterAmount(customer, ATMcards);
									boolean saving_ok = true;
									saving_ok = security.verifysavingsBalance(customer, ATMcards, requestMoney);

									/* verify saving account */
									if (saving_ok) { 
										security.verifyMachineBalance(customer, ATMcards, dispense, requestMoney, machine_balance, bill, availableAmount); 
										ATMcards[i].savingsActivity++;
										ATMcards[i].savingsBalance -= requestMoney;
										machine_balance -= requestMoney;
									}
									else{
										break;
									}
								}
								else {
									break;
								}
							}
							
							/* from checking account */
							else if (account_withdraw == 2) {
								float requestMoney = money.enterAmount(customer, ATMcards);
								boolean checking_ok = true;
								checking_ok = security.verifycheckingBalance(customer, ATMcards, requestMoney);

								if (checking_ok) {
									security.verifyMachineBalance(customer, ATMcards, dispense, requestMoney, machine_balance, bill, availableAmount);
									ATMcards[i].checkingBalance -= requestMoney;
									machine_balance -= requestMoney;
								}
								else {
									break;
								}
							}
			
							break;
							
						/* making deposit */
						case 4: 
							int account_deposit = account.select(customer, ATMcards); 

							/* to savings */
							if (account_deposit == 1) {
								boolean okay = true;
								okay = security.savingsTransactions(customer, ATMcards);

								if (okay) {
									cServices.deposit(customer, ATMcards, machine_balance, 1);
									ATMcards[i].savingsActivity++;
								}
								else {
									break;
								}
							} 

							/* to checking */
							else if (account_deposit == 2) { 
								cServices.deposit(customer, ATMcards, machine_balance, 2);
							}
							break;

						}//end switch
					}  
				}
				System.out.println(emoji_dog);
				System.out.print("Would you like to perform another transaction? (y/n) "); 
				Scanner scan= new Scanner(System.in);
				answer = scan.next();
				if (answer.equals("n")|| answer.equals("N")) {
					cServices.returnCard();
					run = true;
				}
			} while(answer.equals("y") || answer.equals("Y"));	

		}//end while loop
	}//end main
}//end class



	        
	     
	   
