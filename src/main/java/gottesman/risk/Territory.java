package gottesman.risk;

public class Territory implements Comparable<Territory> {

	private String name;
	private int x;
	private int y;

	// private Color color;
	// private int battalions;

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

	/*
	 * public Color getColor() { return color; }
	 * 
	 * public void setColor(Color color) { this.color = color; }
	 * 
	 * public int getBattalions() { return battalions; }
	 * 
	 * public void setBattalions(int battalions) { this.battalions = battalions; }
	 */

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
