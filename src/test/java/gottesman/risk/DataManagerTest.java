package gottesman.risk;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class DataManagerTest {

	DataManager data;

	public DataManagerTest() {
		data = new DataManager(getClass().getResourceAsStream("/Territories.txt"), getClass().getResourceAsStream(
				"/Continents.txt"), getClass().getResourceAsStream("/Adjacencies.txt"));
	}

	@Test
	public void testTerritory() throws NumberFormatException, IOException {
		data.loadTerritories();
		Assert.assertNotNull(data.getTerritories());
	}

	@Test
	public void testContinent() throws NumberFormatException, IOException {
		data.loadContinents();
		Assert.assertNotNull(data.getContinents());

	}

	@Test
	public void testAdjacencies() throws IOException {
		data.loadAdjacencies();
		Assert.assertNotNull(data.getAdjacencies());
	}

	@Test
	public void testNameMap() {
		Assert.assertNotNull(data.getTerritoryNames());
	}
}
