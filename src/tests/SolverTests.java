package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Cell;
import Board.Grid;
import Board.Solver;

public class SolverTests {

	/**
	 * tests that grid not null
	 */
	@Test
	public void testSolver() {
		Solver testSolver = new Solver();
		assertNotNull(testSolver);
	}

	/** tests symptoms of setting a Cell with a solution.  If successful, that cell will register as solved,
	 * and the solution will be removed from other cells.
	 * 
	 */
	@Test
	public void testSetSolvedCell() {
		Solver testSolver = new Solver();
		
		Cell solvedCell = testSolver.grid.getCellArray()[0][0];
		
		testSolver.setSolvedCell(solvedCell, 9);
		
		assertTrue(solvedCell.isSolved());
		assertTrue(solvedCell.checkValue(9));
		assertFalse(solvedCell.checkValue(8));
		
		Cell[] sameRow = testSolver.grid.getRow(0);
		assertEquals(false, sameRow[5].checkValue(9));
		assertTrue(sameRow[5].checkValue(1));
		
		Cell[] sameCol = testSolver.grid.getColumn(0);
		assertFalse(sameCol[8].checkValue(9));
		assertTrue(sameCol[8].checkValue(1));
		
		Cell[] sameBlock = testSolver.grid.getBlock(0);
		assertFalse(sameBlock[8].checkValue(9));
		assertTrue(sameBlock[8].checkValue(1));
	}
	
	/**
	 * Checks that hidden single found correctly
	 *
	 */
	@Test
	public void testHiddenSingles() {
		Solver testSolver = new Solver();
		
		//set cells in other places so that block 0 can only have a 
		//1 at 0,0
		
		Cell row2 = testSolver.grid.getCellArray()[3][1];
		Cell row3 = testSolver.grid.getCellArray()[7][2];
		Cell col2 = testSolver.grid.getCellArray()[1][3];
		Cell col3 = testSolver.grid.getCellArray()[2][7];
		Cell checkCell = testSolver.grid.getCellArray()[0][0];
		
		testSolver.setSolvedCell(row2, 1);
		testSolver.setSolvedCell(row3, 1);
		testSolver.setSolvedCell(col2, 1);
		testSolver.setSolvedCell(col3, 1);

		
		//get block 0;
		Cell[] block = testSolver.grid.getBlock(0);
		
		//check validity of 1 in all cells before running solver
		for(Cell cell:block){
			
			//if in 0,0 position, 1 should still be valid
			if (cell.getCoords().x==0 && cell.getCoords().y==0){
				assertTrue(cell.checkValue(1));
			//in all others it is not
			}else{
				assertFalse(cell.checkValue(1));
			}
		}
		
		//cell should not be solved yet
		assertFalse(checkCell.isSolved());
		
		//should find something when method runs
		assertTrue(testSolver.checkCellArray(block));
		
		//cell should now be solved
		assertTrue(checkCell.isSolved());
	}
	
	
	/**
	 * check that naked singles found correctly
	 */
	@Test
	public void testNakedSingle(){
		Solver testSolver = new Solver();
		
		//get block 0
		Cell[] block = testSolver.grid.getBlock(0);
		
		//iterate over the first 8 cells
		for (int i=0; i<=7; i++){
			
			//solve with value of i+1 (due to index 0)
			testSolver.setSolvedCell(block[i], i+1);
		}
		
		//grab cell we are checking
		Cell testCell = block[8];
		
		//Check current valid numbers
		for (int i=1; i<=9; i++){
			if (i==9){
				assertTrue(testCell.checkValue(i));
			}else{
				assertFalse(testCell.checkValue(i));
			}
		}
		
		//make sure it isn't solved yet
		assertFalse(testCell.isSolved());
		
		//should find a naked single
		assertTrue(testSolver.checkCellArray(block));
		
		//cell should be solved now
		assertTrue(testCell.isSolved());
	}

	@Test
	public void testNakedDouble(){
		Solver testSolver = new Solver();
		
		//get block 0
		Cell[] block = testSolver.grid.getBlock(0);
		
		//get column 2
		Cell[] column = testSolver.grid.getColumn(2);
		
		//iterate over first 7 cells of block, leaving the last 2, which are in row 2
		for (int i=0; i<=6; i++){
			
			//solve with value of i+1
			testSolver.setSolvedCell(block[i], i+1);
		}
		
		//grab the 2 cells in question
		Cell cell7 = block[7];
		Cell cell8 = block[8];
		
		//double check that the 2 cells only have the 8 and 9 remaining as candidates
		for (int i=1; i<=9; i++){
			if(i>=8){
				assertTrue(cell7.checkValue(i));
				assertTrue(cell8.checkValue(i));
			}else{
				assertFalse(cell7.checkValue(i));
				assertFalse(cell8.checkValue(i));
			}
		}
		
		//assuming that passes, we'll verify that they cells in question are in fact in column 2
		
		assertEquals(2, cell7.getCoords().x);
		assertEquals(2, cell8.getCoords().x);
		
		//lets that check a few other cells in that column can take 8 or 9
		assertTrue(column[5].checkValue(8));
		assertTrue(column[4].checkValue(8));
		assertTrue(column[5].checkValue(9));
		assertTrue(column[4].checkValue(9));		

		//should find a naked double in the column
		assertTrue(testSolver.checkCellArray(column));
		
		//lastly we'll check that those other cells can no longer take 8 or 9
		assertFalse(column[5].checkValue(8));
		assertFalse(column[4].checkValue(8));
		assertFalse(column[5].checkValue(9));
		assertFalse(column[4].checkValue(9));
	}
	
}


