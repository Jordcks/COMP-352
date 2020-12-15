import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Stack;

public class Version2 {
	
    /*
     * Find all binary strings that can be formed by replacing *
     * @param pattern binary String
     * @param pw PrintWriter object
     */
	public static void RevealStr(String pattern,PrintWriter pw){
            // create an empty stack
            Stack<String> stack = new Stack();
            stack.push(pattern);    // push the pattern into the stack

            int index;

            // loop till stack is empty
            while (!stack.empty())
            {
                    // pop string from stack and process it
                    String element = stack.pop();

                    // index stores position of first occurrence of * pattern in curr
                    if ((index = element.indexOf('*')) != -1)
                    {
                            // replace '*' with 0 or 1 and push it to the stack
                            for (char ch = '0'; ch <= '1'; ch++)
                            {
                                    element = element.substring(0, index) + ch +
                                                    element.substring(index + 1);
                                    stack.push(element);
                            }
                    }

                    // If no wildcard pattern is found, print the string
                    else
                    	pw.println(element);    
           }
    }
            
	public static void main(String[] args) {
		//Declaring of Objects and Variable
		PrintWriter pw, time2;				
		String result;
		int length,num;
		
		//Initialization of Variable
		pw=null; 
		time2=null;
		length=0;
		
		try {
			time2=new PrintWriter(new FileOutputStream("Time2.txt"));			//Creating Time2.txt to store execution time of each string
			pw=new PrintWriter(new FileOutputStream("Version2.txt"));			//Creating Version2.txt to store binary string without '*' chracter
			
			//Looping from 2 to 100 *
			for (int n=2;n<=26;n+=2) {
				//Initialization of Variables
				num=0;
				length=0;
				result="";
				
				while (length<n && n>length) {
					num=(int) (Math.random()*3);		//Generating random number between 0 to 2
					if (num==2) {
						result=result+"*";			//If generated number is equal to 2, remplace with *
						length++;					//counting number of *
					}else 
						result=result+ Integer.toString(num);
				}
				
				pw.println("String "+result);			//Writing origniated string to file
				
				long startTime= System.nanoTime();		//Start of exexution time

				RevealStr(result, pw);					//Calling RevealStr with string and PrintWriter object

				long stopTime = System.nanoTime();		//Stop of exexution time

				time2.println( "Execution Time: "+(stopTime-startTime));		//Writing calculated execution time to respective file
				
				pw.println();				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error! Program cannot create file."
					+ "\nProgram will exit.");
			pw.close();
			time2.close();
		}
		
		//End of Program meesage
		System.out.println("Program has generated a maximum string with "+ length+"*.");
		time2.close();
		pw.close();
		
	}
}
