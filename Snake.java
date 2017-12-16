package net.mrpaul.md110.psExtra;
import java.awt.EventQueue;
import javax.swing.JFrame;
public class Snake extends JFrame{

	public Snake(){

		add(new BoardMaker());
		
		setResizable(true);
		pack();
		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {                
				JFrame ex = new Snake();
				ex.setVisible(true);                
			}
		}
				);
	}
}
