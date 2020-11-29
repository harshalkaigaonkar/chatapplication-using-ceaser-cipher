package chatApp;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static String encrypt(String text, int s) 
    { 
		char[] inputArray = text.toCharArray();
		int l = inputArray.length;
		char[] outputArray = new char[l]; 
		
        for (int i=0; i<l; i++) 
        { 
        		int salt = ( 90 - s + 18 - 28 ) % 26;
            	inputArray[i] -= salt ;
            	outputArray[i] = inputArray[i];
        } 
        String result = new String(outputArray);
        return result; 
    } 

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Socket s = new Socket("localhost", 3002);
  
       
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		System.out.print("please enter a user name :");
		String name = scan.nextLine();
		
		String input = "";
		while(!input.equals("end")) {
			System.out.print("enter message: ");
			input = scan.nextLine();
			// encryption method call
//			int i =4;
			String eValue = encrypt(input , 5);
			String value = name + "~" + eValue;
			pr.println(value);
			pr.flush();
		}
	}

}
