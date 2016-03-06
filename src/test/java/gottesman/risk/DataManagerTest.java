package gottesman.risk;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class DataManagerTest {

	DataManager data;

	public DataManagerTest() {
		data = new DataManager(getClass().getResource("/Territories.txt").getFile(),
				getClass().getResource("/Continents.txt").getFile(),
				getClass().getResource("/Adjacencies.txt").getFile());
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
