/**
 * 
 */
package Board;

/**
 * This is where all the logic for solving the current board state occurs.
 * @author dbuis
 *
 */
public class Solver {
	
	public Grid grid = new Grid();

	/**
	 * Generic constructor is just fine
	 */
	public Solver() {
		
	}
	
	/**checks for solvable things within an array of cells
	 * 
	 * @param array
	 * @return
	 */
	public boolean checkCellArray(Cell[] array){

		boolean foundACellToSolve=false;
		
		if (checkForHiddenSingles(array)){
			foundACellToSolve=true;
		}
		if (checkForNakedSingles(array)){
			foundACellToSolve=true;
		}
		
		return foundACellToSolve;
	}
	
	
	/**Looks for a lone number within the array.  If only instance of an integer appears
	 * it has to go in that cell
	 * 
	 * @param array
	 * @return
	 */
	private boolean checkForHiddenSingles(Cell[] array) {
		
		boolean foundACellToSolve=false;

		//iterate across numbers 1-9
		for (int i=1; i<=9; i++){
			int count = 0;
			Cell loneCell = null;
			
			//iterate over input array
			for(Cell cell: array){

				//if this value is still legal..
				if (cell.checkValue(i)){
					
					//iterate count
					count++;
					loneCell = cell;
				}
			}
			
			//how many cells can take that value?  null check is just in case
			if(count==1 && loneCell!=null){
				loneCell.setSolvedValue(i);
				foundACellToSolve=true;
			}
		}
		
		return foundACellToSolve;
		
	}

	/** Does what it says on the box.  Looks for a cell that has all but one option eliminated from it
	 * 
	 * @param array
	 * @return
	 */
	private boolean checkForNakedSingles(Cell[] array) {
		
		boolean foundACellToSolve = false;
		
		//initialize at 0 (an invalid sudoku answer
		int value=0;
		
		//iterate over array
		for(Cell cell:array){
			
			//if cell already solved, skip
			if(cell.isSolved()){
			
				//count of how many trues we find (how many valid numbers remain for this cell)
				int count=0;
				//iterate 1 through 9
				for(int i=1;i<=9;i++){
				
					//if that value is still good...
					if(cell.checkValue(i)){
						//increment count of valid options...
						count++;
						//and store it
						value=i;
					}
				}
			
				//if only one valid option remains in Cell... 
				if (count==1 && value!=0){ //value check is a fail safe sort of check.  I can't fathom a scenario where it might be relevant
				//then it is solved
					setSolvedCell(cell, value);
					foundACellToSolve = true;
				}
			}
		}
		
		return foundACellToSolve;
		
	}

	/** Set solution for this cell
	 * 
	 * @param solvedCell
	 * @param solution
	 */
	public void setSolvedCell(Cell solvedCell, int solution){
		
		if (!solvedCell.isSolved()){
			
			//remove value from all appropriate cells
			eliminateFromAll(solvedCell, solution);
			
			//set solution.  If done in the other order it breaks.
			solvedCell.setSolvedValue(solution);
			
		}
		
	}

	/**
	 * When a solution is found for a cell, that number cannot be in any cells in the same block, row, or column.
	 * Therefore they must be eliminated
	 * @param cell
	 * @param valueToEliminate
	 */
	
	private void eliminateFromAll(Cell cell, int valueToEliminate) {
		
		//first eliminate from row
		eliminateFromArray(grid.getRow(cell.getCoords().y), valueToEliminate);
		
		//then eliminate from column
		eliminateFromArray(grid.getColumn(cell.getCoords().x), valueToEliminate);
		
		//then eliminate from block
		eliminateFromArray(grid.getBlock(cell.getBlock()), valueToEliminate);
		
	}
	
	/** When number needs to be eliminated from a specific group of cells
	 * 
	 * @param cellArray
	 * @param solution
	 */
	private void eliminateFromArray(Cell[] cellArray, int valueToEliminate){
		for(Cell cell:cellArray){
			cell.eliminateValue(valueToEliminate);
		}
	}

}
	


