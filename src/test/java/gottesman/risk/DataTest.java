package gottesman.risk;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class DataTest {

	DataManager data;

	public DataTest() {

		data = new DataManager(getClass().getResource("/Territories.txt").getFile(), getClass().getResource(
				"/Continents.txt").getFile(), getClass().getResource("/Adjacencies.txt").getFile());
	}

	@Test
	public void testTerritory() {
		try {

			data.loadTerritories();
			Assert.assertNotNull(data.getTerritories());

		} catch (IOException e) {
			System.out.println("File not found");
		} catch (NumberFormatException e) {
		}

	}

	@Test
	public void testContinent() {
		try {

			data.loadContinents();
			Assert.assertNotNull(data.getContinents());

		} catch (IOException e) {
			System.out.println("File not found");
		} catch (NumberFormatException e) {
		}

	}

	@Test
	public void testAdjacencies() {
		try {

			data.loadAdjacencies();
			Assert.assertNotNull(data.getAdjacencies());

		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	@Test
	public void testNameMap() {

		Assert.assertNotNull(data.getTerritoryNames());
	}
}
