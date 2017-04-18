package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Cell;
import Board.Grid;

public class GridTests {

	@Test
	public void testConstructor() {
		Grid testGrid = new Grid();
		assertNotNull(testGrid.getCellArray()[0][0]);
		assertNotNull(testGrid.getCellArray()[8][8]);
	}
	
	@Test
	public void testCellCopy(){
		Grid testGrid = new Grid();
		
		//solve this Cell
		testGrid.getCellArray()[0][0].setSolvedValue(1);
		
		//check that it is solved in one place
		assertTrue(testGrid.getCellArray()[0][0].isSolved());
		
		//now the other
		assertTrue(testGrid.getAllBlocks()[0][0].isSolved());
		
		//and double check 2 more to make sure its not a fluke
		assertFalse(testGrid.getCellArray()[0][1].isSolved());
		assertFalse(testGrid.getAllBlocks()[0][1].isSolved());
		
		assertFalse(testGrid.getCellArray()[8][8].isSolved());
		assertFalse(testGrid.getAllBlocks()[8][8].isSolved());
		
	}
	
	@Test
	public void testGetRow(){
		Grid testGrid = new Grid();
		
		Cell[] row = testGrid.getRow(1);
		
		//if same row, then y coord should be same
		assertEquals(1, row[0].getCoords().y);
		assertEquals(1, row[5].getCoords().y);
		
		//check one more just in case
		Cell[] row2 = testGrid.getRow(8);
		assertEquals(8, row2[8].getCoords().y);
	}
	
	@Test
	public void testGetColumn(){
		Grid testGrid = new Grid();
		
		Cell[] column = testGrid.getColumn(1);
		
		//if same column, than x coord should be same
		assertEquals(1, column[0].getCoords().x);
		assertEquals(1, column[5].getCoords().x);
		
		//check one more just in case
		Cell[] column2 = testGrid.getColumn(8);
		assertEquals(8, column2[8].getCoords().x);
	}
	
	@Test
	public void testGetGroup(){
		Grid testGrid = new Grid();
		
		Cell[] group = testGrid.getBlock(0);
		
		assertEquals(0, group[0].getBlock());
		assertEquals(0, group[3].getBlock());
		
		Cell[] block2 = testGrid.getBlock(4);
		
		assertEquals(4, block2[8].getBlock());
	}

}