package gottesman.risk.map;

import gottesman.risk.battle.DeckEmptyException;
import gottesman.risk.controllers.GameController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TerritoryViewListener implements MouseListener {

	private GameController gameController;

	public TerritoryViewListener() {
	}

	public void mouseClicked(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		try {
			gameController.onClickTerritory(null, territoryView, territoryView.getTerritory());
		} catch (DeckEmptyException e1) {

			e1.printStackTrace();
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		territoryView.setHighLighted(true);
	}

	public void mouseExited(MouseEvent e) {
		TerritoryView territoryView = (TerritoryView) e.getSource();
		territoryView.setHighLighted(false);
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

}
