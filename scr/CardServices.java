/*
*@author Oanh Le
*/

import java.util.Scanner; 
public class CardServices{
   
 
	
   /**
    * Method Name: insertCard
    * Description: Check to see if the inserted card's ID number is recognized.
    *
    * @param: ATMCard customer, ATMCard[] ATMCards, PIN pin
    */
  
   public void insertCard(ATMCard customer, ATMCard[] ATMcards, PIN pin) {
	   
        //Check the input ATM ID card

	  boolean doAgain= true;
	   while (doAgain) {
    	Scanner scanner = new Scanner (System.in); 
    	System.out.println("Enter Card:"); //customer inputs card id
        customer.cardNum = scanner.nextLine();
        
        for (int i=0; i < ATMcards.length; i++) {
            if (customer.cardNum.equals(ATMcards[i].cardNum)) { //if customer inputs id card correctly
            	
               System.out.println("Card ID found!"); //system will print out "Card ID found!"
               pin.eatCard(customer, ATMcards);
               return;
            }
        }
           System.out.println("Unrecognized Card!"); // card is not recognized
            	 doAgain=true;
            }
            
            
}
   
            
      

	 
   
 /**
  * Method Name: returnCard
  * Description: Return card to the user
  *
  */
    public int returnCard() {
    	  System.out.println("Thank you!");
          System.out.println("Your card is returned.");
          return (1);

       }
    
    
    /** 
     *Method Name: transferFunds
     *Description: transfer funds from savings to checking account or vice versa
     *@param: customer, ATMcards, verify, account
     */
    
    public boolean transferFunds (ATMCard customer, ATMCard[] ATMcards, Security verify, int account) {
    	
    	System.out.println("Enter the amount to transfer:");
    	Scanner scan= new Scanner(System.in);
    	float transferAmount = scan.nextFloat();
    	
    		for (int i =0; i< ATMcards.length;i++) {
    			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
    				
    				switch(account) {
    				case 1: //from savings to checking
    					boolean verifyS = true;
    					verifyS = verify.verifysavingsBalance(customer, ATMcards, transferAmount);
    					if (verifyS) {
    						ATMcards[i].checkingBalance += transferAmount;
            				ATMcards[i].savingsBalance -=transferAmount;
            				System.out.println("TRANSACTION COMPLETE");
            				System.out.println("You have transferred $"+ transferAmount + " from savings account to checking account");
            				System.out.println("Account Summary:");
            				System.out.println("Checking Balance = $"+ ATMcards[i].checkingBalance);
            				System.out.println("Saving Balance = $"+ ATMcards[i].savingsBalance);
    						return true;
    					}
    					else {
    						return false;
    					}
    					
    				case 2: //from checking to savings
    					boolean verifyC = true;
        	    		verifyC= verify.verifycheckingBalance(customer, ATMcards, transferAmount);
    					if (verifyC) {
    						if (ATMcards[i].savingsBalance==-1) {
    							System.out.println("No account to transfer to");
    							break;
    						}
    						ATMcards[i].checkingBalance -= transferAmount;
    						ATMcards[i].savingsBalance +=transferAmount;
            				System.out.println("TRANSACTION COMPLETE");
            				System.out.println("You have transferred $"+ transferAmount + " from checking account to savings account");
            				System.out.println("Account Summary:");
            				System.out.println("Saving Balance = $"+ ATMcards[i].savingsBalance);
            				System.out.println("Checking Balance = $"+ ATMcards[i].checkingBalance);
            				return true;
    					}
    					else {
    						return false;
    				}
    				
    
    }
    			
}
    		}
			return true;
    	}
    
   
     /**
     *Method Name: deposit
     *Description: deposit money from account to checking or vice versa
     *@param: customer, ATMcards, machine_initialAmount, account
     */
    
    
    public float deposit (ATMCard customer, ATMCard[] ATMcards, float machine_initialAmount, int account) {
    	
    	System.out.println("Please insert your envelop to deposit");
    	Scanner scan= new Scanner(System.in);
    	float depositAmount = scan.nextFloat();
    	
    	for (int i =0; i< ATMcards.length;i++) {
			if (ATMcards[i].cardNum.equals(customer.cardNum)) {
				switch(account) {
				case 1: //to savings
					    ATMcards[i].savingsBalance += depositAmount;
	    				machine_initialAmount+= depositAmount;
	    				System.out.println("TRANSACTION COMPLETE");
	    				System.out.println("You have deposited $"+ depositAmount + " to your savings account.");
	    				System.out.println("Account summary:");
	    				System.out.println("Savings balance= $"+ ATMcards[i].savingsBalance);
						
						break;
				
				case 2: //to checkings
					ATMcards[i].checkingBalance += depositAmount;
    				machine_initialAmount+= depositAmount;
    				System.out.println("TRANSACTION COMPLETE");
    				System.out.println("You have deposited $"+ depositAmount + " to your checking account.");
    				System.out.println("Account summary:");
    				System.out.println("Checking balance= $"+ ATMcards[i].checkingBalance);
					
					break;
    		
}
			}
    	}
		return depositAmount;
    }
}



    
    
