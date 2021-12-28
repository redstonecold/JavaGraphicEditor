package app.myapp.graphiceditor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Toolkit;
import java.io.IOException;

public class MenuBar extends JMenuBar implements ActionListener{
	
	JFileChooser fileChooser = new JFileChooser();
	JLabel openImage = new JLabel();
	
	JMenu fileMenu = new JMenu("파일");
	JMenuItem open = new JMenuItem("불러오기");
	JMenuItem save = new JMenuItem("저장");
	
	MenuBar() {
		open.addActionListener(this);
		save.addActionListener(this);
		fileMenu.add(open);
		fileMenu.add(save);

		add(fileMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JMenuItem click = (JMenuItem)e.getSource();
		
		if(click == save) {
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images","jpg","jpeg");
			fileChooser.setFileFilter(filter);
			BufferedImage image = new BufferedImage(Buttons.canvas.getSelectedComponent().getWidth(), Buttons.canvas.getSelectedComponent().getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setBackground(Color.WHITE);
			g.clearRect(0, 0, Buttons.canvas.getWidth(), Buttons.canvas.getHeight());
			Buttons.canvas.getSelectedComponent().print(g);
			g.dispose();

			int ret = fileChooser.showSaveDialog(null);

			if(ret != JFileChooser.APPROVE_OPTION){
				JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}

			try {
				System.out.println(fileChooser.getSelectedFile().toPath());
				ImageIO.write(image, "jpg", new File(fileChooser.getSelectedFile().toPath() + ".jpg"));
			} catch (IOException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
		
		if(click == open) {
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG&TXT Files","jpg","jpeg");
			fileChooser.setFileFilter(filter);
			
			int ret = fileChooser.showOpenDialog(null);

			if(ret != JFileChooser.APPROVE_OPTION){
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String filePath = fileChooser.getSelectedFile().getPath();
			openImage.setIcon(new ImageIcon(filePath));
			Buttons.canvas.add(fileChooser.getSelectedFile().getName(), openImage);
			Buttons.canvas.add(fileChooser.getSelectedFile().getName(), openImage);
		}
		
		
		
	}

}
