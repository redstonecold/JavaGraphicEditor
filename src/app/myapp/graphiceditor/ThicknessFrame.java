package app.myapp.graphiceditor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ThicknessFrame extends JFrame implements ActionListener {
	
	static int thick = 5;
	String numberStrings[] = {"5","6","7","8","9","10","11","12","14","16"};
	JButton numberButtons[] = new JButton[numberStrings.length];
	
	public ThicknessFrame() {
		
		setTitle("Choose Thickness");
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(5,1,2,2));
		
		for(int i=0; i<numberStrings.length; i++) {
			numberButtons[i] = new JButton(numberStrings[i]);
			numberButtons[i].addActionListener(this);
			add(numberButtons[i]);
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String thickness = b.getText();
		thick = Integer.parseInt(thickness);
	}

	
}
