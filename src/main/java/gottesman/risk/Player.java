package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	private Color color;
	private int playerNum;
	private ArrayList<BattalionType> cards;
	private ArrayList<Territory> territories;
	private int battalionAmnt;

	public Player(Color color, int playerNum) {

		this.color = color;
		this.playerNum = playerNum;
		this.cards = new ArrayList<BattalionType>();
		this.territories = new ArrayList<Territory>();
		this.battalionAmnt = 0;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public ArrayList<BattalionType> getCards() {
		return cards;
	}

	public void setCards(ArrayList<BattalionType> cards) {
		this.cards = cards;
	}

	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(ArrayList<Territory> territories) {
		this.territories = territories;
	}

	public int getBattalionAmnt() {
		return battalionAmnt;
	}

	public void setBattalionAmnt(int battalionAmnt) {
		this.battalionAmnt = battalionAmnt;
	}

	public Color getColor() {
		return color;
	}

}
