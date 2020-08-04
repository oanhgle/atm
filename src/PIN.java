/**
 * @author Oanh Le
 * */

import java.util.Scanner;
public class PIN {
   String emoji_crossmark = "\u274C";
   String emoji_checkmark = "\u2705";
   
   /**
      * Method Name: processPIN
      * Description: Compares the entered PIN to the PIN stored for the input card ID
      *
      * @param: customer, ATMCards 
      */

	public int processPIN(ATMCard customer, ATMCard[] ATMcards) {
      int isMatch = 0;
      for (int i = 0; i< ATMcards.length; i++) { 
         if (ATMcards[i].cardNum.equals(customer.cardNum)) {
            if (customer.cardPin.equals(ATMcards[i].cardPin)) {
               isMatch = 1;  
            }
         }
      }       
      if (isMatch == 1) { 
         System.out.println(emoji_checkmark + "\nCorrect PIN"); 
      }
      else {
         System.out.println(emoji_crossmark + "\nWrong PIN. Please try again!");  
      }

      return(isMatch); 
	}
  
	/**
      * Method Name: eatCard
      * Description: if the PIN is incorrectly entered four times, this eats the card
      *
      * @param: customer, ATMCards
      */
     
   public boolean eatCard(ATMCard customer, ATMCard[] ATMcards){
      boolean eat = true;
      int isMatch = 0;

      for (int i = 0; i < 4; i++) {
         System.out.println("Enter PIN: "); 
         Scanner pin = new Scanner(System.in);
         customer.cardPin = pin.next(); 
         isMatch = processPIN(customer, ATMcards);
         if (isMatch == 1) {
            eat = false;
            break;
         }     
      }//end for loop
      if(eat){
         System.out.println("Your card is eaten!");
      }
      return eat;
   }

}//end class
