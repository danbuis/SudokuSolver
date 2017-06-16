/**
 * 
 */
package Board;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;




/**
 * This is where all the logic for solving the current board state occurs.
 * @author dbuis
 *
 */
public class Solver {
	
	private static final int drawWidth = 70;

	public int cycles=10;
	private boolean isSetValuesToggles = false;
	private boolean clearValues = false;
	private boolean puzzleSolved = false;
	
	public Grid grid = new Grid();

	/**
	 * Generic constructor is just fine
	 */
	public Solver() {
		
	}
	
	public boolean solveGrid(){
		int cycles=this.cycles; //keep track of cycles so that we don't get caught in an infinite loop, this will give an exit point
		boolean madeProgress = true;

		while(cycles>0 && madeProgress){

			//did we make any progress?
			madeProgress = checkAll();
			
			//increment counter
			cycles--;

			
		}
		
		System.out.println("ran through "+(this.cycles-cycles)+" cycles");
		grid.printGrid();
		if(!isPuzzleSolved()){
			System.out.println("attempting recursion");
		
			//solveRecursion(0, 0);
		
			grid.printGrid();
		}
		
		return isPuzzleSolved();
	}
	
	public void solveRecursion(int x, int y){
		int numberToTry = 1;
		Cell cell=grid.getCell(x, y);
		if (x==3 && y==1 && numberToTry==7){
			@SuppressWarnings("unused")
			int temp = 3;
			System.out.println("stuff");
		}
		
		
		while(numberToTry<10){
			//if this number can go into this cell
			if(cell.checkValue(numberToTry)){
				
				//first unsolve it since it could have been recursively solved earlier
				this.unsolveCell(cell);
				//then set it to a new solved solution
				this.setSolvedCell(cell, numberToTry, SolveMethod.RECURSION);
				
				if(x==8 && y==8){
					//reached the end and successfully slotted in a value... don't try to recurse again
					break;
				}
				
				//on to the next cell
				if(x!=8){
					solveRecursion(x+1, y);
				}else solveRecursion(0,y+1);
				
				/*if(!isPuzzleSolved()){
					grid.unsolveCell(cell);
				}*/
				
			}//end if loop
			
			
			
			numberToTry++;
		}//end while
		
		
		//if the puzzle is solved, return without unsolving.  First checks the boolean value, if it is false, it evaluates the function.  
		//If function comes to true, the boolean value is updated to true for the next time this is called as we exit the recursion calls
		if(puzzleSolved || isPuzzleSolved()){
			return;
		}
		
		//if numberToTry is 10, we have run out of options, clean up and return to caller
		if(numberToTry==10 && cell.isSolved()){
			this.unsolveCell(cell);
			return;
		}
		
	
	}
	
	public void next(int x, int y){
		
	}
	
	/**
	 * Looks at all cells of a puzzle to see if it has been solved.  If any cell is false, it immediately returns
	 * false and stops checking.  Start in lower right and iterate backwards.  During recursion that will save lots of unneeded checks as we check if we have solved it yet.
	 * @return
	 */
	private boolean isPuzzleSolved() {

		for (int x=8;x>=0; x--){
			for (int y=8; y>=0; y--){
				
				if (!grid.getCell(x, y).isSolved()){
					return false;
				}
				
			}//end y loop
		}//end x loop
		
		puzzleSolved=true;
		return true;
	}

	/** check all rows, columns, and blocks.  Keep track if we made progress anywhere.
	 * 
	 * @return
	 */
	public boolean checkAll(){
		boolean madeProgress= false;
		
		for (int i=0; i<=8; i++){
			
			boolean progressBlock = checkCellArray(grid.getBlock(i));
			//cellToWatch.extendedToString();
			
			boolean progressRow = checkCellArray(grid.getRow(i));
			//cellToWatch.extendedToString();
			
			boolean progressColumn = checkCellArray(grid.getColumn(i));
			//cellToWatch.extendedToString();
			
			//System.out.println("inside check all loop");
			//uses a bunch of OR booleans, because we aren't picky about where progress is made
			madeProgress = madeProgress || progressBlock;
			madeProgress = madeProgress || progressRow;
			madeProgress = madeProgress || progressColumn;
		}
		
		return madeProgress;
	}
	
