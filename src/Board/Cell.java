package Board;

import java.awt.Point;

/**
 * This cell is one of 81 units on the whole board.  It keeps track of what possible numbers can still go in it, and
 * has the ability to look at groups of other cells to find out what options they might still have.
 * @author dbuis
 *
 */
public class Cell {
	/**
	 * 10 item array of booleans.  [0] denotes if this cell is solved.  [1-9] are initialized as true, as in, this number could go here.
	 * When that number is eliminated, the corresponding boolean is switched to false.
	 */
	private boolean[] solveArray;
	
	//Representation of coordinates of cell
	private Point coords;
	
	//what square group of 9 is this cell in.  
	private int block;
	
	/**
	 * generic constructor
	 * @param x
	 * @param y
	 */
	public Cell(int x, int y){
		solveArray = new boolean[] {false, true, true, true, true, true, true, true, true, true};
		setCoords(new Point(x,y));
		setBlock(calculateBlock(x,y));
		
	}


	/**
	 * Use coordinates to determine what portion of the board this cell resides in.
	 * @param x
	 * @param y
	 * @return
	 */
	private int calculateBlock(int x, int y) {
		
		//1-3 should give value of 1, etc.
		int horizontalVal = (int) Math.ceil(x/3.0);
		
		
		//1-3 should give value of 0, 4-6 -> 3, 7-9 ->6
		int verticalVal = 3*(int)Math.floor((y-1)/3.0);

		
		return horizontalVal+verticalVal;
	}


	public int getBlock() {
		return block;
	}


	private void setBlock(int block) {
		this.block = block;
	}


	public Point getCoords() {
		return coords;
	}


	private void setCoords(Point coords) {
		this.coords = coords;
	}
	
	/**
	 * is this cell solved?
	 * @return
	 */
	public boolean isSolved(){
		return solveArray[0];
	}
	
	/**
	 * is given value valid for this cell?
	 * @param valueToCheck
	 */
	public boolean checkValue(int valueToCheck){
		return solveArray[valueToCheck];
	}
	
	
	/**
	 * Used to set a final solved value for the cell
	 * @param solvedValue
	 */
	public void setSolvedValue(int solvedValue){
		scrubSolveArray(); //prep array for solved status
		solveArray[0]=true; //set cell solved to true
		solveArray[solvedValue]=true; //set final value to true
	}
	
	
	/**
	 * Used to eliminate a value from the cell.
	 * @param eliminatedValue
	 */
	public void eliminateValue(int eliminatedValue){
		solveArray[eliminatedValue]=false;
	}

/**
 * scrubs solve array ONLY upon solve of cell.  Sets all values to false.  that's probably a repeat for most of them,
 * but in cases such as a hidden single there will be multiple true values that need to be eliminated.
 */
	private void scrubSolveArray() {
		for (int i=0;i<10;i++){
			solveArray[i]=false;
		}
		
	}
	

}
