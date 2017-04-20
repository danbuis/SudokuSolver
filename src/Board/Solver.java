/**
 * 
 */
package Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		if (checkForNakedDoubles(array)){
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
	
	private boolean checkForHiddenDoubles(Cell[] array){
		boolean foundACellToSolve=false;
		
		//initialize arrayLists to hold locations of what Cells can take each number
		List<List<Cell>> candidateLocations = new ArrayList<List<Cell>>();
		for (int i=0; i<=8; i++){
			candidateLocations.add(new ArrayList<Cell>());
		}
		
		//iterate across numbers 1-9
		//trying to figure out how many of each number are left
		for (int i=0; i<=8; i++){
			//for each cell
			for(Cell cell:array){
				//if the number is a valid candidate
				if(!cell.isSolved()&&cell.checkValue(i+1)){
					//add this cell to the i-th list, so the 0th list holds all the cells that can take a 1.
					candidateLocations.get(i).add(cell);
				}
			}
		}
		
		//gotta keep track of what numbers only have 2 candidates
		ArrayList<Integer> listOfDoubleCandidates = new ArrayList<Integer>();
		for(int i=0;i<=8; i++){
			if (candidateLocations.get(i).size()==2){
				listOfDoubleCandidates.add(i+1);
			}
		}
		
		//At this point we have figured out how many times each number can go in the array
		//remove every List that does not have 2 items in it
		Iterator<List<Cell>> iterator = candidateLocations.iterator();
		while (iterator.hasNext()){
			//grab the next one
			List<Cell> nextList = iterator.next();
			//if it does not have exactly 2...
			if (nextList.size()!=2){
				//remove it
				iterator.remove();
			}
		}
		
		//check to see if 2 lists are equal, if they are, then those Cells are a match
		for (int outside=0; outside<=candidateLocations.size()-2; outside++){
			for (int inside=1; inside<=candidateLocations.size()-1; inside++){
				
				//if these two lists match
				if (candidateLocations.get(outside).equals(candidateLocations.get(inside))){
					//grab the two cells
					Cell cell1 = candidateLocations.get(outside).get(0);
					Cell cell2 = candidateLocations.get(outside).get(1);
					
					int val1=0;
					int val2=0;
					
					//process 2 cells
					for (int i=1; i<=9; i++){
						if(cell1.checkValue(i)&&cell2.checkValue(i)&& listOfDoubleCandidates.contains(i)){
							if(val1==0){
								val1=i;
							}else{
								val2=i;
							}
						}
					}//end for loop
					
					//TODO
					
				}//end if loop
			}//end inside Loop
		}//end outside loop
		
		
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
	
	
	/**
	 * looks for 2 cells in the array that can only take the same 2 values.  Eliminates those 2 from the others in the group.
	 * @param array
	 * @return
	 */
	private boolean checkForNakedDoubles(Cell[] array) {
		boolean foundACellToSolve = false;
		Cell cell1 = null;
		Cell cell2 = null;
		int val1=0;
		int val2=0;
		
		//structure to hold cells we are going to look at more in depth shortly
		List<Cell> cellsWithTwoLeft = new ArrayList<Cell>();
		
		//go through and only grab the ones that have 2 options left
		for (Cell cell:array){
			
			//can skip solved cells
			if (!cell.isSolved()){
				
				if(cell.howManyCandidatesRemain()==2){
					cellsWithTwoLeft.add(cell);					
				}		
			}//end of if cell solved loop	
		} //end of for each
		
		
		//now that array has been filtered to ONLY cells with 2, we can look through what we have to see if we have a match
		//double loop to check all pairs
		for (int outside=0; outside<=cellsWithTwoLeft.size()-2; outside++){
			for (int inside = outside+1; inside<=cellsWithTwoLeft.size()-1; inside++){
				
				//hey look, we have a match!  Lets do stuff
				if(cellsWithTwoLeft.get(outside).sameCandidatesRemain(cellsWithTwoLeft.get(inside))){
		
					//set matching cells for easy/clear reference later
					cell1 = cellsWithTwoLeft.get(outside);
					cell2 = cellsWithTwoLeft.get(inside);
					
					//find the 2 candidate values
					for(int i=1; i<=9;i++){
						if(cell1.checkValue(i)){
							if (val1==0){
								val1=i;
							}else val2=i;
						}
					}//end of for loop looking for 2 values
					
					
					/*For each cell in array...
					 * 1)Make sure it isn't one of the 2 in question
					 * 2)Eliminate the 2 values in the cell
					 */
					for(Cell cell:array){

						//if it doesn't equal cell1 and it doesn't equal cell2...
						if(!cell.equals(cell1)&&!cell.equals(cell2)){

							//eliminate the 2 values
							cell.eliminateValue(val1);
							cell.eliminateValue(val2);
						}
					}
					
					//successfully found a naked double, exit method gracefully and communicate success to caller function
					foundACellToSolve= true;
					return foundACellToSolve;
					
				}//end of "we have a match" loop
				
			}//end inside loop
		}//end outside loop
		
		
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
	


