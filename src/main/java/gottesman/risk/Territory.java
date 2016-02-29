package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;

public class Territory implements Comparable<Territory> {

	private String name;
	private Integer countryID;
	private Color color;
	private int battalions;
	private ArrayList<Territory> adjacentTerritories;

	public Territory(Integer countryID, String name, Color color, int battalions,
			ArrayList<Territory> adjacentTerritories) {
		this.countryID = countryID;
		this.name = name;
		this.color = color;
		this.battalions = battalions;
		this.adjacentTerritories = adjacentTerritories;
	}

	public Integer getCountryID() {
		return countryID;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getBattalions() {
		return battalions;
	}

	public void setBattalions(int battalions) {
		this.battalions = battalions;
	}

	public ArrayList<Territory> getAdjacentCountries() {
		return adjacentTerritories;
	}

	public void setAdjacentCountries(ArrayList<Territory> adjacentTerritories) {
		this.adjacentTerritories = adjacentTerritories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + countryID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Territory other = (Territory) obj;
		if (countryID != other.countryID) {
			return false;
		}
		return true;
	}

	public int compareTo(Territory other) {
		return this.countryID.compareTo(other.getCountryID());

	}

}
