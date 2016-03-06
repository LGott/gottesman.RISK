package gottesman.risk.map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TerritoryViewListener implements MouseListener {

	public void mouseClicked(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		territoryView.showBorder();
	}

	public void mouseExited(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		territoryView.hideBorder();
	}

}
