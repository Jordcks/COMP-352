import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Stack;

public class Version2 {
	/**
	 * Ierative method to solve board
	 * @param board 2D array of integers
	 * @param start_pos	Starting position
	 * @return	boolean value if valid or invalid
	 */
	public static boolean iterative(int[][] board, Position start_pos) {
		//creation fo two stack to store positions
		Stack<Position> stack = new Stack<Position>();
		Stack<Position> positions = new Stack<Position>();
		
		//Addinf starting position to stack
		stack.push(start_pos);
		
		//Checking wheter current position is not zero
		while (!stack.empty() && board[stack.peek().getRow()][stack.peek().getColumn()] != 0) {
			
			//Removing position from stack and assigning it to Position object
			Position p = stack.pop();
			
			//current value at board location
			int i = board[p.getRow()][p.getColumn()];
			
			//Creation of 4 possible new positions with desired value
			Position up = new Position(p.getRow() - i, p.getColumn());
			Position left = new Position(p.getRow(), p.getColumn() - i);
			Position down = new Position(p.getRow() + i, p.getColumn());
			Position right = new Position(p.getRow(), p.getColumn() + i);
			
			//Checking the North
			if (up.isInside(board.length) && !positions.contains(up)) {
				stack.push(up);
				positions.push(up);
			}
			
			//Checking the Left
			if (left.isInside(board.length) && !positions.contains(left)) {
				stack.push(left);
				positions.push(left);
			}
			
			//Checking the South
			if (down.isInside(board.length) && !positions.contains(down)) {
				stack.push(down);
				positions.push(left);
			}
			
			//Checking the Right
			if (right.isInside(board.length) && !positions.contains(right)) {
				stack.push(right);
				positions.push(right);
			}
			
			//Checking wheter the stack is empty
			if (stack.empty()) {
				return false;
			}
		}
		
		return true;				//value 0 is found
	}
	

	public static void main(String[] args) {
		//Declaring of Objects and Variable
		PrintWriter pw,time2; 
		int size, specialx,specialy; 
		int[][] board;
		boolean result;
		
		//Initialization of Variable
		pw=null;
		time2=null;
		
		try {
			pw=new PrintWriter(new FileOutputStream("Version2.txt"));				//Creating Version1.txt to store result for every board
			time2=new PrintWriter(new FileOutputStream("Time2.txt"));				//Creating Time2.txt to store execution time of each board
		} catch (FileNotFoundException e) {
			System.out.println("File cannot be opened.");
			pw.close(); time2.close();
			System.exit(0);
		}
		
		//Creating board of incrementing size of 5 to 20
		for ( size=5 ; size<=20 ;size++) {
			result=false;
			board=new int[size][size];
			
			//Generating random number of size-1 to fill the loop
			for (int i=0;i<size;i++) {
				for (int j=0; j<size;j++) {
					board[i][j]=((int) (Math.random()*(size-1))+1);
				}
			}
			
			//Generating value two random position for value 0
			specialx=(int) (Math.random()*size);
			specialy=(int) (Math.random()*size);
			
			//Initialising value zero at dersired position
			board[specialx][specialy]=0;
			
			//Creating start position object
			Position p=new Position(0,0);
			
			long startTime= System.nanoTime();		//Start of exexution time

			result=iterative(board, p);				//Calling the iterative method with Board and Position
			
			long stopTime = System.nanoTime();		//Stop of exexution time
			
			time2.println("Size: "+size+" & Execution Time: "+(stopTime-startTime));			//Writing calculated execution time to respective file
			
			//Output and writing expected result to file
			if (result==true) {
				System.out.println("Size: "+size+" Found");
				pw.println("Size: "+size+" Found ");
			} else {
				System.out.println("Not found");
				pw.println("Size: "+size+" Not Found ");
			}
		}
		
		//End of Program
		pw.close();
		time2.close();
				
	}

}
