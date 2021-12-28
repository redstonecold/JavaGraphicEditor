package app.myapp.graphiceditor;

import javax.swing.JToolBar;

public class ToolBar extends JToolBar{
//	MainCanvas canvas;
	
	Buttons buttons = new Buttons();
	
	ToolBar() {
		
//		this.canvas = canvas;
//		buttons = new Buttons(canvas);
		
		int i;
		for(i=0; i<buttons.brushButtons.length; i++) {
			add(buttons.brushButtons[i]);
		}
		addSeparator();
		
		for(i=0; i<buttons.settingButtons.length; i++) {
			add(buttons.settingButtons[i]);
		}
		addSeparator();
		
		
		
	}
	

}
