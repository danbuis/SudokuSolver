package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Cell;
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

}
