package gottesman.risk.map;

import gottesman.risk.Territory;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TerritoryView extends JLabel {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	private static final Color SELECTED = Color.RED;
	private static final Color UNSELECTED = null;
	private static final Color HIGHLIGHTED = Color.GREEN;
	private Territory territory;

	public TerritoryView(Territory territory, int x, int y) {
		super("", SwingConstants.CENTER);
		setXY(x, y);
		this.territory = territory;
	}

	public void select() {
		showBorder(SELECTED);
	}

	public void unselect() {
		hideBorder();
	}

	public void highlight() {
		showBorder(HIGHLIGHTED);
	}

	public void hideBorder() {
		setBorder(null);
	}

	public void showBorder(Color color) {
		setBorder(BorderFactory.createLineBorder(color));
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setXY(int x, int y) {
		// set the location so x,y is in the middle of the box
		setBounds(x - (WIDTH / 2), y - (HEIGHT / 2), WIDTH, HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(0, 0, WIDTH, HEIGHT);

		// After you draw the oval, then draw the number.
		super.paintComponent(g);
	}

}
