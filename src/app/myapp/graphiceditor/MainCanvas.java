package app.myapp.graphiceditor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.EmptyStackException;
import java.awt.geom.Ellipse2D;

public class MainCanvas extends Canvas{
	Point start, end;
	Line2D.Double line;
	Rectangle2D.Double rectangle;
	Ellipse2D.Double ellipse;
	Graphics2D g2;
	Image image;
	boolean openBool = false;
	
	public MainCanvas() {
		this.setBackground(Color.WHITE);
		MouseListener mouselistener = new MouseListener();
		
		addMouseListener(mouselistener);
		addMouseMotionListener(mouselistener);
	}
	
	class MouseListener extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent e) {
			if(Buttons.erase == true || Buttons.drawing[0] == true) {
				start = e.getPoint();
				CoordinateDB.coordinates.add(start);
				CoordinateDB.startPoint.add(CoordinateDB.coordinates.size()-1);
			}
			if(Buttons.drawing[1] == true || Buttons.drawing[2] == true || Buttons.drawing[3] == true) {
				start = e.getPoint();
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if(Buttons.erase == true || Buttons.drawing[0] == true) {
				end = e.getPoint();
				CoordinateDB.coordinates.add(end);
				repaint();
			}
			if(Buttons.drawing[1] == true) {
				end = e.getPoint();
				line = new Line2D.Double(start.x,start.y,end.x,end.y);
				repaint();
			}
			if(Buttons.drawing[2] == true) {
				end = e.getPoint();
				rectangle = new Rectangle2D.Double(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x-end.x), Math.abs(start.y-end.y));
				repaint();
			}
			if(Buttons.drawing[3] == true) {
				end = e.getPoint();
				ellipse = new Ellipse2D.Double(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x-end.x), Math.abs(start.y-end.y));
				repaint();
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(Buttons.erase == true) {
				CoordinateDB.endPoint.add(CoordinateDB.coordinates.size()-1);
				FigureDB.figures.push(null);
				FigureDB.figureColors.push(Color.WHITE);
				FigureDB.figureThickness.push(ThicknessFrame.thick);
			}
			if(Buttons.drawing[0] == true) {
				CoordinateDB.endPoint.add(CoordinateDB.coordinates.size()-1);
				FigureDB.figures.push(null);
				FigureDB.figureColors.push(ColorFrame.color);
				FigureDB.figureThickness.push(ThicknessFrame.thick);
			}
			if(Buttons.drawing[1] == true) {
				FigureDB.figures.push(line);
				FigureDB.figureColors.push(ColorFrame.color);
				FigureDB.figureThickness.push(ThicknessFrame.thick);
			}
			if(Buttons.drawing[2] == true) {
				FigureDB.figures.push(rectangle);
				FigureDB.figureColors.push(ColorFrame.color);
				FigureDB.figureThickness.push(ThicknessFrame.thick);
			}
			if(Buttons.drawing[3] == true) {
				FigureDB.figures.push(ellipse);
				FigureDB.figureColors.push(ColorFrame.color);
				FigureDB.figureThickness.push(ThicknessFrame.thick);
			}
		}
	}

	public void paint(Graphics g) {
		
		try {
			Graphics2D g2 = (Graphics2D) g;
			
			int sketchNum = 0;
			
			if(openBool == true) {
				
			}
			
			//이전에 그린 그림들 다시 그려주기 
			for(int i=0; i<FigureDB.figures.size(); i++) {
				g2.setColor(FigureDB.figureColors.get(i));
				g2.setStroke(new BasicStroke(FigureDB.figureThickness.get(i)));
				
				if(FigureDB.figures.get(i) == null) { //Todo 자유곡선 다시 그릴 때 이상하게 됨 
					for(int j= CoordinateDB.startPoint.get(sketchNum); j<CoordinateDB.endPoint.get(sketchNum)-1; j++) {
						g2.drawLine(CoordinateDB.coordinates.get(j).x, CoordinateDB.coordinates.get(j).y, CoordinateDB.coordinates.get(j+1).x, CoordinateDB.coordinates.get(j+1).y);
					}
					sketchNum++;
				}
				else 
					g2.draw(FigureDB.figures.get(i));
			}
			
			//현재 펜 색상 
			if(Buttons.erase == true)  g2.setColor(Color.WHITE);
			else g2.setColor(ColorFrame.color);
			
			//현재 펜 굵기 
			g2.setStroke(new BasicStroke(ThicknessFrame.thick));
			
			//현재 그리기 
			if(Buttons.erase == true || Buttons.drawing[0] == true) {
				for(int i= CoordinateDB.startPoint.peek(); i<CoordinateDB.coordinates.size()-1; i++) {
					g2.drawLine(CoordinateDB.coordinates.get(i).x,CoordinateDB.coordinates.get(i).y,CoordinateDB.coordinates.get(i+1).x, CoordinateDB.coordinates.get(i+1).y);
				}
			}
			if(Buttons.drawing[1] == true) {
				g2.draw(line);
			}
			else if(Buttons.drawing[2] == true) {
				g2.draw(rectangle);
			}
			else if(Buttons.drawing[3] == true) {
				g2.draw(ellipse);
			}
		} catch (EmptyStackException e){
			
		}
		
	}

}
