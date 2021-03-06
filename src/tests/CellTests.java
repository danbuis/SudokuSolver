package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Board.Cell;
import Board.SolveMethod;

public class CellTests {

	//a few test cells
	public Cell testCell1 = new Cell(0,0);
	public Cell testCell2 = new Cell(2,2);
	public Cell testCell3 = new Cell(2,3);
	public Cell testCell4 = new Cell(3,2);
	public Cell testCell5 = new Cell(3,3);
	public Cell testCell6 = new Cell(8,8);
	
	
/**
 * Makes sure that cells are determining their block correctly.  Tests extents and around a Block intersection
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
		
		testCell1.setSolvedValue(2, SolveMethod.INITIAL);
		
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

	/** verifies that cell solves in the first place
	 * 
	 */
	@Test
	public void testIsSolved() {
		assertFalse(testCell6.isSolved());
		
		testCell6.setSolvedValue(9, SolveMethod.INITIAL);
		
		assertTrue(testCell6.isSolved());
	}

	/** checks that retrieving value from solved cell works right
	 * 
	 */
	@Test
	public void testCheckValue() {
		assertTrue(testCell1.checkValue(1));
		assertTrue(testCell1.checkValue(7));
		
		testCell6.setSolvedValue(9, SolveMethod.INITIAL);
		
		assertTrue(testCell6.checkValue(9));
		assertFalse(testCell6.checkValue(7));
		assertFalse(testCell6.checkValue(1));
	}

	/** Tests that all behavior associated with eliminating values from cells works
	 * 
	 */
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
	
	
	/** A few tests for determining info about what candidates still remain in cells
	 * 
	 */
	@Test
	public void testSameCandidatesRemain(){
		Cell test1 = new Cell(0,0);
		Cell test2 = new Cell(1,1);
		Cell test3 = new Cell(2,2);
		
		assertTrue(test1.sameCandidatesRemain(test3));
		assertTrue(test2.sameCandidatesRemain(test1));
		
		test2.eliminateValue(9);
		
		assertFalse(test2.sameCandidatesRemain(test1));
		assertFalse(test1.sameCandidatesRemain(test2));
	}
	
	@Test
	public void testHowManyCandidatesRemain(){
		Cell test1 = new Cell(0,0);
		
		assertEquals(9, test1.howManyCandidatesRemain());
		
		test1.eliminateValue(1);
		assertEquals(8, test1.howManyCandidatesRemain());
		
		test1.setSolvedValue(2, SolveMethod.INITIAL);
		assertEquals(1, test1.howManyCandidatesRemain());
	}

}
