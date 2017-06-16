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

}
