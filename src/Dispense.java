/**
 * @author Oanh Le
 * */

public class Dispense {

	/**
	 * Method Name: verifyMachineBalance
	 * Description: ATM dispenses money
	 * It will attempt to provide every customer with money using the largest available bill
	 * 
	 * @param: withdrawAmount, totalBills, bill, availableAmount
	 */

	public void dispenseMoney(float withdrawAmount, float totalBills, int[] bill, int[] availableAmount) {
		int amount, remain;
		String plural;
		for(int i = 0; i < bill.length; i++){
			if (withdrawAmount >= bill[i]){
				amount = (int) withdrawAmount / bill[i];
				remain = (int) withdrawAmount % (amount * bill[i]);

				availableAmount[i] -= amount;
				totalBills -= bill[i] * amount; 
				plural = ((amount > 1) ? "s" : "");
				System.out.println(amount + " bill" + plural + " $" + bill[i]);

				if (remain == 0){
					break;
				}
				else {
					withdrawAmount = remain;
				}
			}
			else {
				continue;
			}
		}
	}

}//end class
	
	 

