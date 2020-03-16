package financialTracker;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class testDriver {
	public static void main(String[] args) {
		try {
			Account scotia11 = new Account(1);
			System.out.println(scotia11.buyerTest());
			System.out.println(scotia11.categoryTest());
			System.out.println(scotia11.transactionTest());
			System.out.println(scotia11.getBalance());
		}
		catch(IOException e){
			System.out.println(e); 
		}
		catch(InvalidFormatException f) {
			System.out.println(f); 
		}
		
	}
}
