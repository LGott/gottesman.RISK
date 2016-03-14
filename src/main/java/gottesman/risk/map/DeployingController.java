package gottesman.risk.map;

import gottesman.risk.GameState;

import gottesman.risk.Player;
import gottesman.risk.Territory;

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
		if ( territory.isOccupiedBy(activePlayer) ) {
			territory.increaseBattalions(activePlayer.getBattalionsToDeploy());
			activePlayer.setBattalionsToDeploy(0);
			gameState.nextPhase();
			territoryView.repaint();
		}
	
	}

	public void onClickMap(BoardView boardView) {
	}

}
