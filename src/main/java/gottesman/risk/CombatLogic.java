package gottesman.risk;

import java.util.ArrayList;
import java.util.Collections;

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

		// Compare the two dice sets and decrement the battalion amount based on the win

		Collections.sort(attackerDice, Collections.reverseOrder());
		Collections.sort(defenderDice, Collections.reverseOrder());

		for (int i = 0; i < defenderDice.size(); i++) {
			if (attackerDice.get(i) > defenderDice.get(i)) {
				defender.decrementBattalions();
			} else if (defenderDice.get(i) >= attackerDice.get(i)) {
				attacker.decrementBattalions();
			}
		}
	}
}
