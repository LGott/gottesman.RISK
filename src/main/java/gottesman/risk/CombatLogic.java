package gottesman.risk;

import java.util.ArrayList;

public class CombatLogic {

	Dice dice;

	public CombatLogic() {
		dice = new Dice();
	}

	public ArrayList<Integer> getThreeDice() {
		return dice.rollThree();
	}

	public ArrayList<Integer> getTwoDice() {
		return dice.rollTwo();
	}

	public ArrayList<Integer> getOneDie() {
		return dice.rollOne();
	}

	public void calculateWin(ArrayList<Integer> attackerDice, ArrayList<Integer> defenderDice, Territory attacker,
			Territory defender) {

		// TODO Compare the two dice sets and decrement the battalion amount based on the win
	}
}
