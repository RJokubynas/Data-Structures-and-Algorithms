public class Stub {

	public static void main(String[] args) {
		
		FileIO reader = new FileIO();			//reader method from class FileIO
        //String[] inputs = reader.load(System.getProperty("user.dir") + "\\dictionary.txt");   	 //Reading the File as a String array (location is where the java/java class files are)
		
		//Scanner myscanner = new Scanner(System.in);
		//int items = myscanner.nextInt();
		//myscanner.nextLine();
		
		String[] contents = reader.load(System.getProperty("user.dir") + "\\dictionary.txt");				//fills the contents array with words from file
		int items = contents.length;																		//declares length of array
		
		for(int i=0; i<items; i++)																			//for loop to remove the newline from each word
			contents[i] = contents[i].substring(0,contents[i].length()-1);
		
		int size = 433117;																				//prime number of hashtable length 
		Solution mysolution = new Solution();																//instance of class Solution
		String[] hashtable = mysolution.fill(size, contents);												//fill the hashtable with elements of contents array
		HashTable mytable = new HashTable(hashtable);														//creates a HashTable mytable with contents of hashtable
		
		Solution mysolution2 = new Solution();		//prevents cheating by using memory
		
		for(int i=0; i<items; i++)																			//for loop that runs through the whole contents array
		{																									//shuffles the elements in contents array	
			int rand = (int)(Math.random()*items);															
			String temp = contents[i];																		
			contents[i] = contents[rand];
			contents[rand] = temp;
		}
		int total = 0;																						
		
		for(int i=0; i<items; i++)																			//runs through whole contents array
		{
			int slot = mysolution2.find(size, mytable, contents[i]);										//gets index of word contents[i] in HashTable mytable
			
			if(!hashtable[slot].equals(contents[i]))														//if the word in hashtable index slot does not equal to contents[i], give an error message
				System.out.println("error!");
		}
		System.out.println(mytable.gettotal());																//print the total 
	}
}

class HashTable {												//HashTable class used to create a HashTable
	private String[] hashtable;									//creates a string array
	private int total=0;										
	
	public HashTable(String[] input)							//Receives an array of words 
	{
		hashtable = input;										//which is declared to hashtable array
	}
	
	public boolean check(int slot, String check) {				//receives an index and word 
		if(hashtable[slot].equals(check))						//checks if this word is in the index of hashtable array
		{
			//System.out.println(hashtable[slot] + "  " + check);
			return true;
		}
		else
		{
			total++;											//else, increments total (Collision number?)
			return false;										//returns false if not found
		}
	}
	
	public int gettotal() {										//method to get total of (Collisions?)
		return total;
	}
}

class Solution {
	public int find(int size, HashTable mytable, String word) {
			
		String abc = "abcdefghijklmnopqrstuvwxyz";
		
		int wordVal = 0;
		for(int x=1; x<=word.length(); x++)
			wordVal += x*(int)modPow(27,abc.indexOf(word.charAt(x-1)),size);
		
		int modVal = 9973-(wordVal%9973);
		int indexOfMod = wordVal % size;
		
		if(mytable.check(indexOfMod, word))
		{
			//System.out.println("1st hash - found");
			return indexOfMod;
		}
		else
		{
			indexOfMod = (indexOfMod + modVal) % size;
			
			while(mytable.check(indexOfMod, word) == false)
			{
				indexOfMod = (indexOfMod + modVal ) % size;

			}
			//System.out.println("2nd hash - found");
			return indexOfMod;
		}
		

		//fill this in so as to minimize collisions 
		//takes in the HashTable object and the word to be found
		//the only thing you can do with the HashTable object is call "check"
		//this method should return the slot in the hashtable where the word is
		
		//return 0;
	}
	
	public String[] fill(int size, String[] array)
	{
		//takes in the size of the hashtable, and the array of Strings to be placed in the hashtable
		//this should add all the words into the hashtable using some system
		//then it should return the hashtable array 
		
		String[] hashtable = new String[size];
		String abc = "abcdefghijklmnopqrstuvwxyz";
		
		for(int i=0; i<size; i++)
			hashtable[i] ="";
		
		for(int i=0; i<array.length; i++)
		{
			int wordVal = 0;
			for(int x=1; x<=array[i].length(); x++)
				wordVal += x*(int)modPow(27,abc.indexOf(array[i].charAt(x-1)),size);			//find the value of word (27^(index of letter+1))
			
			int indexOfMod = wordVal % size;										//first hash
			int modVal = 9973-(wordVal%9973);
			
			if(hashtable[indexOfMod].equals(""))										//if first hash index is empty
				hashtable[indexOfMod] = array[i];									//add the word to it
			else
			{
				
				while(!(hashtable[indexOfMod].equals("")))							//while the slot in HashTable is occupied	
				{
					indexOfMod = (indexOfMod +modVal) % size;							//do the second hashing
					//modulus it by the size of the hashtable
				}
				hashtable[indexOfMod] = array[i];								//add the word to the double hashed index of the hashtable
			}
		}
		//System.out.println("Fill done!");

		return hashtable;
	}
	public static long modPow(long number, long power, long modulus)
	{
		if(power==0)
			return 1;
		else if (power%2==0) {
			long halfpower=modPow(number, power/2, modulus);
			return modMult(halfpower,halfpower,modulus);
		}else{
			long halfpower=modPow(number, power/2, modulus);
			long firstbit = modMult(halfpower,halfpower,modulus);
			return modMult(firstbit,number,modulus);
		}
	}
	
	public static long modMult(long first, long second, long modulus){
		if(second==0)
			return 0;
		else if (second%2==0) {
			long half=modMult(first, second/2, modulus);
			return (half+half)%modulus;
		}else{
			long half=modMult(first, second/2, modulus);
			return (half+half+first)%modulus;
		}
	}
}
