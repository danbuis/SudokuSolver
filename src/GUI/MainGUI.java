package GUI;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Board.Solver;

public class MainGUI {
	
	static Solver solver = new Solver();
	static JFrame frame;
	

	/* Main jumping off point for program
	 * 
	 */
	public static void main(String[] args) {
		//Entry code here copied from a website, everything past this is my own work.
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	
	private static void createAndShowGUI() {
        //Create and set up the JFrame.
        frame = new JFrame("Dan's Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension (800,675));
      
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        frame.add(containerPanel);
        
        //add PuzzlePanel
        JPanel puzzPanel = new PuzzlePanel(solver, frame);
        containerPanel.add(puzzPanel);
        
        //add Button Panel
        ButtonPanel buttonPanel = new ButtonPanel(solver, puzzPanel);
        containerPanel.add(buttonPanel.getPanel());
     
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	
}
