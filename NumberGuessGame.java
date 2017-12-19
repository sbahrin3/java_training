package my.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NumberGuessGame {
	
	JFrame frame = new JFrame();
	int level = 1;

	public static void main(String[] args) {
		NumberGuessGame game = new NumberGuessGame();
		game.run();

	}
	
	void run() {
		frame.setTitle("Number Guess Game Version 1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 300));
		
		JPanel startScreen = startScreen();
		frame.add(startScreen);
		frame.setVisible(true);
	}
	
	JPanel startScreen() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel label = new JLabel("Number Guess Game");
		label.setBounds(100, 50, 200, 30);
		panel.add(label);
		JButton button = new JButton("Start Game");
		button.setBounds(100, 100, 100, 50);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JPanel gameScreen = gameScreen();
				frame.remove(panel);
				frame.add(gameScreen);
				frame.validate();
				
			}
			
		});
		
		
		panel.add(button);
		return panel;
	}
	
	JPanel gameScreen() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel guessNumberLabel = new JLabel();
		guessNumberLabel.setText("Guess a Number ");
		guessNumberLabel.setBounds(10, 50, 300, 30);
		panel.add(guessNumberLabel);
		JTextField guessNumberText = new JTextField();
		guessNumberText.setBounds(200, 50, 50, 30);
		panel.add(guessNumberText);
		JButton OKButton = new JButton("OK");
		OKButton.setBounds(260, 50, 100, 30);
		panel.add(OKButton);
		
		JLabel attemptLabel = new JLabel(); 
		attemptLabel.setText("Attempt = ");
		attemptLabel.setBounds(10, 80, 200, 30);
		panel.add(attemptLabel);
		
		JLabel resultLabel = new JLabel();
		resultLabel.setText("Result = ");
		resultLabel.setBounds(10, 110, 300, 30);
		panel.add(resultLabel);
		
		JButton continueButton = new JButton();
		continueButton.setText("Continue?");
		continueButton.setBounds(10, 140, 100, 30);
		panel.add(continueButton);
		
		//Our game start here
		NumberGuess numberGuess = new NumberGuess();
		numberGuess.setLevel(level);
		numberGuess.generateSecretNumber();
		
		guessNumberLabel.setText("Guess a Number : 0 .. " + numberGuess.getLimit());
		
		OKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String numberInput = guessNumberText.getText();
				int number = Integer.parseInt(numberInput);
				int result = numberGuess.evaluateGuessNumber(number);
				String resultText = "You Guess " + number;
				if ( result > 0 ) {
					resultText = resultText + ",Try Lower";
				} else if ( result < 0 ) {
					resultText = resultText + ",Try Higher";
				} else {
					resultText = resultText + ",You Got It Right!";
				}
				resultLabel.setText("Result = " + resultText);
				
			}
			
		});
		
		return panel;
	}

}
