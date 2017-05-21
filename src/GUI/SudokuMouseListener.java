package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Board.Cell;
import Board.Solver;

public class SudokuMouseListener extends MouseAdapter {

	private JFrame frame;
	private Solver puzzle;
	private PuzzlePanel puzzlePanel;
	
	public SudokuMouseListener(JFrame frame, Solver puzzle, PuzzlePanel puzzlePanel){
		this.puzzle=puzzle;
		this.frame=frame;
		this.puzzlePanel=puzzlePanel;
	}
	
	public void mousePressed(MouseEvent event) {
 
		System.out.println("Click at :"+event.getPoint());
            Cell cell = puzzle.getCellLocation(event.getPoint());
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
            }
	}
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

	private boolean testValue(Cell cell, int value) {
		if (value>=1 && value <=9){
			return (cell.checkValue(value));
		}
		return false;
	}
            
            
        
    
	
}
