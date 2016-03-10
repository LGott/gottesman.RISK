package gottesman.risk;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class CombatLogicTest {

	@Test
	public void test() {
		CombatLogic combat = new CombatLogic();
		Territory attacker = new Territory("testing", 1, 2);
		Territory defender = new Territory("testing", 2, 3);
		attacker.setBattalions(4);
		attacker.setBattalions(4);
		Dice dice = new Dice();

		final int originalDiceAmntD = defender.getBattalions();
		final int originalDiceAmntA = attacker.getBattalions();

		ArrayList<Integer> attackerDice = dice.rollThree();
		ArrayList<Integer> defenderDice = dice.rollTwo();

		combat.calculateWin(attackerDice, defenderDice, attacker, defender);

		for (int i = 0; i < defenderDice.size(); i++) {
			if (attackerDice.get(i) > defenderDice.get(i)) {
				Assert.assertTrue(defender.getBattalions() == (originalDiceAmntD - 1));
			}
			if (defenderDice.get(i) >= attackerDice.get(i)) {
				Assert.assertTrue(attacker.getBattalions() == (originalDiceAmntA - 1));
			}
		}
	}
}
