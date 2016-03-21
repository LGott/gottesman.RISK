package gottesman.risk.controllers;

import gottesman.risk.DataManager;
import gottesman.risk.Player;
import gottesman.risk.Territory;
import gottesman.risk.battle.BattleFrame;
import gottesman.risk.battle.DeckEmptyException;
import gottesman.risk.map.BoardView;
import gottesman.risk.map.TerritoryView;
import gottesman.risk.playGame.GameState;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * GameController that handles the Move (or Attack) phase of a player's turn.
 *
 */
public class MoveOrAttackController implements GameController {

	private TerritoryView selectedTerritoryView;
	private DataManager dataManager;
	private GameState gameState;

	public MoveOrAttackController(GameState gameState, DataManager dataManager) {
		this.gameState = gameState;
		this.dataManager = dataManager;
	}

	@Override
	public void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory)
			throws DeckEmptyException {
		Player activePlayer = gameState.getActivePlayer();

		// select one of your territories
		if (territory.isOccupiedBy(activePlayer)) {
			selectTerritory(territoryView);
		}
		// select a territory to occupy
		else if (selectedTerritoryView != null) {
			Territory selectedTerritory = selectedTerritoryView.getTerritory();
			// is it a neighbor?
			if (dataManager.areNeighbors(selectedTerritory, territory)) {
				if (canBattle(selectedTerritory, territory)) {
					startBattle(territory, selectedTerritory);
				} else {
					if (!(selectedTerritory.getBattalions() <= 1)) {
						captureTerritory(activePlayer, selectedTerritory, territoryView, territory);
					}
				}
			}
		}
	}

	private void captureTerritory(Player activePlayer, Territory selectedTerritory, TerritoryView territoryView,
			Territory territory) throws DeckEmptyException {
		// move battalions

		int battalionNum = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Enter the amount of battalions you would like to move."));
		if ((battalionNum >= selectedTerritory.getBattalions()) || (battalionNum < 0)) {
			JOptionPane.showMessageDialog(null, "Cannot move this amount of battalions. Try again.");
			return;
		} else {
			territory.getPlayer().removeTerritory(territory); // remove the territory from the player's list
			territory.occupy(activePlayer);
			activePlayer.addTerritory(territory);
			selectedTerritory.moveBattalionsTo(territory, battalionNum);
		}
		selectedTerritoryView.repaint();
		territoryView.repaint();
		selectTerritory(territoryView);

		gameState.setConquer(true);
		System.out.println(gameState.getActivePlayer().getTerritories().toString());
	}

	private void startBattle(Territory territory, Territory selectedTerritory) {
		// Can only attack if has more than one battalion
		try {
			new BattleFrame(selectedTerritory, territory).setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean canBattle(Territory selectedTerritory, Territory territory) {
		return territory.isOccupied() && (selectedTerritory.getPlayer() != territory.getPlayer())
				&& (selectedTerritory.getBattalions() > 1);
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
