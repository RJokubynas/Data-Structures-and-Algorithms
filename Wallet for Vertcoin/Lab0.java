import java.util.*;
import java.lang.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Lab0 {

	public static void main(String[] args) throws NoSuchAlgorithmException 
	{
		int num = 64;
		
		String wallet = "80" + getRandomHexString(num);
		
		String wallet256 =  SHA256.sha256(SHA256.sha256(wallet));
		
		String wallet256Trim = wallet256.substring(0,8);
		
		wallet = wallet + wallet256Trim;
		
		BigInteger decVal = new BigInteger(wallet,16);
		
		System.out.println(DecToBase58(decVal));
		
		
	}

	
	 public static String getRandomHexString(int numchars){
	        Random r = new Random();
	        StringBuffer sb = new StringBuffer();
	        while(sb.length() < numchars){
	            sb.append(Integer.toHexString(r.nextInt()));
	        }

	        return sb.toString().substring(0, numchars);
	    }
	 
	 
	 public static String DecToBase58(BigInteger b)
	 {
		 
		BigInteger baseDiv = new BigInteger("58");
		BigInteger num = new BigInteger("0");
		String base58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
		String val = "";
		int pos = 0;
		
		while(!b.equals(BigInteger.ZERO))
		{
			num = b.mod(baseDiv);
			pos = num.intValue();
			val = base58.substring(pos,pos+1) + val;
			b = b.divide(baseDiv);
		}
		return val;
	 }
}
