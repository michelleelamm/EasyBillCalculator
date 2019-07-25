import java.util.InputMismatchException;
import java.util.Scanner;

public class EasyBillCalculator {
	
	public static void main(String args[]) {
		calc();

		Scanner keyboard = new Scanner(System.in);
		while(true) {
			System.out.print("Would you like to calculate tax and tip from a new cost/price? (Yes/No) ");
			String newCalc="";
			boolean success = false;
			while(!success) {
				try{
					newCalc = keyboard.nextLine(); 
					success = true;
				}
				catch (InputMismatchException e) {
					System.out.print("Wrong input type. Please try again: ");
					keyboard.next();
					if (keyboard.hasNext()) {
						newCalc = keyboard.nextLine();
						success = true;
					}
				}
			}
			if (newCalc.equalsIgnoreCase("yes")){
				calc();
			}
			else if (newCalc.equalsIgnoreCase("no")){
				System.out.println("Thank you for using EasyBillCalculator. Have a great day!");	
				keyboard.close();
				System.exit(0);
			}
		}
	}
	
	public static void calc() {
		Scanner keyboard = new Scanner(System.in);
		
		//Ask user for initial cost/price they want to calculate tax on, and how much sales tax is
		System.out.print("Enter a cost/price: ");
		float cost = 0.00f;
		boolean success = false;
		while(!success) {
			try{
				cost = keyboard.nextFloat(); 
				success = true;
			}
			catch (InputMismatchException e) {
				System.out.print("Wrong input type. Please try again: ");
				keyboard.next();
				if (keyboard.hasNextFloat()) {
					cost = keyboard.nextFloat();
					success = true;
				}
			}
		}
		
		System.out.print("Enter a sales tax %: ");
		float tax = 1.0f;
		success = false;
		while(!success) {
			try {
				tax = keyboard.nextFloat();
				success = true;
			}
			catch(InputMismatchException e) {
				System.out.print("Wrong input type. Please try again. ");
				keyboard.next();
				if(keyboard.hasNextFloat()) {
					tax = keyboard.nextFloat();
					success = true;
				}
			}
		}
		
		//Calculate tax and add to total cost
		float totalTax = (cost*tax) / 100;
		System.out.printf("Calculated Tax: %.2f", totalTax);
		System.out.println();
		float total = cost+totalTax;
		System.out.printf("Total: %.2f", total);
		System.out.println();
		
		//Ask user if they want to add tip
		System.out.print("Did you want to add tip? (Y/N) ");
		keyboard.nextLine();  //clears buffer
		
		char tipOption = keyboard.next().charAt(0);
		boolean tipSuccess = false;
		do{
			if(tipOption == 'Y' || tipOption == 'y') {
				System.out.print("What percentage of tip would you like to add? " );
			
				float tipPercent = 0.0f;
				success = false;
				while(!success) {
					try{
						tipPercent = keyboard.nextFloat(); 
						success = true;
					}
					catch (InputMismatchException e) {
						System.out.print("Wrong input type. Please try again: ");
						keyboard.next();
						if (keyboard.hasNextFloat()) {
							tipPercent = keyboard.nextFloat();
							success = true;
						}
					}
				}
				float calcTip = (total*tipPercent) / 100;
				System.out.printf("Calculated tip: %.2f", calcTip);
				System.out.println();
			
				float totalPlusTip = total + calcTip;
				System.out.printf("Total with tip: %.2f", totalPlusTip);
				System.out.println();
				tipSuccess = true;
			}
			else if (tipOption == 'N' || tipOption == 'n') {
				tipSuccess = true;
			}
			else {
				tipSuccess = false;
				System.out.print("Please try again: ");
				tipOption = keyboard.next().charAt(0);
			}
		}while(!tipSuccess);
	}
	
}
