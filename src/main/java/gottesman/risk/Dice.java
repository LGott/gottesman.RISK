package gottesman.risk;

import java.util.ArrayList;

public class Dice {

	Die die;

	public Dice() {
		die = new Die();
	}

	public ArrayList<Integer> rollThree() {

		ArrayList<Integer> threeDice = new ArrayList<Integer>();

		int i = 0;
		while (i < 3) {
			threeDice.add(die.roll());
			i++;
		}

		return threeDice;
	}

	public ArrayList<Integer> rollTwo() {

		ArrayList<Integer> twoDice = new ArrayList<Integer>();

		int i = 0;
		while (i < 2) {
			twoDice.add(die.roll());
			i++;
		}

		return twoDice;
	}

	public int rollOne() {

		return die.roll();
	}

}