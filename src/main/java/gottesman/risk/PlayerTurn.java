package gottesman.risk;

public interface PlayerTurn {

	void startTurn();

	boolean checkCardMatch(); // Check for match

	void makeTrade(); // Process the card trade

	int processReinforcments(); // Calculate reinforcement amount

	void placeReinforcements(); // Distribute reinforcements

	// TODO Finish adding more methods

}
