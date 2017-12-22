package my.game;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
import java.io.File;
import java.io.IOException;


public class MyShipProgram extends JFrame {
	
	MyCanvas canvas;
	Ship ship;
	Astroid astroid;
	Laser laser;
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
			astroid.collideWithLaser();

			
			g.drawImage(ship.img, ship.x, ship.y, this);
			g.drawImage(astroid.img, astroid.x, astroid.y, this);
			g.drawImage(laser.img, laser.x, laser.y, this);
			
		}
	}
	
	class Ship {
		Image img;
		Image[] images = new Image[3];
		int x = 200;
		int y = 500;
		int moveDirection = 0; //0-not moving, 1-go right, 2-go left
		int moveSpeed = 10;
		int animTime = 0;
		int animFrame = 0;
		boolean fired = false;
		
		
		Ship() {
			img = Toolkit.getDefaultToolkit().getImage("c:/sprites/ship.png");
			images[0] = Toolkit.getDefaultToolkit().getImage("c:/sprites/ship1.png");
			images[1] = Toolkit.getDefaultToolkit().getImage("c:/sprites/ship2.png");
			images[2] = Toolkit.getDefaultToolkit().getImage("c:/sprites/ship3.png");
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
			if ( fired ) {
				laser.move();
			}
		}
		
		void animate() {
			img = images[animFrame];
			animTime++;
			if ( animTime > 1 ) {
				animFrame++;
				animTime = 0;
			}
			if ( animFrame > 2 ) {
				animFrame = 0;
			}
		}
		
		void fire() {
			fired = true;
			File soundFile = new File("c:/sprites/laser.wav");
			try {
				
				AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			laser.x = ship.x + 45;
			laser.y = ship.y - 20;
			
		}
		
	}
	
	class Astroid {
		Image img;
		Image imgNormal;
		Image imgExploded;
		int x = 200;
		int y = 10;
		int d = 0;
		boolean exploded = false;
		int explodeTime = 0;
		
		Astroid() {
			imgNormal = Toolkit.getDefaultToolkit().getImage("c:/sprites/asteroid2.png");
			imgExploded = Toolkit.getDefaultToolkit().getImage("c:/sprites/asteroid3.png");
			img = imgNormal;
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
			
			if ( exploded ) {
				explodeTime++;
				if ( explodeTime == 30 ) {
					exploded = false;
					explodeTime = 0;
					
					img = imgNormal;
				}
			}
		}

		void collideWithLaser() {
			boolean hitY = false;
			boolean hitX = false;
			if (laser.y < y  && laser.y > y - 200) {
				hitY = true;
			}
			if ( laser.x > x && laser.x < x + 200 ) {
				hitX = true;
			}
			if ( hitX && hitY ) {
				exploded = true;
				img = imgExploded;
				laser.x = -100;
			}
		}

	}
	
	class Laser {
		Image img;
		int x = -100;
		int y = 1000;
		int d = 5;
		int laserTime = 0;
		int laserSpeed = 1;
		
		Laser() {
			img = Toolkit.getDefaultToolkit().getImage("c:/sprites/laser.png");
		}
		
		void move() {
			
			if ( ship.fired ) {
				
				laser.y = laser.y - 15;
			
				if ( laser.y < 0 ) {
					ship.fired = false;
					laser.y = 1000;
				}
				
			}
			
		}
		
		void animate() {
			
		}
		
	}
	

	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		
		canvas = new MyCanvas();
		ship = new Ship();
		astroid = new Astroid();
		laser = new Laser();

		
		add(canvas);

		Timer t = new Timer(20, new ActionListener() {
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
				if ( e.getKeyCode() == KeyEvent.VK_DOWN ) {
					ship.moveDirection = 0;
				}
				if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
					if ( !ship.fired ) ship.fire();
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
 
