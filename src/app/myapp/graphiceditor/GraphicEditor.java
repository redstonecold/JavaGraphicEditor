package app.myapp.graphiceditor;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;

public class GraphicEditor extends JFrame{
	
//	private MainCanvas canvas = new MainCanvas();
	private MenuBar menubar = new  MenuBar();
//	private ToolBar toolbar = new ToolBar(canvas);
	private ToolBar toolbar = new ToolBar();
	
	GraphicEditor() {
		setTitle("Swing GraphicEditor");
		setSize(1000,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menubar);
		
		Container c = this.getContentPane();
		
		c.add(toolbar, BorderLayout.NORTH);
//		c.add(canvas, BorderLayout.CENTER);
		c.add(toolbar.buttons.canvas, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GraphicEditor();
	}
	
}
