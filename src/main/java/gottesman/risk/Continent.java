package gottesman.risk;

import java.util.List;
import java.util.Set;

public class Continent {

	private String name;
	private int bonus;
	private String[] territories; // list of territories that are in the continents

	public Continent(String name, int bonus, String[] territories) {

		this.name = name;
		this.bonus = bonus;
		this.territories = territories;

	}

	public String getName() {
		return name;
	}

	public int getBonus() {
		return bonus;
	}

	public String[] getTerritories() {
		return territories;
	}
	
	public boolean isFullyOccupied(Set<String> occupiedTerritories) {
		for ( String t : territories ) {
			if ( !occupiedTerritories.contains(t) ) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public String toString() {
		return this.name + " " + this.bonus + " " + territories.toString();
	}
	/*
	 * public int checkContinent(ArrayList<Territory> playerCountries) {
	 * 
	 * if ((playerCountries.containsAll(territories)) { return this.bonus; } else { return 0; // No bonus given } }
	 */


}