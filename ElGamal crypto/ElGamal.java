package lab6;

import java.util.*;

public class ElGamal{
	static Scanner in = new Scanner(System.in);
	
	public static void main(String [] args)
	{
		//Example public key
		//Alice's public key (24852977,2744,8414508)
		//long aliceP = 248529771;				//Alice's prime number
		//long aliceG = 27441;					//Alice's generator
		//long aliceGMP = 84145081;			//Alice's g^x modulo p
		
		long aliceP = in.nextLong();
		long aliceG = in.nextLong();
		long aliceGMP = in.nextLong();
		
		//Example cypher
		//Bob's cipher (15268076,743675)
		//long bobC1 = 152680761;				//Bob's c1				
		//long bobC2 = 7436751;				//Bob's
		
		long bobC1 = in.nextLong();
		long bobC2 = in.nextLong();

		for(int x=0; x<=aliceP; x++)			//Loop to run through the values 0 to p
		//As we know the value of g^x modulo p , we can find x (secret key) by brute force
			
			if(modPow(aliceG,x,aliceP) == aliceGMP)			//Send the generator, incremented secret key (i) and prime number to  modPow 
															//to check if the total equals = g^x modulo p (aliceGMP)
			{
				//If it equals, it means it's the secret key (i)
				System.out.println(modMult(modPow(bobC1,aliceP-1-x,aliceP),bobC2,aliceP));				//Send the values c1^p-1-x , c2 , p to get message (m)
				break;										//Break the loop once you got the secret key
			}
	}
	
	public static long modPow(long number, long power, long modulus)
	{
		//raises a number to a power with the given modulus
		//when raising a number to a power, the number quickly becomes too large to handle
		//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
		//however  you  want  the  algorithm  to  work  quickly - having  a  multiplication loop would result in an O(n) algorithm!
		//the trick is to use recursion - keep breaking the problem down into smaller pieces and use the modMult method to join them back together
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
		//multiplies the first number by the second number with the given modulus
		//a  long can  have  a  maximum  of  19  digits.  Therefore,  if  you're  multiplying two ten digits numbers the usual way, things will go wrong
		//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range 
		//however  you  want  the  algorithm  to  work  quickly - having  an  addition  loop would result in an O(n) algorithm!
		//the trick is to use recursion - keep breaking down the multiplication into smaller pieces and mod each of the pieces individually
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