import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Version1 {
	/*
     * Find all binary strings that can be formed by replacing *
     * @param pattern binary String
     * @param index start position
     * @param pw PrintWriter object
     */
	static void RevealStr(char line[], int index, PrintWriter pw){
	      
	      // Checks if current index is equals to length of the character array
		  if(index == line.length){
	         // Displays the binary string
			 pw.println(line);
			 return;
		  }
	  
	      // Checks if current index position character is a '*' character
		  if(line[index] == '*')	 {
			// Assigns '0' at current index position to replace '*'
	      	line[index] = '0';
	      	
	      	RevealStr(line, index + 1,pw);
	      	
	    	line[index] = '1';
	      	
	      	RevealStr(line, index+ 1,pw);
	  
	      	// To backtrack as string is passed by reference to the function
	      	// assigns the '*' at current index position
	      	line[index] = '*';
		  }// End of if condition
		  else {// Otherwise current index position does not contains '*' character
			  // Recursively calls the method by increasing the current index position by one
			  RevealStr(line, index+1,pw);      
		  }
	}// End of method
	
	
	public static void main(String[] args) {
		//Declaring of Objects and Variable
		PrintWriter pw, time1;
		String result;
		char[] array;
		int length,num;
		
		//Initialization of Variable
		pw=null; 
		time1=null;
		result="";
		length=0;
		
		try {
			time1=new PrintWriter(new FileOutputStream("Time1.txt"));			//Creating Time1.txt to store execution time of each string
			pw=new PrintWriter(new FileOutputStream("Version1.txt"));			//Creating Version1.txt to store binary string without '*' chracter
			
			//Looping from 2 to 100 *
			for (int n=2;n<=26;n+=2) {
				//Initialization of Variables
				length=0;
				result="";
				num=0;
				
				while (length<n && n>length) {
					num=(int) (Math.random()*3);		//Generating random number between 0 to 2
					if (num==2) {
						result=result+"*";				//If generated number is equal to 2, remplace with *
						length++;					//counting number of *
					}else 
						result=result+ Integer.toString(num);
				}
				
				array=result.toCharArray();			//Converting generated string to an array of characters
				
				pw.println("String "+result);			//Writing origniated string to file
				
				long startTime= System.nanoTime();		//Start of exexution time

				RevealStr(array, 0,pw);					//Calling RevealStr with string and PrintWriter object

				long stopTime = System.nanoTime();		//Stop of exexution time

				time1.println( "Execution Time: "+(stopTime-startTime));		//Writing calculated execution time to respective file
				
				pw.println();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error! Program cannot create file."
					+ "\nProgram will exit.");
			time1.close();
			pw.close();
			
		}
		
		//End of Program meesage
		System.out.println("Program has generated a maximum string with "+ length+"*.");
		time1.close();
		pw.close();
		
	}
	
	 

}
