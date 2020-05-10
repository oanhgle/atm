/*
*@author Oanh Le
*/

public class Dispense {
	 /**
     * Method Name: verifyMachineBalance
     * Description: ATM dispenses money. 
     * It will attempt to provide every customer with money using the largest available bill
     * @param withdrawAmount, totalBills
     */
     
	 int[] dispense = { 20, 10, 5, 1 };
	 int[] count = {0,0,0,0}; 
	 int[] availableAmount = {25, 25, 40, 50};
 
	 public int dispenseMoney(float withdrawAmount, float totalBills) {
		
		
			for (int i = 0; i < dispense.length; i++) {
				
				if (withdrawAmount <= totalBills) {
					if (dispense[i] < withdrawAmount || dispense[i] == withdrawAmount) {
						 int countNote = (int) (withdrawAmount/ dispense[i]);
						 if(availableAmount[i]>0) { 
							
								if (countNote >= availableAmount[i]) 
									count[i] = availableAmount[i];
								else 
								    count[i] = countNote;
								
								if (countNote >= availableAmount[i]) 
									availableAmount[i] = 0;
								else 
									availableAmount[i] -= countNote;
								    
								withdrawAmount -= count[i]*dispense[i];
								totalBills -= count[i]*dispense[i];

						 }
						 else {
							 System.out.println("Not enough money - cannot complete transaction");
						 }
					}
				    
				
				}
				else {
					System.out.println("Not enough money - cannot complete transaction");
					break;
				}
				}
			
			for (int i = 0; i < count.length; i++) {
					
					//printing the numbers of bills dispensed and total in dollars
					System.out.println(count[i]+ " bills $"+ (dispense[i]) + ". Total $"+(dispense[i] * count[i]));
					 
				}
			
			
	
			 return 1;
	 }
	 }
	
	 

