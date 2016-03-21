package gottesman.risk.playGame;

import gottesman.risk.playGame.GameState.Phase;

/**
 * Listener that gets called when the game phase changes.
 */
public interface GameStateListener {

	void onPhaseChange(GameState.Phase phase);

}
