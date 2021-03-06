package chatApp;
import java.net.*;
import java.io.*;


public class Server {
	
	public static String decrypt(String text, int s) 
    { 
		char[] inputArray = text.toCharArray();
		int l = inputArray.length;
		char[] outputArray = new char[l]; 
		
		
        for (int i=0; i<l; i++) 
        { 
        	if(inputArray[i] != ' ') {
        		int salt= ( inputArray[i] - s + 97) % 26;
            	inputArray[i] += salt ;
            	outputArray[i] = inputArray[i];
        	} else {
        		outputArray[i] = inputArray[i];
        	}
        } 
        String result = new String(outputArray);
        return result; 
    } 

	public static void main(String[] args) {
		
		
		try (ServerSocket ss = new ServerSocket(3002)) {
			System.out.println("waiting for client");
			Socket s = ss.accept();
   
			
			System.out.println("Client connected");
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader bf = new BufferedReader(in); 
			
			String msg = "";
			while(!msg.equals("end")) {
				String str = bf.readLine();
				String inputValue[] = str.split("~");
				String userName = inputValue[0];
				msg = inputValue[1];
				String dValue = decrypt(msg , 5);
				System.out.println( userName +": " + dValue); 
				System.out.println("Encrypted Value : " + msg);
			}
		}
	}

}
