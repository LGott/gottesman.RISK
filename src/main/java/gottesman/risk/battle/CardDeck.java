package gottesman.risk.battle;

import gottesman.risk.BattalionType;

import java.util.ArrayList;
import java.util.Random;

public class CardDeck {

	BattalionType card;
	ArrayList<BattalionType> deck;
	Random rand;

	public CardDeck() {

		// There are 42 countries on the map, so there are 42 cards in deck
		this.deck = new ArrayList<BattalionType>(42);

		rand = new Random();

		fillDeck();

	}

	public void fillDeck() {

		// Fill the deck with random cards based on battalion type

		for (int i = 0; i < 42; i++) {
			card = BattalionType.values()[rand.nextInt(3)];
			deck.add(card);

		}
	}

	public BattalionType drawCard() throws DeckEmptyException {

		if (deck.isEmpty() == true) {
			throw new DeckEmptyException();
		}

		int num = rand.nextInt(deck.size());

		BattalionType cardDrawn = deck.get(num);
		deck.remove(num); // Remove the card from the arrayList so that it cannot be drawn again
		return cardDrawn;

	}

}
