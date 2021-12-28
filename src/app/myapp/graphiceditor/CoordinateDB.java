package app.myapp.graphiceditor;

import java.awt.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CoordinateDB {
	static List<Point> coordinates = new ArrayList<Point>();
	static Stack<Integer> startPoint = new Stack<Integer>();
	static Stack<Integer> endPoint = new Stack<Integer>();
	
	static Stack<Integer> redoStartPoint = new Stack<Integer>();
	static Stack<Integer> redoEndPoint = new Stack<Integer>();
}
