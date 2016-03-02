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
	
	@Test
	public void testDrawAllCards() {
		CardDeck cardDeck = new CardDeck();
		for ( int i=0; i<42; i++ ) {
			cardDeck.drawCard();
		}
	}
	
	@Test
	public void testOverdraw() {
		CardDeck cardDeck = new CardDeck();
		for ( int i=0; i<100; i++ ) {
			cardDeck.drawCard();
		}
	}

}
