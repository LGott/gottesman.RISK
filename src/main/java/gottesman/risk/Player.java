package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private Color color;
	private ArrayList<BattalionType> cards;
	private ArrayList<Territory> territories;
	private int battalionAmnt;

	public Player(Color color) {

		this.color = color;
		this.cards = new ArrayList<BattalionType>();
		this.territories = new ArrayList<Territory>();
		this.battalionAmnt = 0; // Default start amount
	}

	public ArrayList<BattalionType> getCards() {
		return cards;
	}

	public void addCard(BattalionType card) {
		this.cards.add(card);
	}

	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	public void addTerritory(Territory territories) {
		this.territories.add(territories);
	}

	public Color getColor() {
		return color;
	}

}
