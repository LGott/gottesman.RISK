package gottesman.risk;

import junit.framework.Assert;

import org.junit.Test;

// TODO: this class should be named DiceTest
public class TestDice {
	@Test
	public void testDice() {

		Dice dice = new Dice();
		Assert.assertNotNull(dice.rollThree());
		Assert.assertNotNull(dice.rollTwo());
		Assert.assertNotNull(dice.rollOne());
	}

}
