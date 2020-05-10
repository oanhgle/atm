/*
*@author Oanh Le
*/

import java.util.Scanner;
public class PIN {
    
  /**
   * Method Name: processPIN
   * Description: Compares the entered PIN to the PIN stored for this card
   * User has to input the pin twice
   *
   * @param: customer, ATMCards 
   */
	

	public int processPIN(ATMCard customer, ATMCard[] ATMcards) {
        int isMatch=0;
		for (int i =0; i< ATMcards.length;i++) { 
			
			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
				if (customer.cardPin.equals(ATMcards[i].cardPin)) {
		
					isMatch =1;  
				   }
	         }
		}
		
	            
	         if (isMatch == 1){ 
	            System.out.println("Correct PIN"); 
	            
	            return(isMatch);
	           
	         }
	        
	         else 
	         {
	        	 System.out.println("Wrong PIN. Please try again!"); 
	            
	         }
		
		
	         return(isMatch); 
	}
  
	/**
	   * Method Name: processPIN
	   * Description:if the PIN is incorrectly entered four times, this eats the card
	   *
	   * @param: customer, ATMCards
	   */
     
      public int eatCard(ATMCard customer, ATMCard[] ATMcards){
    	  
         int compare = 0;
         int isMatch = 0;
         for (int i = 0; i < 4; i++) {
        	 System.out.println("Enter pin:"); 
     		 Scanner pin = new Scanner(System.in);
             customer.cardPin = pin.next(); 
             isMatch = processPIN(customer, ATMcards);
       if (isMatch== 1) 
            {
               compare = 1; 
               break; 
            }
                    
         }
      if (compare != 1) 
      {
      
         System.out.println("Your card is eaten");
         ATMCard.main(null);
        
      }
         
      return compare;
     
   }


}
