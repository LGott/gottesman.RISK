package gottesman.risk;

import java.util.ArrayList;

public class Continent {

	private String name;
	private int bonus;
	private ArrayList<Territory> territories; // list of territories that are in the continents

	public Continent(String name, int bonus, ArrayList<Territory> territories) {

		this.name = name;
		this.bonus = bonus;
		this.territories = territories;

	}

	public int checkContinent(ArrayList<Territory> playerCountries) {

		if (playerCountries.containsAll(territories)) {
			return this.bonus;
		} else {
			return 0; // No bonus given
		}
	}

	public String getName() {
		return name;
	}

	public int getBonus() {
		return bonus;
	}

	public ArrayList<Territory> getTerritories() {
		return territories;
	}

}