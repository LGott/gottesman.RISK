package gottesman.risk;

import java.awt.Color;

public class Territory implements Comparable<Territory> {

	private String name;
	private int x;
	private int y;

	private Player player;
	private Color color;
	private int battalions;

	public Territory(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void occupy(Player player, int battalions) {
		this.player = player;
		this.battalions = battalions;
	}

	public void setColor(Color color) {
		this.color = player.getColor();
	}

	public Color getColor() {
		return color;
	}

	public int getBattalions() {
		return battalions;
	}

	public void setBattalions(int battalions) {
		this.battalions += battalions;
	}

	public void decrementBattalions() {
		this.battalions -= 1;
	}

	public boolean isOccupiedBy(Player activePlayer) {
		return player == activePlayer;
	}

	public boolean isOccupied() {
		return (player != null) && (battalions > 0);
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
		if (name != other.name) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.name + " " + this.x + " " + this.y;
	}

	public int compareTo(Territory other) {
		return this.name.compareTo(other.getName());
	}

}
