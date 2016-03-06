package gottesman.risk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {

	private BufferedReader in;
	private ArrayList<Territory> territories;
	private ArrayList<Continent> continents;
	private String terFile;
	private String contFile;
	private String adjFile;

	private String[] list;
	private String line;

	private Map<String, String[]> adjacencies;
	private Map<String, Territory> territoryNames;

	public DataManager(String terFile, String contFile, String adjFile) {

		this.territories = new ArrayList<Territory>();
		this.continents = new ArrayList<Continent>();
		this.adjacencies = new HashMap<String, String[]>();
		this.territoryNames = new HashMap<String, Territory>();
		this.terFile = terFile;
		this.contFile = contFile;
		this.adjFile = adjFile;

	}

	public void loadTerritories() throws NumberFormatException, IOException {

		reader(terFile);

		while ((line = in.readLine()) != null) {

			list = line.split(",");
			territories.add(new Territory(list[0], Integer.parseInt(list[1].trim()), Integer.parseInt(list[2].trim())));
		}
	}

	public void loadContinents() throws NumberFormatException, IOException {

		reader(contFile);

		while ((line = in.readLine()) != null) {

			list = line.split("#");
			String[] ter = list[2].split(",");
			continents.add(new Continent(list[0], Integer.parseInt(list[1].trim()), ter));

		}
	}

	public void loadAdjacencies() throws IOException {

		reader(adjFile);

		while ((line = in.readLine()) != null) {

			list = line.split("#");
			String[] adj = list[1].trim().split(",");
			adjacencies.put(list[0], adj);
		}
	}

	public Map<String, Territory> getTerritoryNames() {

		for (int i = 0; i < territories.size(); i++) {
			territoryNames.put(territories.get(i).getName(), territories.get(i));
		}
		return this.territoryNames;
	}

	public void reader(String file) throws FileNotFoundException {
		in = new BufferedReader(new FileReader(new File(file)));
	}

	public ArrayList<Territory> getTerritories() {
		return this.territories;
	}

	public ArrayList<Continent> getContinents() {
		return this.continents;
	}

	public Map<String, String[]> getAdjacencies() {
		return this.adjacencies;
	}

}
