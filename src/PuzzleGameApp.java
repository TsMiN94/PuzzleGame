import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PuzzleGameApp extends JFrame{
	private JFrame jf = this;
	public PuzzleGameApp() {
		setTitle("Puzzle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setContentPane(new GameMenuPanel(jf));
		c.setLayout(null);
		
		setResizable(false);
		setSize(900,1000);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new PuzzleGameApp();
	}
}
