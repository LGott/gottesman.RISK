package gottesman.risk.controllers;

import gottesman.risk.DataManager;
import gottesman.risk.Player;
import gottesman.risk.Territory;
import gottesman.risk.battle.DeckEmptyException;
import gottesman.risk.map.BoardView;
import gottesman.risk.map.TerritoryView;
import gottesman.risk.playGame.GameState;

import javax.swing.JOptionPane;

/**
 * GameController that handles the Fortify phase of a player's turn.
 *
 */
public class FortifyController implements GameController {

	private TerritoryView selectedTerritoryView;
	private DataManager dataManager;
	private GameState gameState;

	public FortifyController(GameState gameState, DataManager dataManager) {
		this.gameState = gameState;
		this.dataManager = dataManager;
	}

	@Override
	public void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory)
			throws DeckEmptyException {
		Player activePlayer = gameState.getActivePlayer();

		if (territory.isOccupiedBy(activePlayer)) {
			if (selectedTerritoryView != null) {
				Territory selectedTerritory = selectedTerritoryView.getTerritory();
				if (dataManager.areNeighbors(selectedTerritory, territory)) {

					int battalionNum = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Enter the amount of battalions you would like to fortify the territory with."));

					if ((battalionNum > selectedTerritory.getBattalions()) || (battalionNum < 0)) {
						JOptionPane.showMessageDialog(null, "Cannot deploy that amount of battalions. Try again.");
					} else {
						selectedTerritory.moveBattalionsTo(territory, battalionNum);
						selectedTerritoryView.repaint();
						territoryView.repaint();
						gameState.nextPhase();
					}
				}
			} else {
				selectTerritory(territoryView);
			}
		}
	}

	private void selectTerritory(TerritoryView territoryView) {
		unselectSelectedTerritory();
		selectedTerritoryView = territoryView;
		selectedTerritoryView.setSelected(true);
	}

	@Override
	public void onClickMap(BoardView boardView) {
		unselectSelectedTerritory();
	}

	private void unselectSelectedTerritory() {
		if (selectedTerritoryView != null) {
			selectedTerritoryView.setSelected(false);
			selectedTerritoryView = null;
		}
	}

}
