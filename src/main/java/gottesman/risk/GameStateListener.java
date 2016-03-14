package gottesman.risk;

/**
 * Listener that gets called when the game phase changes.
 */
public interface GameStateListener {

	void onPhaseChange(GameState.Phase phase);

}