	/**checks for solvable things within an array of cells
	 * 
	 * @param array
	 * @return
	 */
	public boolean checkCellArray(Cell[] array){
		//System.out.println("inside checkCellArray");

		boolean foundACellToSolve=false;
		
		if (checkForHiddenSingles(array)){
			foundACellToSolve=true;
		}
		if (checkForNakedSingles(array)){
			foundACellToSolve=true;
		}
		/*if (checkForHiddenDoubles(array)){
			foundACellToSolve=true;
		}
		if (checkForNakedDoubles(array)){
			foundACellToSolve=true;
		}*/
		
		
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
			//Cell loneCell = null;
			int CellX = 0; 
			int CellY = 0;
			
			//iterate over input array
			for(Cell cell: array){

				//if this value is still legal and cell is not solved.
				if (!cell.isSolved() && cell.checkValue(i)){
					
					//iterate count
					count++;
					CellX=cell.getCoords().x;
					CellY=cell.getCoords().y;
				}
			}
			
			//how many cells can take that value?  
			if(count==1){
				foundACellToSolve=true;
				setSolvedCell(grid.getCell(CellX,CellY),i, SolveMethod.DEDUCTION);
					
				System.out.println("Found a hidden single -");
				System.out.println(grid.getCell(CellX,  CellY).toString());
				
			}
		}
		
