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
		System.out.println("Public key details");
		System.out.println("Enter prime number:");
		long aliceP = in.nextLong();
		System.out.println("Enter generator:");
		long aliceG = in.nextLong();
		System.out.println("Enter g^x modulo p:");
		long aliceGMP = in.nextLong();
		
		//Example cypher
		//Bob's cipher (15268076,743675)
		//long bobC1 = 152680761;				//Bob's c1				
		//long bobC2 = 7436751;				//Bob's
		
		//encoded message cipher
		System.out.println("\nCipher details:");
		System.out.println("Enter 1st number:");
		long bobC1 = in.nextLong();
		System.out.println("Enter 2nd number:");
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
