package cs210Lab11;

import java.util.*;

public class Lab11 {
	
	public static void main(String []args)
	{
		int dice = 0;
		boolean block = false;
		boolean result = false;
		double matchRate = 0;
		
		for(int i=0; i<604800*52; i++)	//simulates for about a year
		{
			if(i%3 == 0)		//when 3 seconds have passed
				dice = rollDice();		//roll the dice
			
			if(i%600 == 0)		//when 10 minutes have passed
			{
				block = true;		//block was found
				
				result = checkVal(block,dice);		//call method that checks if block is true 
			}
			
			if(result)
				matchRate++;
			
			result = false;
		}
		
		System.out.println(matchRate/(52*7));
		System.out.println(matchRate/(52*7*24));
		System.out.println(matchRate/(52*7*24*60));
		System.out.println(matchRate/(52*7*24*60*60));
		
	}
	
	public static int rollDice()
	{
		Random r = new Random();

		return r.nextInt(6) + 1;	//Rolls a dice valued 1 to 6
	}
	
	public static boolean checkVal(boolean block, int dice)
	{
		if(!block)
			return false;
		
		if(block == true && dice != 6)
			return false;
		
		if(block == true && dice == 6)
			return true;
		
		return false;
	}
}
