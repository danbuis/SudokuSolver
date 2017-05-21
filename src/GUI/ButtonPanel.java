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
	private JButton solveButton;
	private JButton resetPuzzleButton;
	private JPanel puzzlePanel;
	
	private Solver solver;
	
	public ButtonPanel(Solver solver, JPanel puzzPan){
		this.puzzlePanel=puzzPan;
		this.solver=solver;
		
		this.panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		setValuesButton = new JToggleButton("Set Values");
		setValuesButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                solver.setSetValuesToggles(setValuesButton.isSelected());
            }
        });
		panel.add(setValuesButton);
		
		solveButton = new JButton("Solve Puzzle");
		solveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				solver.solveGrid();
				puzzlePanel.repaint();
				
			}
			
		});
		panel.add(solveButton);
		
		resetPuzzleButton = new JButton ("Reset Puzzle");
		resetPuzzleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
