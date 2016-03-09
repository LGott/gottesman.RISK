package gottesman.risk.map;

import gottesman.risk.DataManager;
import gottesman.risk.GameState;
import gottesman.risk.Territory;

import java.util.List;

public class MoveOrAttackController implements GameController {

	private TerritoryView selectedTerritory;

	private DataManager dataManager;

	private GameState gameState;

	public MoveOrAttackController(GameState gameState, DataManager dataManager) {
		this.gameState = gameState;
		this.dataManager = dataManager;
	}

	public void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory) {

		// select one of your territories
		if (territory.isOccupiedBy(gameState.getActivePlayer())) {
			unselectSelectedTerritory();
			selectedTerritory = territoryView;
			selectedTerritory.select();
		}
		// select a territory to occupy
		else if (selectedTerritory != null) {
			List<String> neighbors = dataManager.getAdjacencies().get(selectedTerritory.getTerritory().getName());
			// is it a neighbor?
			if ((neighbors != null) && neighbors.contains(territory.getName())) {
				if (territory.isOccupied()) {
					// TODO: start a battle
					new DiceBattleView(selectedTerritory.getTerritory(), territory).setVisible(true);

				} else {
					// move n-1 battalions
					territory.setColor(gameState.getActivePlayer());
					territory.setBattalions(selectedTerritory.getTerritory().getBattalions() - 1);
					territoryView.repaint();
					selectedTerritory.getTerritory().setBattalions(1);
					unselectSelectedTerritory();
					selectedTerritory = territoryView;
					selectedTerritory.select();
				}
			}
		}

	}

	public void onClickMap(BoardView boardView) {
		unselectSelectedTerritory();
	}

	private void unselectSelectedTerritory() {
		if (selectedTerritory != null) {
			selectedTerritory.unselect();
			selectedTerritory = null;
		}
	}

}
