package gottesman.risk;

import java.awt.Color;

public class Territory implements Comparable<Territory> {

	private String name;
	private int x;
	private int y;

	private Player player;
	private int battalions = 0;

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

	public void occupy(Player player) {
		this.player = player;
	}

	public void unoccupy() {
		this.player = null;
		this.battalions = 0;
	}

	public int getBattalions() {
		return battalions;
	}

	public void setBattalions(int battalions) {
		this.battalions = battalions;
	}

	public void increaseBattalions(int battalions) {
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
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return this.name + " " + this.x + " " + this.y;
	}

	public int compareTo(Territory other) {
		return this.name.compareTo(other.getName());
	}

	public Player getPlayer() {
		return player;
	}

	public Color getColor() {
		return player == null ? null : player.getColor();
	}

	/**
	 * Move n-1 battalions to the other Territory
	 * 
	 * @param other
	 */
	public void moveBattalionsTo(Territory other, int battalionNum) {
		if (battalions <= 0) {
			return;
		}
		other.increaseBattalions(battalionNum);
		battalions -= battalionNum;
	}

}
