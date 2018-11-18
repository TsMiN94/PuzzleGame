import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InGamePanel extends JPanel implements ActionListener {
	private ImageIcon icon;
	private Image img;
	private int row = 4;
	private int col = 4;
	private int game[];
	private int mouseCnt = 0;
	private int curNum, oldNum;
	private String imagePath;
	private BufferedImage imgArray[];
	private JButton imgBtn[];
	private JButton curBt, oldBt;
	private JFrame jf;

	public InGamePanel(String imagePath, JFrame jf) {
		this.imagePath = imagePath;
		this.jf = jf;

		MediaTracker tracker = new MediaTracker(this);
		img = Toolkit.getDefaultToolkit().getImage(imagePath);
		tracker.addImage(img, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}

		int width = img.getWidth(this) / row;
		int height = img.getHeight(this) / col;

		setSize(new Dimension(width * row, height * col));

		imgArray = new BufferedImage[row * col];

		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				imgArray[cnt] = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = imgArray[cnt].getGraphics();
				g.drawImage(img, 0, 0, width, height, j * width, i * height, (j + 1) * width, (i + 1) * height, this);
				cnt++;
			}
		}
		game = new int[row * col];
		imgBtn = new JButton[row * col];

		for (int i = 0; i < row * col; i++) {
			imgBtn[i] = new JButton();
			imgBtn[i].addActionListener(this);
			add(imgBtn[i]);
		}
		shuffle();
		setLayout(new GridLayout(row, col));

		setSize(900, 900);
	}

	public void shuffle() {
		Random rnd = new Random();
		do {
			for (int i = 0; i < row * col; i++)
				game[i] = 0;
			for (int i = 0; i < row * col; i++) {
				int temp = 0;
				do {
					temp = rnd.nextInt(row * col);
				} while (game[temp] != 0);
				game[temp] = i;
			}
		} while (endGame());

		for (int i = 0; i < row * col; i++) {
			imgBtn[i].setIcon(new ImageIcon(imgArray[game[i]]));
		}
	}

	public boolean endGame() {
		boolean endGame = true;

		for (int i = 0; i < game.length; i++) {
			if (i != game[i])
				endGame = false;
		}
		return endGame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton curBt = (JButton) e.getSource();

		for (int i = 0; i < imgBtn.length; i++) {
			if (curBt.getIcon().equals(imgBtn[i].getIcon())) { // 내가 누른 버튼의 아이콘과 버튼이미 지배열안에있는 배열의 인덱스를 비교하여 찾아낸다.
				curNum = i;
				break;
			}
		}

		if (mouseCnt == 0) {
			mouseCnt++;
			oldNum = curNum;
		} else {
			
			if (oldNum != curNum) {
				imgBtn[oldNum].setIcon(new ImageIcon(imgArray[game[curNum]]));
				imgBtn[curNum].setIcon(new ImageIcon(imgArray[game[oldNum]]));

				int temp = game[oldNum];
				game[oldNum] = game[curNum];
				game[curNum] = temp;

				if (endGame()) {
					jf.setContentPane(new GameOverPanel(imagePath));
				}
			}

			mouseCnt =0;
		}
	}
}
