package gottesman.risk.map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TerritoryViewListener implements MouseListener {

	private GameController gameActionListener;

	public TerritoryViewListener(GameController gameActionListener) {
		this.gameActionListener = gameActionListener;
	}

	public void mouseClicked(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		gameActionListener.onClickTerritory(null, territoryView, territoryView.getTerritory());
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		territoryView.highlight();
	}

	public void mouseExited(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		territoryView.unselect();
	}

}
