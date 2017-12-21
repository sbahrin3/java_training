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
	int height = 700;
	int x = width/2;
	int y = height - 200;
	
	class MyCanvas extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);
			
			ship.animate();
			ship.move();
			astroid.move();
			
			g.drawImage(ship.img, ship.x, ship.y, this);
			g.drawImage(astroid.img, astroid.x, astroid.y, this);
		}
	}
	
	class Ship {
		Image img;
		int x = 200;
		int y = 500;
		int moveDirection = 0; //0-not moving, 1-go right, 2-go left
		int moveSpeed = 10;
		
		Ship() {
			img = Toolkit.getDefaultToolkit().getImage("C:/sprites/ship.png");
		}
		
		void move() {
			if ( moveDirection > 0 ) {
				if ( x < width - 100 ) x = x + moveSpeed;
			}
			else if ( moveDirection < 0 ) {
				if ( x > 0 ) x = x - moveSpeed;
			}
			else {
				x = x;
			}
		}
		
		void animate() {
			
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
		setResizable(false);
		
		canvas = new MyCanvas();
		ship = new Ship();
		astroid = new Astroid();
		
		add(canvas);

		Timer t = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		
		t.start();
		
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() ==  KeyEvent.VK_LEFT) {
					ship.moveDirection = -1;
				}
				else if ( e.getKeyCode() == KeyEvent.VK_RIGHT) {
					ship.moveDirection = 1;
				}
				if ( e.getKeyCode() == KeyEvent.VK_UP ) {
					ship.moveDirection = 0;
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
