import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Snake extends JFrame implements ActionListener{
	private int snakeLength = 1;
	private static boolean playing = false;
	//makeScreen()
	private static void makeScreen() {
		JFrame screen = new JFrame();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.getContentPane().setBackground(Color.cyan);
		screen.setVisible(true);
		screen.setResizable(false);
		Graphics g = screen.getGraphics();
		g.setColor(Color.red);
		playing = true;	
		drawSnake(g);
		
	}
	@Override
	public void paintComponents(Graphics h) {
		super.paintComponents(h);
		drawSnake(h);
		repaint();
	}
	
	
	
	
	//updateScreen()
	//drawSnake()
	private static void drawSnake(Graphics g) {
		if (playing) {
			g.fillRect(100,100,100,100);
			
			
		}
	}
	//move()
	//checkLocation()
	//increaseLength
	//gameOver()
	//checkSelfCollision()
	
	
	public static void main(String[] args) {
		makeScreen();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
