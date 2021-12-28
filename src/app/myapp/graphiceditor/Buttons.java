package app.myapp.graphiceditor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Shape;

import javax.swing.JButton;

public class Buttons extends MouseAdapter{
	
	private String brushStrings[] = {"자유곡선", "선", "사각형", "원"};
	private String settingStrings[] = {"색상", "굵기", "지우기","undo","redo","clear","배경색"};
	
	private int brushLength = brushStrings.length;
	private int settingLength = settingStrings.length;
	
	public JButton[] brushButtons = new JButton[brushLength];
	public JButton[] settingButtons = new JButton[settingLength];
	
	static Boolean[] drawing = new Boolean[4];
	static Boolean erase = false;
	
	private int bNum;
	int canvasNum = 0;
	
	static TapGroup canvas = new TapGroup();
	
	Buttons() {
		addCanvas();
		
		int i;
		
		for(i=0; i<brushLength; i++) {
			brushButtons[i] = new JButton(brushStrings[i]);
			brushButtons[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			brushButtons[i].setPreferredSize(new Dimension(30, 30));
			brushButtons[i].setBorderPainted(false);
			brushButtons[i].addMouseListener(this);
		}
		for(i=0; i<settingLength; i++) {
			settingButtons[i] = new JButton(settingStrings[i]);
			settingButtons[i].setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			settingButtons[i].setPreferredSize(new Dimension(30, 30));
			settingButtons[i].setBorderPainted(false);
			settingButtons[i].addMouseListener(this);
		}
		for(i=0; i<brushLength; i++) {
			drawing[i] = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		for(bNum=0; bNum<brushLength; bNum++) {
			if(brushButtons[bNum] == button) {
				setToDraw(bNum);
				break;
			}
		}
		if(button == settingButtons[0]) {
			ColorFrame colorFrame = new ColorFrame();
			setSettingButtons(0);
		}
		
		if(button == settingButtons[1]) {
			ThicknessFrame thicknessFrame = new ThicknessFrame();
			setSettingButtons(1);
		}
		
		if(button == settingButtons[2]) {
			setToErase();
		}
		
		if(button == settingButtons[3]) {
			setSettingButtons(3);
			undo();
		}
		
		if(button == settingButtons[4]) {
			setSettingButtons(4);
			redo();
		}
		
		if(button == settingButtons[5]) {
			setSettingButtons(5);
			clear();
		}
		
		if(button == settingButtons[6]) {
			BackgroundColorFrame backgroundColorFrame = new BackgroundColorFrame();
			setSettingButtons(6);
			canvas.getSelectedComponent().setBackground(backgroundColorFrame.color);
		}
		
	}
	
	public void setToDraw(int bNum) {
		erase=false;
		for(int i=0; i<brushLength; i++) {
			if(i==bNum) {
				drawing[bNum] = true;
				brushButtons[i].setBorderPainted(true);
			}
			else {
				drawing[i] = false;
				brushButtons[i].setBorderPainted(false);
			}
		}
		for(int i=0; i<settingLength; i++) {
			settingButtons[i].setBorderPainted(false);
		}
	}
	
	public void setToErase() {
		erase = true;
		for(int i=0; i<brushLength; i++) {
			drawing[i] = false;
			brushButtons[i].setBorderPainted(false);
		}
		for(int i=0; i<settingLength; i++) {
			if(i==2) settingButtons[2].setBorderPainted(true);
			settingButtons[i].setBorderPainted(false);
		}
	}
	
	private void addCanvas() {
		MainCanvas newCanvas = new MainCanvas();

		newCanvas.setBackground(Color.white);

		canvas.add("canvas", newCanvas);
		canvasNum++;
	}
	
	private void undo() {
		if(FigureDB.figures.isEmpty()) return;
		if(FigureDB.figures.peek() == null) {
			System.out.println(CoordinateDB.startPoint.peek());
			CoordinateDB.redoStartPoint.push(CoordinateDB.startPoint.pop());
			System.out.println(CoordinateDB.endPoint.peek());
			CoordinateDB.redoEndPoint.push(CoordinateDB.endPoint.pop());
		}
		System.out.println(FigureDB.figures.peek());
		FigureDB.redoFigures.push(FigureDB.figures.pop());
		System.out.println(FigureDB.figureColors.peek());
		FigureDB.redoFigureColors.push(FigureDB.figureColors.pop());
		canvas.getSelectedComponent().repaint();
	}
	
	private void redo() {
		try {
			if(FigureDB.figures.isEmpty()) return;
			if(FigureDB.figures.peek() == null) {
				System.out.println(CoordinateDB.redoStartPoint.peek());
				CoordinateDB.startPoint.push(CoordinateDB.redoStartPoint.pop());
				System.out.println(CoordinateDB.redoEndPoint.peek());
				CoordinateDB.endPoint.push(CoordinateDB.redoEndPoint.pop());
			}
			System.out.println(FigureDB.redoFigures.peek());
			FigureDB.figures.push(FigureDB.redoFigures.pop());
			System.out.println(FigureDB.redoFigureColors.peek());
			FigureDB.figureColors.push(FigureDB.redoFigureColors.pop());
			canvas.getSelectedComponent().repaint();
		} catch (EmptyStackException e) {
			
		}
	}
	
	private void clear() {
		FigureDB.figures = new Stack<Shape>();
		FigureDB.figureColors = new Stack<Color>();
		FigureDB.figureThickness = new Stack<Integer>();
		FigureDB.redoFigures = new Stack<Shape>();
		FigureDB.redoFigureColors = new Stack<Color>();
		FigureDB.redoFigureThickness = new Stack<Integer>();
		
		CoordinateDB.coordinates = new ArrayList<Point>();
		CoordinateDB.startPoint = new Stack<Integer>();
		CoordinateDB.endPoint = new Stack<Integer>();
		CoordinateDB.redoStartPoint = new Stack<Integer>();
		CoordinateDB.redoEndPoint = new Stack<Integer>();
		
		canvas.getSelectedComponent().repaint();
	}
	
	private void setSettingButtons(int sNum) {
		erase = false;
		
		for(int i=0; i<brushLength; i++) {
			drawing[i] = false;
			brushButtons[i].setBorderPainted(false);
		}
		for(int i=0; i<settingLength; i++) {
			if(i==sNum) {
				settingButtons[sNum].setBorderPainted(true);
			}
			settingButtons[i].setBorderPainted(false);
		}
	}

	public Boolean[] getDrawing() {
		return drawing;
	}

	public void setDrawing(Boolean[] drawing) {
		this.drawing = drawing;
	}
	
	
	
}
