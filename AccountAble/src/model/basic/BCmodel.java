package model.basic;


public class BCmodel {
	//Calculates how much is taken out depending on percentage and returns value.
	public static double calculate(double num2, String operator) {
		switch (operator) {
		case "12%":
			return num2 * .12 ;
		case "8%":
			return num2 * .08 ;
		case "4%":
			return num2 * .04;
		case "25%":
			return num2 * .25 ;
		case "CE":
			return 0;
			
		default:
			return 0;
		
		}
		
	}


	//Calculates how much is left over after percentage is taken out returns value. 
	public static double payCalc(double num3, String operator) {
		switch (operator) {
		case "12%":
			return num3 - (.12 * num3)  ;
		case "8%":
			return num3 - (.08 * num3) ;
		case "4%":
			return num3 - (.04 * num3);
		case "25%":
			return num3 - (.25 * num3) ;
		case "CE":
			return 0;
			
		default:
			return 0;
		
	}

}
}
