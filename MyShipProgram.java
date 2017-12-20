package my.game;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;

public class MyShipProgram extends JFrame {
	
	MyCanvas canvas;
	Ship ship;
	int width = 800;
	int height = 400;
	int x = width/2;
	int y = height - 200;
	
	class MyCanvas extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.WHITE);
			
			g.drawImage(ship.img, x, y, this);
		}
	}
	
	class Ship {
		Image img;
		int x;
		int y;
		
		Ship() {
			img = Toolkit.getDefaultToolkit().getImage("C:/sprites/ship.jpg");
		}
	}
	
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		
		canvas = new MyCanvas();
		ship = new Ship();
		
		add(canvas);
		
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() ==  KeyEvent.VK_LEFT) {
					x = x - 10;
					repaint();
				}
				else if ( e.getKeyCode() == KeyEvent.VK_RIGHT) {
					x = x + 10;
					repaint();
				}
			}
			
			
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MyShipProgram p = new MyShipProgram();
		p.run();
	}
	

}
