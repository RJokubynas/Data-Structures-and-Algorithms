import java.util.*;					//library utilities imported to use Scanner and HashSet
import java.util.regex.Pattern;		//utilities was not registering in Eclipse regex.Pattern , so it was imported exclusively

public class HangMan 				//class name
{
	//certain variables are initiated outside the method so they can be used and manipulated throughout
	static Scanner in = new Scanner(System.in);
	public static int count;				//counter for hidden letters
	public static String letter;			//guesses letter
	public static String word;				//word to guess
	public static String blockWord;			//hidden word
	public static int tries;				//amount of tries given
	public static HashSet<String> guess = new HashSet<String>();									//HashSet to contain already guessed letters
	public static Pattern regex = Pattern.compile("[0123456789$&+,:;=\\\\?@#|/'<>.^*()%!-]");		//Regular expression to recognize special characters and numbers

	public static void main(String [] args)
	{
		FileIO reader = new FileIO();			//reader method from class FileIO
        String[] inputs = reader.load(System.getProperty("user.dir") + "\\dictionary.txt");   	 //Reading the File as a String array (location changed to where the dictionary file was relocated)
        String x = "";							//will contain string for game continuation or termination
        boolean check = false;					//check for game continuation or termination
        boolean syntaxCheck = false;			//check for input validity
        boolean letterCheck = false;			//check if letter was guessed

        while(!check)														//while user wants to play (termination check)
        {
        	System.out.println("\nHANGMAN\n");								//game title
        	word = inputs[new Random().nextInt(inputs.length-1)];			//get a word from the inputs array using Random in the range of 0 to length of array - 1
        	blockWord = "";													//hidden word to guess
        	count = 1;														//counter to count the hidden letters (1 is 0 in this regard(for now))
        	tries = 7;														//amount of tries given

        	for(int i=0; i<word.length()-1; i++)
        		blockWord += "#";											//create the hidden word with # characters length of the word to guess

        	//System.out.println(word);  			//prints the word you're trying to guess
        	System.out.println("Word is:\n" + blockWord + "\nGuess a letter:");					//prints the hidden word to show length

        	while(count != 0 && tries > 0)				//loops until you guess a word or run out of tries (0 means no hidden letters left)
        	{
        		count = 1;								//restart count to default value
        		while(!syntaxCheck)						//loops until correct input
        		{
        			syntaxCheck = true;
        			letter = in.nextLine();
        			if(letter.length() > 1 || letter.length() == 0 || regex.matcher(letter).find() || guess.contains(letter))		//checks for correct input (1 character length | no empty string | no special characters or numbers | was not used)
        			{
        				syntaxCheck = false;
        				System.out.println("Wrong input! Please enter a letter:");
        			}
        		}
        		guess.add(letter);					//add the used letter to the HashSet
        		syntaxCheck = false;

            	letterCheck = wordCheck(letter);		//sends the guessed letter to wordCheck methdod to check if letter was found in the word letterCheck is true(found), false(not found)

            	if(letterCheck)																	//If you guessed right
            		System.out.println("You guessed correct!" + "\n" + blockWord);				//prints blocked word
            	else																			//If you guessed wrong
            	{
            		tries--;																	//reduces amount of left tries by 1
            		System.out.println("You guessed wrong." + "\n" + blockWord + "\nTries left: " + tries);	  //prints blocked word and amount of tries left
            	}
        	}

        	if(count == 0)																	//if count = 0, you've won the game
        		System.out.println("Congratulation! You've guessed the word.\n");
        	if(tries == 0)																	//if you ran out of tries, you've lost the game
        		System.out.println("Game over. You've been hanged.");

        	System.out.println("Do you want to play again? \n(Enter 'no' to quit or anything else to play again.)\n");		//print
        	x = in.nextLine();				//take input to decide game termination or continuation
        	if(x.equals("no"))				//if input was 'no', terminate
        	{
        		check = true;				//termination approved
        		System.out.println("\nTerminated");			//print
        	}
        }
		in.close();			//close input
	}

	public static boolean wordCheck(String letter)			//method that checks if the letter is contained in the word
	{
		boolean check = false;								//letter check boolean false by default

		for(int i=0; i<word.length()-1; i++)				//for loop that runs through the whole word so as to check repeating guessed letter
		{
			if(word.charAt(i) == letter.charAt(0))			//if the character at the current location in the word is same as the letter that was given by player
			{
				blockWord = blockWord.substring(0,i) + word.charAt(i) + blockWord.substring(i+1,word.length()-1);		//replace the hidden letter in blockWord with guessed letter
				check = true;																							//letter was matched, true
			}
			if(blockWord.charAt(i) == '#')					//if there's still hidden letters in blockWord
				count++;									//add 1 to counter
		}
		if(count==1)										//if no hidden letters found
			count = 0;										//make count 0 to imply that word was guessed

		if(check)											//if letter guess was correct,
			return true;									//return true to imply it

		return false;										//otherwise return false, implying no match was found
	}
}			//end