package tests;

import org.junit.Test;

import Board.Solver;

public class SolveEntirePuzzle {
	
	@Test
	public void testEasySolve1(){
		Solver solver = new Solver();
		solver.setSolvedCell(solver.grid.getCellArray()[0][0], 2);
		solver.setSolvedCell(solver.grid.getCellArray()[3][0], 8);
		solver.setSolvedCell(solver.grid.getCellArray()[5][0], 4);
		solver.setSolvedCell(solver.grid.getCellArray()[8][0], 6);
		
		solver.setSolvedCell(solver.grid.getCellArray()[2][1], 6);
		solver.setSolvedCell(solver.grid.getCellArray()[6][1], 5);
		
		solver.setSolvedCell(solver.grid.getCellArray()[1][2], 7);
		solver.setSolvedCell(solver.grid.getCellArray()[2][2], 4);
		solver.setSolvedCell(solver.grid.getCellArray()[6][2], 9);
		solver.setSolvedCell(solver.grid.getCellArray()[7][2], 2);
		
		solver.setSolvedCell(solver.grid.getCellArray()[0][3], 3);
		solver.setSolvedCell(solver.grid.getCellArray()[4][3], 4);
		solver.setSolvedCell(solver.grid.getCellArray()[8][3], 7);
		
		solver.setSolvedCell(solver.grid.getCellArray()[3][4], 3);
		solver.setSolvedCell(solver.grid.getCellArray()[5][4], 5);
		
		solver.setSolvedCell(solver.grid.getCellArray()[0][5], 4);
		solver.setSolvedCell(solver.grid.getCellArray()[4][5], 6);
		solver.setSolvedCell(solver.grid.getCellArray()[8][5], 9);
		
		solver.setSolvedCell(solver.grid.getCellArray()[1][6], 1);
		solver.setSolvedCell(solver.grid.getCellArray()[2][6], 9);
		solver.setSolvedCell(solver.grid.getCellArray()[6][6], 7);
		solver.setSolvedCell(solver.grid.getCellArray()[7][6], 4);
		
		solver.setSolvedCell(solver.grid.getCellArray()[2][7], 8);
		solver.setSolvedCell(solver.grid.getCellArray()[6][7], 2);
		
		solver.setSolvedCell(solver.grid.getCellArray()[0][8], 5);
		solver.setSolvedCell(solver.grid.getCellArray()[3][8], 6);
		solver.setSolvedCell(solver.grid.getCellArray()[5][8], 8);
		solver.setSolvedCell(solver.grid.getCellArray()[8][8], 1);
		
		solver.grid.printGrid();
		System.out.println(" ");
		
		solver.solveGrid();
				
		
		
		
		
		
		
	}

}
