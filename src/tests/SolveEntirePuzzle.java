package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Solver;

public class SolveEntirePuzzle {
	
	@Test
	public void testEasySolve1(){
		Solver solver = new Solver();
		solver.setSolvedCell(solver.grid.getCell(0,0), 2, false);
		solver.setSolvedCell(solver.grid.getCell(3,0), 8, false);
		solver.setSolvedCell(solver.grid.getCell(5,0), 4, false);
		solver.setSolvedCell(solver.grid.getCell(8,0), 6, false);
		
		solver.setSolvedCell(solver.grid.getCell(2,1), 6, false);
		solver.setSolvedCell(solver.grid.getCell(6,1), 5, false);
		
		solver.setSolvedCell(solver.grid.getCell(1,2), 7, false);
		solver.setSolvedCell(solver.grid.getCell(2,2), 4, false);
		solver.setSolvedCell(solver.grid.getCell(6,2), 9, false);
		solver.setSolvedCell(solver.grid.getCell(7,2), 2, false);
		
		solver.setSolvedCell(solver.grid.getCell(0,3), 3, false);
		solver.setSolvedCell(solver.grid.getCell(4,3), 4, false);
		solver.setSolvedCell(solver.grid.getCell(8,3), 7, false);
		
		solver.setSolvedCell(solver.grid.getCell(3,4), 3, false);
		solver.setSolvedCell(solver.grid.getCell(5,4), 5, false);
		
		solver.setSolvedCell(solver.grid.getCell(0,5), 4, false);
		solver.setSolvedCell(solver.grid.getCell(4,5), 6, false);
		solver.setSolvedCell(solver.grid.getCell(8,5), 9, false);
		
		solver.setSolvedCell(solver.grid.getCell(1,6), 1, false);
		solver.setSolvedCell(solver.grid.getCell(2,6), 9, false);
		solver.setSolvedCell(solver.grid.getCell(6,6), 7, false);
		solver.setSolvedCell(solver.grid.getCell(7,6), 4, false);
		
		solver.setSolvedCell(solver.grid.getCell(2,7), 8, false);
		solver.setSolvedCell(solver.grid.getCell(6,7), 2, false);
		
		solver.setSolvedCell(solver.grid.getCell(0,8), 5, false);
		solver.setSolvedCell(solver.grid.getCell(3,8), 6, false);
		solver.setSolvedCell(solver.grid.getCell(5,8), 8, false);
		solver.setSolvedCell(solver.grid.getCell(8,8), 1, false);
		
		solver.grid.printGrid();
		System.out.println(" ");
			
		assertTrue(solver.solveGrid());
		
		
		
		
		
	}

}
