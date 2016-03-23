package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Player {

	private Color color;
	private ArrayList<BattalionType> cards;
	private ArrayList<Territory> territories;
	private int battalionsToDeploy;

	public Player(Color color) {
		this.color = color;
		this.cards = new ArrayList<BattalionType>();
		this.territories = new ArrayList<Territory>();
		this.battalionsToDeploy = 0;
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

	public void removeTerritory(Territory t) {
		if (territories.contains(t)) {
			this.territories.remove(t);
		}
	}

	public Color getColor() {
		return color;
	}

	public int getBattalionsToDeploy() {

		return battalionsToDeploy;
	}

	public void setBattalionsToDeploy(int battalionsToDeploy) {
		this.battalionsToDeploy = battalionsToDeploy;
	}

	public boolean checkCardMatch() {

		int cannon = Collections.frequency(cards, BattalionType.CANNON);
		int cavalry = Collections.frequency(cards, BattalionType.CAVALRY);
		int infintry = Collections.frequency(cards, BattalionType.INFINTRY);

		if ((cannon == 3) || (cavalry == 3) || (infintry == 3)) {
			for (BattalionType card : cards) {
				if (card.equals(BattalionType.CANNON)) {
					cards.remove(card);
				} else if ((card.equals(BattalionType.CAVALRY))) {
					cards.remove(card);
				} else if (card.equals(BattalionType.INFINTRY)) {
					cards.remove(card);
				}
			}
			return true;

		} else if ((cannon >= 1) && (cavalry >= 1) && (infintry >= 1)) { // Remove 1 of each type
			cards.remove(BattalionType.CANNON);
			cards.remove(BattalionType.CAVALRY);
			cards.remove(BattalionType.INFINTRY);
			return true;
		}

		else {
			return false;
		}
	}
}
