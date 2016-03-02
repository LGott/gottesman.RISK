package gottesman.risk;

import junit.framework.Assert;

import org.junit.Test;

public class CardDeckTest {

	@Test
	public void testDrawCard() {

		CardDeck cardDeck = new CardDeck();
		BattalionType battalionType = cardDeck.drawCard();
		Assert.assertNotNull(battalionType);

	}

}
