import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPanel extends JPanel {
	private ImageIcon icon;
	private String imgPath;
	
	public GameOverPanel(String imgPath) {
		this.imgPath = imgPath;
		icon = new ImageIcon(imgPath);
		JLabel gameOver = new JLabel("!!!! Success !!!!!!");
		gameOver.setFont(new Font("Serif",Font.BOLD,60));
		gameOver.setBounds(100,50,500,200);
		gameOver.setOpaque(false);
		add(gameOver);
		setSize(900,900);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon.getImage(),90,100,700,800 ,this);
	}
}
