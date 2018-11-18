import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMenuPanel extends JPanel {
	private String str = "*** Puzzle Game ***";
	private String imagePath;
	private JDialog jd;
	private JFrame jf;
	public GameMenuPanel(JFrame jf) {
		setLayout(null);
		this.jf = jf;
	
		setBackground(Color.DARK_GRAY);

		JButton gameStartBt = new JButton("Start");
		gameStartBt.setFont(new Font("Serif", Font.BOLD, 30));
		gameStartBt.setBounds(320, 300, 200, 100);
		gameStartBt.setFocusable(false);
		gameStartBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setContentPane(new InGamePanel(imagePath,jf));
			}
		});
			
	
		JButton imageSelectBt = new JButton("ImageSelect");
		imageSelectBt.setFont(new Font("Serif", Font.BOLD, 30));
		imageSelectBt.setBounds(320, 450, 200, 100);
		imageSelectBt.setFocusable(false);
		imageSelectBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jd = new JDialog(jf, "imageSelect");
				jd.setSize(300, 500);
				jd.setLayout(null);
				jd.setVisible(true);
				JButton a = new JButton("Beach Woman");
				a.setBounds(25,25,230,120);
				a.setFocusable(false);
				a.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						imagePath = "Img/image.jpg";
						jd.setVisible(false);
					}
				});
				JButton b = new JButton("HongJinYoung");
				b.setBounds(25,175,230,120);
				b.setFocusable(false);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						imagePath = "Img/Hong.jpg";
						jd.setVisible(false);
					}
				});
				JButton c = new JButton("GyungLee");
				c.setFocusable(false);
				c.setBounds(25,325,230,120);
				c.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						imagePath = "Img/Space.jpg";
						jd.setVisible(false);
					}
				});
				jd.add(a);
				jd.add(b);
				jd.add(c);
			}
		});
		
		add(gameStartBt);
		add(imageSelectBt);

		setSize(850, 850);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.setFont(new Font("Serif", Font.BOLD, 70));
		g.drawString(str, 110, 100);
	}
}
