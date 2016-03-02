package gottesman.risk;

import junit.framework.Assert;

import org.junit.Test;

public class CardDeckTest {

	@Test
	public void testDrawCard() {
		CardDeck cardDeck = new CardDeck();
		BattalionType battalionType = null;

		try {
			battalionType = cardDeck.drawCard();
		} catch (DeckEmptyException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(battalionType);
	}

	@Test
	public void testDrawAllCards() {
		CardDeck cardDeck = new CardDeck();
		for (int i = 0; i < 42; i++) {

			try {
				cardDeck.drawCard();
			} catch (DeckEmptyException e) {
				e.printStackTrace();
			}

		}
	}

	@Test(expected = DeckEmptyException.class)
	public void testOverdraw() throws DeckEmptyException {

		CardDeck cardDeck = new CardDeck();
		for (int i = 0; i < 100; i++) {

			cardDeck.drawCard();
		}
	}

}
