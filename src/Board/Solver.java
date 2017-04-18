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
	


