/**
 * 
 */
package Board;

/**The Grid object contains a 2-D array of Cells and organizes them by rows, columns, and blocks.  All queries to cells pass through the Grid.
 * Basically the Grid manages the 81 cells.
 * @author dbuis
 *
 */
public class Grid{

	private Cell[][] cellArray;
	private Cell[][] cellGroups;
	
	/**
	 * Generic constructor
	 */
	public Grid() {
		initializeCellArray();
		initializeBlocks();	
	}
	

	/**
	 * sort cells by blocks so that we don't have to search for them over and over during solving
	 */
	private void initializeBlocks() {
		
		//set up blocks
		cellGroups = new Cell[9][9];
		
		Cell cellToSort;
		int block;
		
		//iterate over all Cells
		for(int x=0; x<=8; x++){
			for (int y=0; y<=8; y++){
				
				cellToSort = cellArray[x][y];
				block = cellToSort.getBlock();
				
				//sort Cell into correct block
				for(int i=0;i<=8;i++){
					if(cellGroups[block][i]==null){
						cellGroups[block][i]=cellToSort;
						break;
					}
				}
				
			}
		}
		
	}


	/**
	 * Initializes cell array.  Creates the array, and then creates a Cell with the correct attributes for each item.
	 */
	private void initializeCellArray() {
		//give dimensions to array
		cellArray = new Cell[9][9];
		
		//iterate over grid
		for(int x=0; x<=8; x++){
			for (int y=0; y<=8; y++){
				
				//create new Cell in each spot
				cellArray[x][y] = new Cell(x,y);
			}
		}
		
	}
	
	/**
	 * gets the entire cellArray
	 * @return
	 */
	
	private Cell[][] getCellArray(){
		return this.cellArray;
	}
	
	public Cell getCell(int x, int y){
		return this.getCellArray()[x][y];
	}
	
	
	/** gets all the groups
	 * 
	 * @return
	 */
	public Cell[][] getAllBlocks(){
		return this.cellGroups;
	}
	
	
	/**
	 * gets a specified row of cells
	 * @param row
	 * @return
	 */
	public Cell[] getRow(int row){
Cell[] returnArray = new Cell[9];
		
		for (int i=0; i<=8; i++){
			returnArray[i]=this.cellArray[i][row];
		}
			
		return returnArray;
	}
	
	
	/** gets a specified column of cells
	 * 
	 * @param column
	 * @return
	 */
	public Cell[] getColumn(int column){
		
		
		return cellArray[column];
	}
	
	/** gets a specified block of cells
	 * 
	 * @param block
	 * @return
	 */
	public Cell[] getBlock(int block){
		return cellGroups[block];
	}
	
	public String printGrid(){
		String row = "";
		for (int y=0; y<=8; y++){
			
			for (int x=0; x<=8; x++){
				if(this.cellArray[x][y].isSolved()){		
					row =row+" "+this.cellArray[x][y].solvedValue;
				} else{
					row = row+" -";
				}

			}//end y loop
			row=row+"\n";
		}//end x loop
		
		return row;
	}
	
	public String toString(){
		return printGrid();
	}
	

	
	

}
