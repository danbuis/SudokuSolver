package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.SolveMethod;
import Board.Solver;

public class SolveEntirePuzzle {
	
	@Test
	public void testEasySolve1(){
		Solver solver = new Solver();
		solver.setSolvedCell(solver.grid.getCell(0,0), 2, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(3,0), 8, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(5,0), 4, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(8,0), 6, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(2,1), 6, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,1), 5, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(1,2), 7, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(2,2), 4, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,2), 9, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(7,2), 2, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(0,3), 3, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(4,3), 4, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(8,3), 7, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(3,4), 3, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(5,4), 5, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(0,5), 4, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(4,5), 6, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(8,5), 9, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(1,6), 1, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(2,6), 9, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,6), 7, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(7,6), 4, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(2,7), 8, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,7), 2, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(0,8), 5, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(3,8), 6, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(5,8), 8, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(8,8), 1, SolveMethod.INITIAL);
		
		System.out.println(solver.grid.printGrid());
		System.out.println(" ");
			
		assertTrue(solver.solveGrid());
		
	}
	
	//@Test
	public void testRawRecursion(){
		Solver testSolver = new Solver();
		
		testSolver.solveRecursion(0, 0);
		assertTrue(testSolver.grid.getCell(0, 0).isSolved());
		
		assertTrue(testSolver.grid.getCell(1, 0).isSolved());
		assertTrue(testSolver.grid.getCell(0, 1).isSolved());
		assertTrue(testSolver.grid.getCell(4, 4).isSolved());
		assertTrue(testSolver.grid.getCell(7, 8).isSolved());
		assertTrue(testSolver.grid.getCell(8, 7).isSolved());
		assertTrue(testSolver.grid.getCell(8, 8).isSolved());
		
		
	}
	
	
	/** this puzzle requires more than hidden singles and naked singles to solve, recursion will have to work around previously solved
	 * cells
	 */
	@Test
	public void testInterruptedRecursion(){
		Solver solver = new Solver();
		
		solver.setSolvedCell(solver.grid.getCell(4,0), 3, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(5,0), 2, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(2,2), 7, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(3,2), 6, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,2), 9, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(7,2), 1, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(8,2), 4, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(1,3), 9, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(2,3), 6, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,3), 8, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(2,4), 5, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(5,4), 8, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(1,5), 3, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(4,5), 4, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(8,5), 5, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(1,6), 5, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(3,6), 2, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(0,7), 7, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(6,7), 5, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(7,7), 6, SolveMethod.INITIAL);
		
		solver.setSolvedCell(solver.grid.getCell(0,8), 9, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(2,8), 4, SolveMethod.INITIAL);
		solver.setSolvedCell(solver.grid.getCell(4,8), 1, SolveMethod.INITIAL);
		
		assertTrue(solver.solveGrid());
		
		
		
		
		
		
	}
	

}
