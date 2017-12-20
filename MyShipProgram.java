package my.game;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;

public class MyShipProgram extends JFrame {
	
	MyCanvas canvas;
	Ship ship;
	Astroid astroid;
	int width = 800;
	int height = 500;
	int x = width/2;
	int y = height - 200;
	
	class MyCanvas extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);
			
			g.drawImage(ship.img, ship.x, ship.y, this);
			g.drawImage(astroid.img, astroid.x, astroid.y, this);
		}
	}
	
	class Ship {
		Image img;
		int x = 200;
		int y = 300;
		
		Ship() {
			img = Toolkit.getDefaultToolkit().getImage("C:/sprites/ship.png");
		}
	}
	
	class Astroid {
		Image img;
		int x = 200;
		int y = 10;
		int d = 0;
		
		Astroid() {
			img = Toolkit.getDefaultToolkit().getImage("C:/sprites/asteroid.png");
		}
		
		void move() {
			if ( d == 0 ) {
				x = x - 10;
			} else {
				x = x + 10;
			}
			if ( x < 0 ) {
				d = 1;
			}
			if ( x > 700 ) {
				d = 0;
			}
		}
		
	}
	
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		
		canvas = new MyCanvas();
		ship = new Ship();
		astroid = new Astroid();
		
		add(canvas);

		Timer t = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				astroid.move();
				repaint();
			}
		});
		
		t.start();
		
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() ==  KeyEvent.VK_LEFT) {
					ship.x = ship.x - 10;
				}
				else if ( e.getKeyCode() == KeyEvent.VK_RIGHT) {
					ship.x = ship.x + 10;
				}
				if ( e.getKeyCode() == KeyEvent.VK_UP ) {
					ship.y = ship.y - 10;
				}
				else if ( e.getKeyCode() == KeyEvent.VK_DOWN ) {
					ship.y = ship.y + 10;
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
