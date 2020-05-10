/*
@author Oanh Le
*/

import java.util.Scanner;
public class ATMCard {
	
		// Initialize ATMCard values
	    String firstname; //customer's first name
	    String lastname; //customer's last name
	    String cardNum; //card number
	    String cardPin; //pin number
	    float checkingBalance; //checking's balance
	    float savingsBalance; //savings balance
	    float savingsActivity; //savings activity made this month
	 
	    public ATMCard(String firstName, String lastName, String num, String pin, int checkings, int savings, int savings_activity) {
	    	firstname= firstName;
	    	lastname= lastName;
	    	cardNum= num;
	    	cardPin= pin;
	    	checkingBalance= checkings;
	    	savingsBalance= savings;
	    	savingsActivity= savings_activity;
	    	}
	    
	    /**
	    * Method Name: main
	    */
	   public static void main(String[] args) {
	       // Create an array of 5 ATM cards.
	        ATMCard[] ATMcards = new ATMCard[5];
	        
	        // Create 5 ATM cards and add them to the array.
	        ATMCard ATMcard = new ATMCard("","","","",0,0,0);
	        for (int i = 0; i < 5; i++) {
	        
	            ATMcard = new ATMCard("","","","",0,0,0);
	            ATMcards[i] = ATMcard;
	        }
	    
	        // Stored ATM cards in database.
	        ATMcards[0].firstname= "Kyle"; ATMcards[0].lastname= "Bustami";
	        ATMcards[0].cardNum = "123456789"; ATMcards[0].cardPin = "1111";
	        ATMcards[0].checkingBalance = 500; ATMcards[0].savingsBalance = 200;
	        ATMcards[0].savingsActivity = 2;

	        ATMcards[1].firstname= "Cory"; ATMcards[1].lastname= "Chambers";
	        ATMcards[1].cardNum = "135792468"; ATMcards[1].cardPin = "2097";
	        ATMcards[1].checkingBalance = 100; ATMcards[1].savingsBalance = 700;
	        ATMcards[1].savingsActivity = 3;

	        ATMcards[2].firstname= "Tanner"; ATMcards[2].lastname= "Douglas";
	        ATMcards[2].cardNum = "019283746"; ATMcards[2].cardPin = "6194";
	        ATMcards[2].checkingBalance = 1500; ATMcards[2].savingsBalance = 2500;
	        ATMcards[2].savingsActivity = 5;

	        ATMcards[3].firstname= "Jordan"; ATMcards[3].lastname= "Jones";
	        ATMcards[3].cardNum = "675849302"; ATMcards[3].cardPin = "0071";
	        ATMcards[3].checkingBalance = 50; ATMcards[3].savingsBalance = -1;
	        ATMcards[3].savingsActivity = 0;

	        ATMcards[4].firstname= "Jesse"; ATMcards[4].lastname= "Pecar";
	        ATMcards[4].cardNum = "347821904"; ATMcards[4].cardPin = "9871";
	        ATMcards[4].checkingBalance = 150; ATMcards[4].savingsBalance = 250;
	        ATMcards[4].savingsActivity = 1;
	      
	        
	        boolean run = true;
	        while (run) {
	        	
	        	 // Create objects to perform some methods on a card.
	        	ATMCard customer = new ATMCard("","","","",0,0,0);
		        CardServices cServices = new CardServices();
		        PIN pin = new PIN(); 
		        Security security= new Security();
		        Account account= new Account();
		        Dispense dispense =new Dispense();
		        Money money = new Money();
		        
		        cServices.insertCard(customer,ATMcards,pin); 
		        float machine_initialAmount = 1000;
	   
		        String[] options = new String[2]; 
		        options[0] = "Savings";
		        options[1] = "Checking";
		        String Answer="";
		        
		        //Description: Allows customer to process the transaction

		        do {
		        	 
			        for (int i = 0; i < 5; i++) {
			        	
			        	if (ATMcards[i].cardNum.equals(customer.cardNum)) {
			        	    	int option= account.typeOfTransaction(customer, ATMcards);
			        	    	switch(option) {
			        	    	
			        	    	 //1. check balance
			        	    	case 1:
			        	    		int acctCheck = account.select(customer, ATMcards); 
			    	        	    if (acctCheck==1) { //savings
			    	        	    	security.checkBalance(customer, ATMcards, ATMcards[i].savingsBalance, options[0]);
			    	        	    	
			    	        	    }
			    	        	    else if (acctCheck==2) { //checking
			    	        	    	security.checkBalance(customer, ATMcards, ATMcards[i].checkingBalance, options[1]);
			    	        	    }
			        	      		break;
			        	      		
			        	      	//2. transfer from savings account to checking	
			        	      	case 2: 
			        	      		int acctTransfer = account.select(customer, ATMcards); 
			    	        	    if (acctTransfer == 1) { //savings
			    
			    	        	    	if (security.savingsTransactions(customer, ATMcards, true)) {
			    	        	    		boolean complete = true;
				    	        	    	complete = cServices.transferFunds(customer, ATMcards, security, 1);
			    	        	    		if (complete) {
												ATMcards[i].savingsActivity++;
												break;
											}
											else {
												break;
											}
			    	        	    	}
			    	        	    	else {
			    	        	    		break;
			    	        	    	}
			    	  
			    	        	    }
			    	        	    
			    	        	    else if (acctTransfer == 2) { //checking
			    	        	    
			    	        	      	if (security.savingsTransactions(customer, ATMcards, true)) {
			    	        	      		boolean complete2 = true;
				    	        	    	complete2 = cServices.transferFunds(customer, ATMcards, security, 2);
			    	        	    		if (complete2) {
												ATMcards[i].savingsActivity++;
												continue;
											}
											else {
												
												break;
											}
			    	        	    	}
			    	        	    	else {
			    	        	    		break;
			    	        	    	}
			    	        	    
			    	        	    }
			        	      		break;
			        	      		
			        	      		
			        	      	//3. request money to withdraw
			        	      	case 3: 
			        	      		int acctWithdraw = account.select(customer, ATMcards); 
			    	        	    if (acctWithdraw == 1) { //savings
			    	        	    	if (security.savingsTransactions(customer, ATMcards, true)) {
			    	        	    		float requestMoney = money.enterAmount(customer, ATMcards);
			    	        	    		boolean verifyS = true;
			    	        	    		verifyS= security.verifysavingsBalance(customer, ATMcards, requestMoney);
			    	        	    		//verify saving account
				    	        	    	if (verifyS) { 
				    	        	    		security.verifyMachineBalance(customer, ATMcards, ATMcards[i].savingsBalance, 
				    	        	    				dispense, requestMoney, machine_initialAmount); 
				    	        	    		ATMcards[i].savingsActivity++;
				    	        	    		ATMcards[i].savingsBalance -= requestMoney;
				    	        	    		machine_initialAmount -=requestMoney;
						        		  	    break;
			    	        	    	}
			    	        	    	    else{
			    	        	    		     break;
			    	        	    	}
				    	        	    	
			    	        	    }
			    	        	    	else {
			    	        	    		break;
			    	        	    	}
			    	        	    }
			    	        	    
			    	        	    else if (acctWithdraw == 2) { //checking
			    	        	    	float requestMoney = money.enterAmount(customer, ATMcards);
			    	        	    	boolean verifyC = true;
		    	        	    		verifyC= security.verifycheckingBalance(customer, ATMcards, requestMoney);
			    	        	    	if (verifyC) {
			    	        	    		security.verifyMachineBalance(customer, ATMcards, ATMcards[i].checkingBalance,
			    	        	    				dispense, requestMoney, machine_initialAmount);
			    	        	    		ATMcards[i].checkingBalance -= requestMoney;
			    	        	    		machine_initialAmount -=requestMoney;
					        		  	    break;
			    	        	    	}
			    	        	    	else {
			    	        	    		break;
			    	        	    	}
			    	        	    }
				    		      		
			        		  	   break;
			        		  	  
			        		  	//4. Making deposit
			        	      	case 4: 
			        	      		int acctDeposit = account.select(customer, ATMcards); 
			    	        	    if (acctDeposit == 1) { //savings
			    	        	    	if (security.savingsTransactions(customer, ATMcards, true)) {
					        	      		cServices.deposit(customer, ATMcards, machine_initialAmount, 1);
					        	      		ATMcards[i].savingsActivity++;
					        	      		break;
			    	        	    	}
			    	        	    	else {
			    	        	    		break;
			    	        	    	}
			    	        	    } 
			    	        	    else if (acctDeposit == 2) { //checking
			    	        	    	cServices.deposit(customer, ATMcards, machine_initialAmount, 2);
			    	        	    	break;
			    	        	    }
			        	      		break;
			        	      	
			        	      		}
			        	    	 
			        	        }  
			        }
			        System.out.println("Would you like to perform another transaction? (Y/N)"); 
					Scanner scan= new Scanner(System.in);
					Answer = scan.next();
					if (Answer.equals("n")|| Answer.equals("N")) {
						cServices.returnCard();
					}
			      
			   } while(Answer.equals("y") || Answer.equals("Y"));
			        
			        }
			   }
}



	        
	     
	   
