package gottesman.risk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private JButton rules;
	private JFrame ruleFrame;
	private JTextArea rulesText;

	public StartPanel() {

		rules = new JButton("Game Rules");
		rulesText = new JTextArea("");
		ruleFrame = new JFrame();
		ruleFrame.setVisible(false);

		try {

			image = ImageIO.read(getClass().getResourceAsStream("/Images/StartScreenPic.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());
		add(rules, BorderLayout.SOUTH);

		ruleFrame.setTitle("Risk Rules");
		ruleFrame.add(rulesText);
		ruleFrame.setSize(600, 250);
		ruleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ruleFrame.setLocationRelativeTo(null);
		ruleFrame.setResizable(false);

		rulesText.setLineWrap(true);
		rulesText.setWrapStyleWord(true);
		rulesText.setBackground(Color.BLACK);
		rulesText.setForeground(Color.WHITE);
		rulesText.setEditable(false);

		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rulesText.setText("");

				InputStream ruleFile = getClass().getResourceAsStream("/riskRules.txt");
				BufferedReader in = new BufferedReader(new InputStreamReader(ruleFile));

				String line;
				try {
					while ((line = in.readLine()) != null) {
						rulesText.append(line);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ruleFrame.setVisible(true);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

	}

}