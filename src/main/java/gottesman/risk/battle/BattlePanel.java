package gottesman.risk.battle;

import gottesman.risk.Territory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BattlePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public BattlePanel(Territory attacker, Territory defender) {

		try {

			if (attacker.getColor().equals(Color.CYAN)) {
				if (defender.getColor().equals(Color.WHITE)) {
					getImage("/Images/BlueVsWhite.jpg");
				} else if (defender.getColor().equals(Color.GREEN)) {
					getImage("/Images/BlueVsGreen1.jpg");
				}
			} else if (attacker.getColor().equals(Color.GREEN)) {
				if (defender.getColor().equals(Color.WHITE)) {
					getImage("/Images/GreenVsWhite.jpg");
				} else if (defender.getColor().equals(Color.CYAN)) {
					getImage("/Images/GreenVsBlue.jpg");
				}
			} else if (attacker.getColor().equals(Color.WHITE)) {
				if (defender.getColor().equals(Color.GREEN)) {
					getImage("/Images/WhiteVsGreen.jpg");
				} else if (defender.getColor().equals(Color.CYAN)) {
					getImage("/Images/WhiteVsBlue.jpg");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getImage(String img) throws IOException {
		image = ImageIO.read(getClass().getResourceAsStream(img));
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

	}

}
