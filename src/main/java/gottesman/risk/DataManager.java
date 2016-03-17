package gottesman.risk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

	private ArrayList<Territory> territories;
	private ArrayList<Continent> continents;
	private Map<String, List<String>> adjacencies;
	private Map<String, Territory> territoryNames;

	public DataManager() throws NumberFormatException, IOException {

		this.territories = new ArrayList<Territory>();
		this.continents = new ArrayList<Continent>();
		this.adjacencies = new HashMap<String, List<String>>();
		this.territoryNames = new HashMap<String, Territory>();

		loadTerritories();
		loadContinents();
		loadAdjacencies();

	}

	private void loadTerritories() throws NumberFormatException, IOException {

		InputStream terFile = getClass().getResourceAsStream("/Territories.txt");
		BufferedReader in = reader(terFile);

		String line;
		while ((line = in.readLine()) != null) {

			String[] list = line.split(",");
			territories.add(new Territory(list[0], Integer.parseInt(list[1].trim()), Integer.parseInt(list[2].trim())));
		}
	}

	private void loadContinents() throws NumberFormatException, IOException {

		InputStream contFile = getClass().getResourceAsStream("/Continents.txt");
		BufferedReader in = reader(contFile);

		String line;
		while ((line = in.readLine()) != null) {

			String[] list = line.split("#");
			String[] ter = list[2].split(",");
			continents.add(new Continent(list[0], Integer.parseInt(list[1].trim()), ter));
		}
	}

	private void loadAdjacencies() throws IOException {

		InputStream adjFile = getClass().getResourceAsStream("/Adjacencies.txt");
		BufferedReader in = reader(adjFile);

		String line;
		while ((line = in.readLine()) != null) {

			String[] list = line.split("#");
			String[] adj = list[1].trim().split(",");
			adjacencies.put(list[0], Arrays.asList(adj));
		}
	}

	public Map<String, Territory> getTerritoryNames() {

		for (int i = 0; i < territories.size(); i++) {
			territoryNames.put(territories.get(i).getName(), territories.get(i));
		}
		return this.territoryNames;
	}

	private BufferedReader reader(InputStream file) throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(file));
	}

	public ArrayList<Territory> getTerritories() {
		return this.territories;
	}

	public ArrayList<Continent> getContinents() {
		return this.continents;
	}

	public Map<String, List<String>> getAdjacencies() {
		return this.adjacencies;
	}

	/**
	 * @param a
	 * @param b
	 * @return true if the two Territories are neighbors
	 */
	public boolean areNeighbors(Territory a, Territory b) {
		List<String> neighbors = getAdjacencies().get(a.getName());
		return (neighbors != null) && neighbors.contains(b.getName());
	}
}
