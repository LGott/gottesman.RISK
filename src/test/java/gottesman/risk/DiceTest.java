package gottesman.risk;

import junit.framework.Assert;

import org.junit.Test;


public class DiceTest {
	@Test
	public void testDice() {

		Dice dice = new Dice();
		Assert.assertNotNull(dice.rollThree());
		Assert.assertNotNull(dice.rollTwo());
		Assert.assertNotNull(dice.rollOne());
	}

}
