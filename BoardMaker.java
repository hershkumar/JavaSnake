package net.mrpaul.md110.psExtra;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
public class BoardMaker extends JPanel implements ActionListener{
	private boolean playing = false;
	private boolean before = true; 
	static JFrame screen = new JFrame();
	private final int BOARD_LENGTH = 300;
	private final int BOARD_WIDTH = 300;
	private final static int DOT_SIZE= 10;
	private final int ALL_DOTS= 900;
	private final static int RAND_POS = 29;
	private static int blob_x;
	private static int blob_y;
	private static int dots;
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private final int x[] = new int[ALL_DOTS];
	private final int y[] = new int[ALL_DOTS];
	JButton button = new JButton("Start");
	private Timer timer;
	private final int DELAY = 75; 


	public BoardMaker() {
		setBackground(Color.white);
		addKeyListener(new Adapter());
		setPreferredSize(new Dimension(BOARD_LENGTH, BOARD_WIDTH));
		setVisible(true);
		setFocusable(true);
		button.setLayout(null);
		this.add(button);
		button.addActionListener(this);
		button.setVisible(true);

	}
	public static void gameOver(Graphics g) {
		if (dots< 5) {
			g.drawString("You got " + dots + " points", 40, 40);
			g.drawString("How terrible", 40, 200);
		}
		else if (dots < 10) {
			g.drawString("You got " + dots + " points", 40, 40);
			g.drawString("Meh... You're still pretty bad", 40, 200);
		}
		else if (dots< 25) {
			g.drawString("You got " + dots + " points", 40, 40);
			g.drawString("You're OK", 40, 200);
		}
		else if (dots < 50) {
			g.drawString("You got " + dots + " points", 40, 40);
			g.drawString("You're Average.", 40, 200);
		}
		else if (dots < 100) {
			g.drawString("You got " + dots + " points", 40, 40);
			g.drawString("You're Pretty Good!", 40, 200);
		}
		else if (dots >= 100) {
			g.drawString("You got " + dots + " points", 40, 40);
			g.drawString("WOW! You're are the greatest Snake player ever!", 40, 200);
		}







	}
	private void drawSnake(Graphics g) {

		if (playing) {
			//draw the blob
			g.setColor(Color.blue);
			g.fillRect(blob_x, blob_y, 10,10);

			for (int z = 0; z < dots; z++) {
				g.setColor(Color.red);
				g.fillRect( x[z], y[z],10,10);
			}

			Toolkit.getDefaultToolkit().sync();

		} else if (before == false) {

			gameOver(g);
		}        
	}
	private void startGame() {

		dots = 1;

		for (int z = 0; z < dots; z++) {
			x[z] = 50 - z * 10;
			y[z] = 50;
		}

		locateBlob();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawSnake(g);
	}

	private void move() {

		for (int z = dots; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (leftDirection) {
			x[0] -= DOT_SIZE;
		}

		if (rightDirection) {
			x[0] += DOT_SIZE;
		}

		if (upDirection) {
			y[0] -= DOT_SIZE;
		}

		if (downDirection) {
			y[0] += DOT_SIZE;
		}
	}

	private void checkBlob() {

		if ((x[0] ==	blob_x) && (y[0] == blob_y)) {

			dots++;
			locateBlob();
		}
	}

	public static void locateBlob() {
		int r = (int) (Math.random() * RAND_POS);
		blob_x = ((r * DOT_SIZE));

		r = (int) (Math.random() * RAND_POS);
		blob_y = ((r * DOT_SIZE));
	}
	private void checkCollision() {

		for (int z = dots; z > 0; z--) {

			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				playing = false;
			}
		}

		if (y[0] >= BOARD_LENGTH) {
			playing = false;
		}

		if (y[0] < 0) {
			playing = false;
		}

		if (x[0] >= BOARD_WIDTH) {
			playing = false;
		}

		if (x[0] < 0) {
			playing = false;
		}

		if(!playing) {
			timer.stop();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			startGame();
			this.remove(button);
			this.revalidate();
			this.repaint();
			playing = true;
			before = false;
		}
		else if (playing == true) {

			checkBlob();
			checkCollision();
			move();
		}

		this.repaint();
	}

	private class Adapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}

			if ((key == KeyEvent.VK_UP) && (!downDirection)) {
				upDirection = true;
				rightDirection = false;
				leftDirection = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
		}
	}	
}

