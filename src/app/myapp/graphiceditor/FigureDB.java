package app.myapp.graphiceditor;

import java.util.Stack;
import java.awt.Color;
import java.awt.Shape;

public class FigureDB {
	
	static Stack<Shape> figures = new Stack<Shape>();
	static Stack<Color> figureColors = new Stack<Color>();
	static Stack<Integer> figureThickness = new Stack<Integer>();
	
	static Stack<Shape> redoFigures = new Stack<Shape>();
	static Stack<Color> redoFigureColors = new Stack<Color>();
	static Stack<Integer> redoFigureThickness = new Stack<Integer>();
}
