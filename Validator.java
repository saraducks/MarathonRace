package demofinal;

import java.util.Scanner;
/**
 * This will check the user input 
 * @author saraducks
 *
 */
public class Validator {
    public static int getInteger(Scanner sc){
    	boolean isValid = false;
    	int i =0;
    	while(isValid == false){
    		if(sc.hasNextInt()){
    			i = sc.nextInt();
    			isValid = true;
    		}
    		else{
    			System.out.println("Error!enter valid choice");
    		}
    		sc.nextLine();
    	}
        return i;
    }
	public static int getIntegerRange(Scanner sc,int min,int max){
		int i=0;
		boolean option = false;
		
		while(option == false){
			i = getInteger(sc);
			
			if(i>=min && i<= max){
				option = true;
			}
			else{
				System.out.println("Invalid choice.Enter the valid choice number");
				}
	        }
		return i;
	}
}