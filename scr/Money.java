/*
*@author Oanh Le
*/

import java.util.Scanner;
public class Money {
	/**
	 * Method Name: enterAmount
	 * Description: asks user to enter the amount that they want to withdraw
	 *
	 * @param: ATMCard customer, ATMCard[] ATMCards
	 */
	public float enterAmount(ATMCard customer, ATMCard[] ATMcards) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter amount to withdraw: "); //customer input the desired amount to withdraw
		float requestMoney = scanner.nextFloat(); 
	    return requestMoney; 
	    }
	
}