		return foundACellToSolve;
		
	}
	
	/*private boolean checkForHiddenDoubles(Cell[] array){
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
					
					//process 2 cells
					for (int i=1; i<=9; i++){
						//if both cells can accept this value, and its on the list of double candidates, its a keeper
						if(cell1.checkValue(i)&&cell2.checkValue(i)&& listOfDoubleCandidates.contains(i)){
							foundACellToSolve=true;
							System.out.println("Found part of a hidden double - "+i);
							System.out.println(cell1.toString());
							System.out.println(cell2.toString());
						} else{
							//else, get rid of it
							cell1.eliminateValue(i);
							cell2.eliminateValue(i);
						}
					}//end for loop
				}//end if loop
			}//end inside Loop
		}//end outside loop
		
		
		return foundACellToSolve;
	}*/

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
			if(!cell.isSolved()){
			
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
					setSolvedCell(cell, value, SolveMethod.DEDUCTION);
					foundACellToSolve = true;
					System.out.println("Found a naked single -");
					System.out.println(cell.toString());
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
	public void setSolvedCell(Cell solvedCell, int solution, SolveMethod solveMethod){
		
		if (!solvedCell.isSolved()){
			
			//remove value from all appropriate cells
			eliminateFromAll(solvedCell, solution);
			
			//set solution.  If done in the other order it breaks.
			solvedCell.setSolvedValue(solution, solveMethod);
			
		}
		
	}
	
	/**
	 * Need to unsolve a cell in case we need to resort to recursive solving with backtracking
	 * @param cellToUnsolve
	 */
	
	public void unsolveCell(Cell cellToUnsolve){
		//filter out weird inputs
		if(cellToUnsolve.isSolved()&&cellToUnsolve.solvedValue>0){
			//first grab the current solved value
			int oldSolvedValue = cellToUnsolve.solvedValue;
			System.out.println("top of unsolve cell");
			System.out.println("unsolving : "+cellToUnsolve);
			
			//next do a reset of the cell
			cellToUnsolve.resetCell();
			
			//next look at all cells in same row, column and block
			Cell[] sameRow = this.grid.getRow(cellToUnsolve.getCoords().y);
			Cell[] sameCol = this.grid.getColumn(cellToUnsolve.getCoords().x);
			Cell[] sameBlock = this.grid.getBlock(cellToUnsolve.getBlock());
			
			// for the ith element in each array
			for (int i=0; i<9;i++){
				//if its solved
				if(sameRow[i].isSolved()){
					//remove it as option for future solving in calling cell
					cellToUnsolve.eliminateValue(sameRow[i].solvedValue);
					
					//if checked cell is solved via recursion we might need to do an add back as we clean up calling cell
					if(sameRow[i].solveMethod==SolveMethod.RECURSION){
						addBackPossibleValue(sameRow[i], oldSolvedValue);
					}
				}else{
					//else add it back to the other already unsolved cell
					addBackPossibleValue(sameRow[i], oldSolvedValue);
				} //end row block
				
				
				if(sameCol[i].isSolved()){
					cellToUnsolve.eliminateValue(sameCol[i].solvedValue);
					
					if(sameCol[i].solveMethod==SolveMethod.RECURSION){
						addBackPossibleValue(sameCol[i], oldSolvedValue);
					}
				}else{
					addBackPossibleValue(sameCol[i], oldSolvedValue);
				} //end col block
				
				
				if(sameBlock[i].isSolved()){
					cellToUnsolve.eliminateValue(sameBlock[i].solvedValue);
					
					if(sameBlock[i].solveMethod==SolveMethod.RECURSION){
						addBackPossibleValue(sameBlock[i], oldSolvedValue);
					}
				}else{
					addBackPossibleValue(sameBlock[i], oldSolvedValue);;
				} //end block block
			}
		}
		
	}
	
	private void addBackPossibleValue(Cell cell, int valueToAddBack){
		System.out.println("top of add back");
		System.out.println("trying to add back "+valueToAddBack);
		
		//grab all related Cells
		Cell[] sameRow = this.grid.getRow(cell.getCoords().y);
		Cell[] sameCol = this.grid.getColumn(cell.getCoords().x);
		Cell[] sameBlock = this.grid.getBlock(cell.getBlock());
		
		//initialize boolean.  will be true if we find another cell solved to the same value
		boolean foundOtherDuplicateValue = false;
		
		//iterate through all 3 groups of cells
		for (int i=0; i<9; i++){
			System.out.println("loop " +i);
			//if it is solved, and has the same value...
			if (sameRow[i].isSolved() && sameRow[i].solvedValue==valueToAddBack){
				//change boolean
				foundOtherDuplicateValue = true;
				//no need to keep looking
				System.out.println("found dup row: "+sameRow[i]);
				break;
			}
			if (sameCol[i].isSolved() && sameCol[i].solvedValue==valueToAddBack){
				foundOtherDuplicateValue = true;
				System.out.println("found dup col: "+sameCol[i]);
				break;
			}
			if (sameBlock[i].isSolved() && sameBlock[i].solvedValue==valueToAddBack){
				foundOtherDuplicateValue = true;
				System.out.println("found dup block: "+sameBlock[i]);
				break;
			}
		}
		
		System.out.println("end loop");
		//if no other cells found that would disallow this value, proceed
		if(!foundOtherDuplicateValue){
			cell.uneliminateValue(valueToAddBack);
			System.out.println("uneliminate");
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

	public void draw(Graphics g) {
		int x = 0;
        for (int i = 0; i < 9; i++) {
            int y = 0;
            for (int j = 0; j < 9; j++) {
                Rectangle r = new Rectangle(x, y, drawWidth, drawWidth);
                grid.getCell(i, j).setBounds(r);
                grid.getCell(i, j).draw(g, x, y, drawWidth);
                y += drawWidth;
            }
            x += drawWidth;
        }
		
	}

	public Cell getCellLocation(Point point) {
		for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (grid.getCell(x, y).contains(point)) {
                	System.out.println("cell "+x+","+y);
                    return grid.getCell(x, y);
                }
            }
        }
		return null;
	
		
	}


	public boolean isSetValuesToggles() {
		return isSetValuesToggles;
	}

	public void setSetValuesToggles(boolean isSetValuesToggles) {
		this.isSetValuesToggles = isSetValuesToggles;
	}

	public void reset() {
		System.out.println("reseting");
		for (int x=0;x<9;x++){
			for (int y=0; y<9;y++){
				grid.getCell(x, y).resetCell();
			}
		}
		
	}

	public boolean isClearValues() {
		return clearValues;
	}

	public void setClearValues(boolean clearValues) {
		this.clearValues = clearValues;
	}

}
	


