package gottesman.risk.battle;

import java.util.ArrayList;

public class Dice {

	Die die;

	public Dice() {
		die = new Die();
	}

	public ArrayList<Integer> rollThree() {

		ArrayList<Integer> threeDice = new ArrayList<Integer>();

		for (int i = 0; i < 3; i++) {
			threeDice.add(die.roll());
		}

		return threeDice;
	}

	public ArrayList<Integer> rollTwo() {

		ArrayList<Integer> twoDice = new ArrayList<Integer>();

		for (int i = 0; i < 2; i++) {
			twoDice.add(die.roll());

		}

		return twoDice;
	}

	public ArrayList<Integer> rollOne() {
		ArrayList<Integer> oneDie = new ArrayList<Integer>();
		oneDie.add(die.roll());
		return oneDie;
	}
}
