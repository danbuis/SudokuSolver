package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Board.Cell;
import Board.Solver;
/**
 * Class that watches for mouse clicks and then acts accordingly
 * @author Buis
 *
 */
public class SudokuMouseListener extends MouseAdapter {

	private JFrame frame;
	private Solver puzzle;
	private PuzzlePanel puzzlePanel;
	
	public SudokuMouseListener(JFrame frame, Solver puzzle, PuzzlePanel puzzlePanel){
		this.puzzle=puzzle;
		this.puzzlePanel=puzzlePanel;
	}
	
	/**
	 * only mouse listening method
	 */
	public void mousePressed(MouseEvent event) {
		
			//what cell was clicked
            Cell cell = puzzle.getCellLocation(event.getPoint());
            
            //if we are setting values right now
            if (puzzle.isSetValuesToggles()){       
            	if (cell != null) {

            		//bring up dialog and get a value for the cell
            		int value = getValue(cell);
            		//if value is valid...
                	if (value > 0) {
                    	puzzle.setSolvedCell(cell, value);
                    	puzzlePanel.repaint();
                	}
            	}
            }else if (puzzle.isClearValues()){
            	if(cell!=null) {
            		puzzle.grid.unsolveCell(cell);
            		puzzlePanel.repaint();
            	}
            }
	}
	
	/**
	 * Window to input data, which is validated before being sent into the puzzle
	 * @param cell
	 * @return
	 */
	private int getValue(Cell cell) {
        int value = 0;
        boolean validEntry =false;
        
        while (value == 0) {
            String inputValue = JOptionPane.showInputDialog(frame,
                    "Type a value from 1 to 9");
 
            if (inputValue == null) { // Cancel button
                return 0;
            }
         
            //Data Validation   
            try {
                value = Integer.parseInt(inputValue);
                
                //make sure its 1-9 and can still be used in that cell
                validEntry = testValue(cell, value);
            } catch (NumberFormatException e) {
                value = 0;
            }
        }
        
        if (validEntry){
        	return value;
        }
        else return 0;
    }
	
	
	/**
	 * Make sure value is legal for the cell
	 * @param cell
	 * @param value
	 * @return
	 */
	private boolean testValue(Cell cell, int value) {
		if (value>=1 && value <=9){
			return (cell.checkValue(value));
		}
		return false;
	}
            
            
        
    
	
}
