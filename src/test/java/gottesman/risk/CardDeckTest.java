package gottesman.risk;

import org.junit.Test;

import junit.framework.Assert;

public class CardDeckTest {

	@Test
	public void testDrawCard() {
		
		CardDeck cardDeck = new CardDeck();
		BattalionType battalionType = cardDeck.drawCard();
		Assert.assertNotNull(battalionType);
		
	}
	
}
