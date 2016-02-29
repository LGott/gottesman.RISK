package gottesman.risk;

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
		card = BattalionType.values()[rand.nextInt(3)];

		for (int i = 0; i < deck.size(); i++) {

			deck.add(card);
		}
	}

	public BattalionType drawCard() {

		int num = rand.nextInt(deck.size());

		BattalionType cardDrawn = deck.get(num);
		deck.remove(num);

		return cardDrawn; // Remove the card from the arrayList so that it cannot be drawn again

	}

}
