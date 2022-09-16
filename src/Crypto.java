// Final Assignment for CMSC 
// We will be using the Caeser Cipher which replaces every character with a character 3 places after

// Imported packages
import java.util.Random;

public class Crypto {
	// Global variables
	private String userInput; // Will store userInput
	private String ctext = new String(); // Will to ciphered text (ie gibberish) might have to initialize with ctext = new String();
	private String dtext = new String(); // Will hold deciphered text
	private String[] cryptobox = new String[2]; // where 0 is key and 1 is the ctext
	// Constants
	
	// Constructor will initialize userInput after the user enters it.
	public Crypto(String userInput) {
		this.userInput = userInput;
	}
	
	// will take user input and then convert it to gibberish
	// Will also create a unique key
	// Cipher algorithm will be in here
	// Input => userInput
	// Output => unique key and gibberish
	public String cipher() {
		// For loop to loop all the characters of entered string
		for (int i=0; i < userInput.length(); i++) {
			char character = userInput.charAt(i);
			
			// Lets add the character to 3 to replace it with a character that is 3 places after
			character += 3;
			ctext += character;
		}
		
		// Call the function strData
		strData(ctext);
		
		return ctext;
		
	}
	// Will take unique key and turn gibberish back into a text
	// Decipher algorithm will be in here
	// Input => Gibberish & key
	// output=> Original text
	public String decipher() {
		for (int i=0; i < ctext.length(); i++){
            char character = ctext.charAt(i);
            
            // lets minus 3 characters from our ciphered text and 
            character -= 3;
            dtext += character;
        }
	
		return dtext;
		
	}
	
	// Make unique key
	// Which will be used to map key and gibberish text
	public String genKey() {
		// create a string of all characters
	    String character = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // Our key will be 8 characters length
	    int length = 7;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(character.length());

	      // get character specified by index
	      // from the string
	      char randomChar = character.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }

	    String syskey = sb.toString();
	    
	    return syskey;
	}
	
	// Check whether the array is empty
	// If the array is not empty the user can show encrypted message and decipher message
	// If not empty an error will result
	public boolean isEmpty() {
		if (cryptobox[0] != null) {
			return false;
		}
		
		else 
			return true;	
	}
	
	// Store data into cryptobox array
	public void strData(String text) {
		// Lets generate a unique key to go with ciphered text
				String usrkey = genKey();
				
				// Store ciphered text and key pair into an array
				cryptobox[0] = usrkey;
				cryptobox[1] = text;
	}
	// This method will provide the key and be used for authentication purposes
	public String getKey() {
		return cryptobox[0];
	}
	// This method will provide the gibberish code that is stored in array
	public String getCrypt() {
		return cryptobox[1];
	}

}
