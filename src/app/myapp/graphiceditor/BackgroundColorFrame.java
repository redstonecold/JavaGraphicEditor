package app.myapp.graphiceditor;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BackgroundColorFrame extends JFrame implements ChangeListener{
	JColorChooser colorChooser = new JColorChooser();
	
	ColorSelectionModel selectionModel = colorChooser.getSelectionModel();
	static Color color = Color.WHITE;
	
	public BackgroundColorFrame() {
		setTitle("Choose Background Color");
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		colorChooser.getSelectionModel().addChangeListener(this);

		add(colorChooser);
		
		pack();
		setVisible(true);
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		color = colorChooser.getColor();
	}
}
