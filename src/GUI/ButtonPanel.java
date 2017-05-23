package GUI;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Board.Solver;

public class ButtonPanel {
	
	JPanel panel;
	
	private JToggleButton setValuesButton;
	private JToggleButton clearValuesButton;
	private JButton solveButton;
	private JButton resetPuzzleButton;
	private JPanel puzzlePanel;
	
	public ButtonPanel(Solver solver, JPanel puzzPan){
		this.puzzlePanel=puzzPan;
		
		//set up layout of buttons
		this.panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		//set up set values toggle
		setValuesButton = new JToggleButton("Set Values");
		setValuesButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
            	//when clicked, do this stuff
                solver.setSetValuesToggles(setValuesButton.isSelected());
            }
        });
		panel.add(setValuesButton);
		
		//set up set values toggle
				clearValuesButton = new JToggleButton("Clear Values");
				clearValuesButton.addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent event) {
		            	//when clicked, do this stuff
		                solver.setClearValues(clearValuesButton.isSelected());
		            }
		        });
				panel.add(clearValuesButton);
		
		//set up solve button
		solveButton = new JButton("Solve Puzzle");
		solveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//when clicked, do this stuff
				solver.solveGrid();
				puzzlePanel.repaint();
				
			}
			
		});
		panel.add(solveButton);
		
		//set up reset button
		resetPuzzleButton = new JButton ("Reset Puzzle");
		resetPuzzleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//when clicked, do this stuff
				solver.reset();
				puzzlePanel.repaint();	
			}		
		});
		panel.add(resetPuzzleButton);
	}
	
	public JPanel getPanel(){
		return panel;
	}

}
