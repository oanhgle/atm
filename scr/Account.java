/*
*@author Oanh Le
*/

import java.util.Scanner;
public class Account {
	/**
	   * Method Name: select
	   * Description: Select account to process the transaction 
	   *
	   * @param: customer, ATMCards
	   */
	public int select(ATMCard customer, ATMCard[] ATMcards) {
		
		System.out.println("Please select an account: "); 
		System.out.println("(1) Savings");
		System.out.println("(2) Checking");
		Scanner scan= new Scanner(System.in);
		int answer = scan.nextInt(); //customer inputs 1 or 2
		return answer;

	}
	
	
	   /** 
     * Method Name: typeOfTransaction
	   * Description: Customers choose transaction to whether check balance, transfer funds, withdraw money or deposit money.
	   * @param: customer, ATMCards
     */
	   
	public int typeOfTransaction(ATMCard customer, ATMCard[] ATMcards){
		
		for (int i =0; i< ATMcards.length;i++) {


			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
			 
				System.out.println("Hello, "+ ATMcards[i].firstname);
		System.out.println("Welcome to Bank of America");
		System.out.println("Please choose a transaction:");
		System.out.println("(1) Check Balance");
		System.out.println("(2) Transfer funds");
		System.out.println("(3) Withdraw money");
		System.out.println("(4) Deposit money");
		
		Scanner scan= new Scanner(System.in);
		int option = scan.nextInt(); //customer inputs 1,2,3 or 4
		return option;
	}
		}
		return 0;
		}
		
}
	
	
	
