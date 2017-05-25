package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Cell;
import Board.Grid;

public class GridTests {

	/**
	 * Make sure full extent of grid is created
	 */
	@Test
	public void testConstructor() {
		Grid testGrid = new Grid();
		assertNotNull(testGrid.getCell(0,0));
		assertNotNull(testGrid.getCell(8,8));
	}
	
	/**
	 * Makes sure that Cells accessed through different organizational structures are still identical
	 */
	@Test
	public void testCellCopy(){
		Grid testGrid = new Grid();
		
		//solve this Cell
		testGrid.getCell(0,0).setSolvedValue(1, false);
		
		//check that it is solved in one place
		assertTrue(testGrid.getCell(0,0).isSolved());
		
		//now the other
		assertTrue(testGrid.getAllBlocks()[0][0].isSolved());
		
		//and double check 2 more to make sure its not a fluke
		assertFalse(testGrid.getCell(0,1).isSolved());
		assertFalse(testGrid.getAllBlocks()[0][1].isSolved());
		
		assertFalse(testGrid.getCell(8,8).isSolved());
		assertFalse(testGrid.getAllBlocks()[8][8].isSolved());
		
	}
	
	/**
	 * tests behaviour of getRow() method
	 */
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
	
	/**
	 * tests behaviour of getColumn() method
	 */
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
	
	
	/**
	 * tests behaviour of getGroup() method
	 */
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
