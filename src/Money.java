/**
 * @author Oanh Le
 * */

import java.util.Scanner;
public class Money {
	String emoji_dog = "\uD83D\uDC36";

	/**
	 * Method Name: enterAmount
	 * Description: prompt user to enter the amount that they want to withdraw
	 *
	 * @param: ATMCard customer, ATMCard[] ATMCards
	 */

	public float enterAmount(ATMCard customer, ATMCard[] ATMcards) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(emoji_dog + "\nEnter amount to withdraw: ");
		float requestMoney = scanner.nextFloat(); 
	    return requestMoney; 
	}
	
}//end class
