package gottesman.risk;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class DataManagerTest {

	DataManager data;

	public DataManagerTest() throws NumberFormatException, IOException {
		data = new DataManager();
	}

	@Test
	public void testTerritory() throws NumberFormatException, IOException {
		data.getTerritories();
		Assert.assertNotNull(data.getTerritories());
	}

	@Test
	public void testContinent() throws NumberFormatException, IOException {
		data.getContinents();
		Assert.assertNotNull(data.getContinents());

	}

	@Test
	public void testAdjacencies() throws IOException {
		data.getAdjacencies();
		Assert.assertNotNull(data.getAdjacencies());
	}

	@Test
	public void testNameMap() {
		Assert.assertNotNull(data.getTerritoryNames());
	}
}
