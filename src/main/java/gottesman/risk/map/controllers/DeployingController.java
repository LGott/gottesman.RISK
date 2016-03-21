package gottesman.risk.map.controllers;

import gottesman.risk.Player;
import gottesman.risk.Territory;
import gottesman.risk.battle.DeckEmptyException;
import gottesman.risk.map.BoardView;
import gottesman.risk.map.TerritoryView;
import gottesman.risk.playGame.GameState;

import javax.swing.JOptionPane;

/**
 * GameController that handles the Deploy phase of a player's turn.
 *
 */
public class DeployingController implements GameController {

	private GameState gameState;

	public DeployingController(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory)
			throws DeckEmptyException {

		Player activePlayer = gameState.getActivePlayer();

		int battalionsToDeploy = activePlayer.getBattalionsToDeploy();
		if (battalionsToDeploy >= 1) {
			if (territory.isOccupiedBy(activePlayer)) {

				int battalionNum = Integer.parseInt(JOptionPane.showInputDialog(null, "You have " + battalionsToDeploy
						+ " battalions to deploy. Enter the amount of battalions"
						+ " you would like to deploy to this territory."));

				if ((battalionNum > battalionsToDeploy) || (battalionNum < 0)) {
					JOptionPane.showMessageDialog(null, "Cannot deploy that amount of battalions. Try again.");
				} else {
					territory.increaseBattalions(battalionNum);
					activePlayer.setBattalionsToDeploy(battalionsToDeploy - battalionNum);
				}
			}
		}
		territoryView.repaint();
		if (battalionsToDeploy < 1) {
			activePlayer.setBattalionsToDeploy(0);
			gameState.nextPhase();
		}
	}

	@Override
	public void onClickMap(BoardView boardView) {
	}

}
