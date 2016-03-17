package gottesman.risk.battle;

public class Die {

	private final int MAX = 6;

	private int value;

	public Die() {

		this.value = 0;
	}

	public int roll() {
		value = (int) (Math.random() * MAX) + 1;

		return value;
	}

	public int getValue() {

		return this.value;
	}

}
