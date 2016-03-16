package gottesman.risk.map;

import gottesman.risk.GameState;
import gottesman.risk.Player;
import gottesman.risk.Territory;

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

	public void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory) {

		Player activePlayer = gameState.getActivePlayer();

		int battalionsToDeploy = activePlayer.getBattalionsToDeploy();
		if (battalionsToDeploy >= 1) {
			if (territory.isOccupiedBy(activePlayer)) {
				int battalionNum = Integer.parseInt(JOptionPane.showInputDialog(null, "You have " + battalionsToDeploy
						+ " battalions to deploy. Enter the amount of battalions"
						+ " you would like to deploy to this territory."));
				if (battalionNum > battalionsToDeploy) {
					JOptionPane.showMessageDialog(null,
							"Not enough reinforcements to deploy that amount of battalions. Try again.");
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

	public void onClickMap(BoardView boardView) {
	}

}
