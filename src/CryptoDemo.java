import java.util.Scanner;
import java.util.InputMismatchException;

public class CryptoDemo {
	// Create an object array to hold the obj to be created below
	// I chose this method as creating a global object would have bee problematic
	// We are only storing one object at this time
	private static Crypto[] objArray = new Crypto[1];

	public static void main(String[] args) {
		menu();
	}
	public static void menu() {
		try {
			// While loop so that the menu appears
			while (true) {
				// Show menu
				System.out.println("==================================== CRYPTO APP ============================");
				System.out.println("Welcome to the crypto app. Here are your options:");
				System.out.println("1. Encrypt a string.");
				System.out.println("2. Show encrypted message.");
				System.out.println("3. Decrypt the stored message.");
				System.out.println("4. Exit the program.");
				System.out.println();
				
				// Prompt user
				System.out.print("Make a selection: ");
				// Create scanner object for user to get input
				Scanner input1 = new Scanner(System.in);
				int choice1 = input1.nextInt();
				
				// Switch for menu selection 
				switch(choice1) {
					case 1:
						System.out.println("============================================================================");
						toCipher();
						System.out.println("============================================================================");
						break;
					case 2:
						System.out.println("============================================================================");
						showCipher();
						System.out.println("============================================================================");
						break;
					case 3:
						System.out.println("============================================================================");
						toDecipher();
						System.out.println("============================================================================");
						break;
					case 4:
						System.out.println("============================================================================");
						System.out.println("Program is exiting.....");
						System.out.println("Goodbye!!!");
						System.out.println("============================================================================");
						System.exit(0);
						break;
					default:
						System.out.println("Invalid entry. Try again");
				}
				// If choice1 does not match our expected entries continue with loop
				// else break to continue with action
				if (choice1 == 1 && choice1 == 2 && choice1 == 3 && choice1 ==4) {
					break;
				}
				else continue;
			}
		}
		// Catch the inputMismatchError when user enters an invalid input
		catch (InputMismatchException e) {
			System.out.println("You entered an invalid entry.");
			menu();
		}
				
	}
	public static void toCipher() {
		// Create a scanner object to get user input
		Scanner input = new Scanner(System.in);
		
		// Prompt user for String to be ciphered
		System.out.print("Enter the string you would like to be ciphered: ");
		String usrInput = new String();
		usrInput=input.nextLine();
		System.out.println();
		// Show the user what they entered
		System.out.println("You entered: " + usrInput);
		System.out.print("Enter 'y' to continue or 'n' to enter another string: ");
		// Create a new Scanner obj to clear the input variable
		input = new Scanner(System.in); 
		// Get the first character as Java does not have nextChar built-in
		char choice = input.nextLine().charAt(0);
		choice = Character.toLowerCase(choice);
		
		// Choice switch statement
		switch (choice) {
			case 'y':
				// Break 
				break;
			case 'n':
				// Loop through until the user is satisfied with entered input 
				// and then goes to y
				while (choice == 'n') {
					System.out.print("Enter the string you would like to be ciphered: ");
					input = new Scanner(System.in); // Create a new Scanner obj to clear the input variable
					usrInput = input.nextLine();
					System.out.println();
					System.out.println("You entered: " + usrInput);
					System.out.print("Enter 'y' to continue or 'n' to enter another string: ");
					input = new Scanner(System.in); // Create a new Scanner obj to clear the input variable
					choice = input.nextLine().charAt(0);
					choice = Character.toLowerCase(choice);
				}
				break;
			default:
				while (choice != 'y' && choice != 'n') {
					System.out.print("You entered an invalid entry. Please try again: ");
					input = new Scanner(System.in);
					choice = input.nextLine().charAt(0);
					choice = Character.toLowerCase(choice);
				}
				break;
		}
		// Pass the string input to the obj object
		Crypto obj = new Crypto(usrInput);
		objArray[0] = obj;
		// Run the method cipher on the entered text
		obj.cipher();
		System.out.println("Encryption is successful.");
		// Print out the cipher key
		String key = objArray[0].getKey();
		
		System.out.println();
		System.out.println("The string has been ciphered. Hold onto the generated key: " + key);
		System.out.println("This key will help you decipher the generated text");
	} // End of toCipher method
	
	public static void showCipher() {
		try {
			
			String key = new String();
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter the key to show encrypted text: ");
			key = input.nextLine();
			
			if (objArray[0].isEmpty() == false) {
				if (key.equals(objArray[0].getKey()) == true) {
					String ctext = objArray[0].getCrypt();
					System.out.println("The encrypted message is:");
					System.out.printf("%s%n",ctext);
				}
				else {
					// Let's give the user 2 more opportunities to enter a correct key
					for (int i = 1; i < 3; i++) {
						System.out.print("Key does not match.The input is invalid. Try again: ");
						input = new Scanner(System.in);
						key = input.nextLine();
						
						if (key.equals(objArray[0].getKey())){
							break;
						}
						else continue;
						
					}
					// what happens after breaking
					if (key.equals(objArray[0].getKey()) == true) {
						String ctext = objArray[0].getCrypt();
						System.out.println("The encrypted message is:");
						System.out.printf("%s%n",ctext);
					}
					else {
						System.out.println("You have exceeded the number of tries.");
						System.out.println("Back to main menu .......");
						menu();
					}	
				}
			}
		}
		catch (NullPointerException e) {
			System.out.println("There is no entry to check your key against.");
			System.out.println("Back to main menu ......."); 
			//System.exit(0);
			menu();
		}
	} // End of showCipher method
	
	public static void toDecipher() {
		try {
			Scanner input = new Scanner(System.in);
			// Prompt the user to whether they would like to decipher the entered text
			System.out.print("Enter the key for the text to decipher: ");
			
			// Create a scanner object to get input
			String key = input.nextLine();
			String dtext = new String();
			// Check that the first entry is not null
			if (objArray[0].isEmpty() == false) {
				if (key.equals(objArray[0].getKey())) {
					System.out.println("Decryption is successful. The decrypted message is:");
					
					// Perform the decryption
					dtext = objArray[0].decipher();
					
					System.out.printf("%s%n", dtext);
					
				}
				else
				{
					// Let's give the user 2 more opportunities to enter a correct key
					for (int i = 1; i < 3; i++) {
						// Decryption unsuccessful
						System.out.print("Decryption is unsuccessful.The input is invalid. Try again: ");
						input = new Scanner(System.in);
						key = input.nextLine();
						
						if (key.equals(objArray[0].getKey())){
							break;
						}
						else continue;
						
					}
					
					if (key.equals(objArray[0].getKey())) {
						// Decryption confirmation
						System.out.println("Decryption is successful. The decrypted message is:");

						// Perform the decryption
						dtext = objArray[0].decipher();
						// Provide user with decrypted message
						System.out.printf("%s%n", dtext);
						
					}
					else {
						System.out.println("You have exceeded the number of tries.");
						System.out.println("Back to main menu .......");
						menu();
					}
				}		
			}
		} catch(NullPointerException e) {
			System.out.println("There is no entry to check your key against.");
			System.out.println("Back to main menu ........");
			menu();
		}
	}// End of toDecipher method	
} // End of CryptoDemo method
