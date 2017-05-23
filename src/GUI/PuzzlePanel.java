package GUI;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Board.Solver;

/**
 * JPanel that holds the puzzle for the GUI
 * @author Buis
 *
 */

public class PuzzlePanel extends JPanel{
	

	private Solver currentPuzzle;
	
	public PuzzlePanel( Solver puzzle, JFrame frame){
		currentPuzzle = puzzle; //to have access to puzzle
		this.addMouseListener(new SudokuMouseListener(frame, currentPuzzle, this));
		new JPanel();
		
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentPuzzle.draw(g);
    }
	

}
