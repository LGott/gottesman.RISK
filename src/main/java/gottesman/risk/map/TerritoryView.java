package gottesman.risk.map;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import gottesman.risk.Territory;

public class TerritoryView extends JLabel {

	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	private Territory territory;

	public TerritoryView(Territory territory, int x, int y) {
		setBounds(x, y, WIDTH, HEIGHT);
		this.territory = territory;
	}

	public void showBorder() {
		showBorder(Color.RED);
	}

	public void hideBorder() {
		showBorder(Color.BLUE);
	}
	
	public void showBorder(Color color) {
		setBorder(BorderFactory.createLineBorder(color));
	}

	public Territory getTerritory() {
		return territory;
	}

	public void setXY(int x, int y) {
		setBounds(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
	}

}
