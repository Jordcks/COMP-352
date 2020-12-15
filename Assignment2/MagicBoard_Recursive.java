import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Class Position
 * @author jordan
 *
 */
class Position {
	//Private attributes
	private int row;
	private int column;
	
	/**
	 * Parameterized Constructor
	 * @param row stores row index
	 * @param col stores column index
	 */
	public Position(int row, int col) {
		this.row = row;
		this.column = col;
	}
    
	/**
	 * Check equality of two positions
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		} else if (other.getClass() != getClass()) {
			return false;
		} else {
			Position p = (Position) other;
			return (row == p.getRow() && column == p.getColumn());
		}
	}
	
	//Mutuator Method
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	/**
	 * Check whether a position is with the size of board
	 * @param size board size
	 * @return boolean value if valid
	 */
	public boolean isInside(int size) {
		// size is length of one side of the square board
		if (row >= size || column >= size || row <= -1 || column <= -1) {
			return false;
		} else {
			return true;
		}
	}
}


public class Magic {
	/**
	 * Recursive method to solve th board
	 * @param board 2D array of integers
	 * @param c_row	Starting row position
	 * @param c_col	Starting column position
	 * @param p	ArrayList of Position to store positions
	 * @return	boolean value if solved.
	 */
	public static boolean recursive(int[][] board, int row, int col, ArrayList<Position> position) {
		int n;
		
		//Cehck wheter current row and current column index is valid
		if (row >= board.length || col >= board.length || row <= -1 || col <= -1) 
            return false;
        
		//Check wheter the ArrayList already contains the position
		if (position.contains(new Position(row, col)))
            return false;
        
		//Add the position to the ArrayList
		position.add(new Position(row, col));
        
		//current value at board location
		n = board[row][col];
		
		//Checking wheter current position contains value zero
		if (board[row][col] == 0)
            return true; // reached 0
		if (recursive(board, row + n, col, position)) 			//Call to the North
            return true;
		if (recursive(board, row - n, col, position))			//Call to the South
            return true;
		if (recursive(board, row, col + n, position)) 			//Call to the Right
            return true;
		if (recursive(board, row, col - n, position)) 			//Call to the Left
            return true;
        
		return false;
	}
	
	public static void main(String[] args) {
		//Declaring of Objects and Variable
		PrintWriter pw, time1;
		int size, specialx,specialy; 
		int[][] board;
		boolean result;
		
		//Initialization of Variable
		pw=null;
		time1=null;
		
		
		try{
			pw=new PrintWriter(new FileOutputStream("Version1.txt"));				//Creating Version1.txt to store result for every board
			time1=new PrintWriter(new FileOutputStream("Time1.txt"));				//Creating Time1.txt to store execution time of each board
		} catch (FileNotFoundException e) {
			System.out.println("Files cannot be opened.");								
			pw.close(); time1.close();
			System.exit(0);
		}
		
		//Creating board of incrementing size of 5 to 20
		for ( size=5 ; size<=20 ;size++) {
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
			
			//Creation of ArrayList
			ArrayList<Position> p = new ArrayList<Position>();
			
			long startTime= System.nanoTime();		//Start of exexution time

			result=recursive(board, 0,0, p);		//Calling recursive method with Board, Position and ArrayList
			
			long stopTime = System.nanoTime();		//Stop of exexution time
			
			time1.println("Size: "+size+" & Execution Time: "+(stopTime-startTime));				//Writing calculated execution time to respective file
			
			//Output and writing expected result to file
			if (result==true) {
				System.out.println("Size: "+size+" Found "+ result);
				pw.println("Size: "+size+" Found ");								
			} else {
				System.out.println("Size: "+size+" Not found");
				pw.println("Size: "+size+" Found ");
			}
		}
		
		//End Of Program
		pw.close();
		time1.close();
	}
	
}

