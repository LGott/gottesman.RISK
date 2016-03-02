package gottesman.risk;

public class DeckEmptyException extends Exception {
	public DeckEmptyException() {
		super("Deck is empty. Cannot Draw card.");
	}
}
