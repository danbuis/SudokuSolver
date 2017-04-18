package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Cell;

public class CellTests {

	public Cell testCell1 = new Cell(0,0);
	public Cell testCell2 = new Cell(2,2);
	public Cell testCell3 = new Cell(2,3);
	public Cell testCell4 = new Cell(3,2);
	public Cell testCell5 = new Cell(3,3);
	public Cell testCell6 = new Cell(8,8);
	
	
/**
 * Makes sure that cells are determning their block correctly.  Tests extents and around a Block intersection
 */
	@Test
	public void testGetBlock() {
		assertEquals(0, testCell1.getBlock());
		assertEquals(0, testCell2.getBlock());
		assertEquals(1, testCell4.getBlock());
		assertEquals(3, testCell3.getBlock());
		assertEquals(4, testCell5.getBlock());
		assertEquals(8, testCell6.getBlock());
	}

	/** tests coordinate retrieval
	 * 
	 */
	@Test
	public void testGetCoords() {
		assertEquals(2, testCell2.getCoords().x);
		assertEquals(2, testCell2.getCoords().y);
		
		assertEquals(2, testCell3.getCoords().x);
		assertEquals(3, testCell3.getCoords().y);
	}
	
	/** Checks that values associated with setting a cell to solved work correctly
	 * 
	 */
	@Test
	public void testSetSolvedValue() {
		assertFalse(testCell1.isSolved());
		
		testCell1.setSolvedValue(2);
		
		assertTrue(testCell1.isSolved());
		assertFalse(testCell1.checkValue(1));
		assertTrue(testCell1.checkValue(2));
		assertFalse(testCell1.checkValue(3));
		
		assertFalse(testCell1.checkValue(4));
		assertFalse(testCell1.checkValue(5));
		assertFalse(testCell1.checkValue(6));
		
		assertFalse(testCell1.checkValue(7));
		assertFalse(testCell1.checkValue(8));
		assertFalse(testCell1.checkValue(9));
	}

	@Test
	public void testIsSolved() {
		assertFalse(testCell6.isSolved());
		
		testCell6.setSolvedValue(9);
		
		assertTrue(testCell6.isSolved());
	}

	@Test
	public void testCheckValue() {
		assertTrue(testCell1.checkValue(1));
		assertTrue(testCell1.checkValue(7));
		
		testCell6.setSolvedValue(9);
		
		assertTrue(testCell6.checkValue(9));
		assertFalse(testCell6.checkValue(7));
		assertFalse(testCell6.checkValue(1));
	}

	

	@Test
	public void testEliminateValue() {
		
		Cell testCell = new Cell(5,5);
		
		assertTrue(testCell.checkValue(1));
		
		testCell.eliminateValue(1);
		
		assertFalse(testCell.checkValue(1));
		assertTrue(testCell.checkValue(2));
		assertTrue(testCell.checkValue(3));
		
		assertTrue(testCell.checkValue(4));
		assertTrue(testCell.checkValue(5));
		assertTrue(testCell.checkValue(6));
		
		assertTrue(testCell.checkValue(7));
		assertTrue(testCell.checkValue(8));
		assertTrue(testCell.checkValue(9));
		
	}

}
