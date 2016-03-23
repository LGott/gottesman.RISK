package gottesman.risk.playGame;

import gottesman.risk.BattalionType;
import gottesman.risk.Continent;
import gottesman.risk.DataManager;
import gottesman.risk.Player;
import gottesman.risk.Territory;
import gottesman.risk.battle.CardDeck;
import gottesman.risk.battle.DeckEmptyException;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GameState {

	/**
	 * Represents the phases of turn;
	 */
	public enum Phase {
		DEPLOY, MOVE, FORTIFY
	}

	private static final Color colors[] = new Color[] { Color.WHITE, Color.GREEN, Color.CYAN, Color.BLUE, Color.RED, Color.MAGENTA, };

	private List<Player> players;
	private int currentPlayer;
	private Phase phase;
	private boolean conquer = false;
	private GameStateListener gameStateListener;
	private DataManager dataManager;
	private CardDeck deck;
	private int matchNum;
	private int cardBonus;

	public GameState(DataManager dataManager, GameStateListener gameStateListener, int numPlayers) {
		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(colors[i]));
		}
		this.dataManager = dataManager;
		this.gameStateListener = gameStateListener;
		this.matchNum = 0;
		this.cardBonus = 4; // First card bonus starts off as 4
	}

	/**
	 * Divide up the map randomly
	 * 
	 * @throws DeckEmptyException
	 */
	public void startGame() throws DeckEmptyException {
		List<Player> players = getPlayers();
		final int numPlayers = players.size();
		final int battalionsPerPlayer = 40 - ((numPlayers - 2) * 5);
		final Random random = new Random();
		List<Territory> territories = dataManager.getTerritories();
		Collections.shuffle(territories);

		// For each player, occupy a random list of territories with a
		// random amount of battalions in each territory

		int start = 0;
		int territoriesPerPlayer = territories.size() / numPlayers;
		for (Player player : players) {

			List<Territory> list = territories.subList(start, start + territoriesPerPlayer);
			int battalionsLeft = battalionsPerPlayer;

			// give every territory one battalion to start
			for (Territory t : list) {
				t.occupy(player);
				t.setBattalions(1);
				player.addTerritory(t);
				battalionsLeft--;
			}

			// deploy a random amount of battalions
			for (Territory t : list) {
				int maxBattalions = Math.min(6, battalionsLeft);
				int battalions = random.nextInt(maxBattalions) + 1;
				t.increaseBattalions(battalions);
				battalionsLeft -= battalions;
				if (battalionsLeft <= 0) {
					break;
				}
			}
			start += territoriesPerPlayer;
		}

		// Initialize the card Deck
		deck = new CardDeck();
		deck.fillDeck();

		// Set up the correct player by setting it as the last player and then incrementing the phase.
		currentPlayer = players.size();

		phase = Phase.FORTIFY;
		nextPhase();
	}

	public Player getActivePlayer() {
		return players.get(currentPlayer);
	}

	/**
	 * Increments the phase of the turn or changes to the next player
	 * 
	 * @throws DeckEmptyException
	 */
	public Phase nextPhase() throws DeckEmptyException {

		switch (phase) {
		case DEPLOY:
			phase = Phase.MOVE;
			break;
		case MOVE:
			phase = Phase.FORTIFY;
			if (conquer == true) { // Only draw card if player has captured a territory
				drawCard();
			}
			conquer = false; // Reset conquer to false
			break;
		case FORTIFY:
			phase = Phase.DEPLOY;
			nextPlayer();
			calculateBattalionsToDeploy();
			break;

		default:
			break;
		}

		gameStateListener.onPhaseChange(phase);
		return phase;
	}

	/**
	 * Calculates the amount of Battalions the active player should deploy at the start of the deploy phase.
	 */
	private void calculateBattalionsToDeploy() {
		Player player = this.getActivePlayer();
		List<Territory> territories = dataManager.getTerritories();
		Set<String> occupiedTerritories = new HashSet<String>();
		for (Territory t : territories) {
			if (t.isOccupiedBy(player)) {
				occupiedTerritories.add(t.getName());
			}
		}
		int battalionsToDeploy = (int) Math.floor(occupiedTerritories.size() / 3.00);

		List<Continent> continents = dataManager.getContinents();
		for (Continent c : continents) {
			if (c.isFullyOccupied(occupiedTerritories)) {
				battalionsToDeploy += c.getBonus();
			}
		}

		boolean cardMatch = this.getActivePlayer().checkCardMatch();
		if (cardMatch) {
			battalionsToDeploy += checkMatch();
			matchNum++;
		}

		player.setBattalionsToDeploy(Math.max(battalionsToDeploy, 3));
		System.out.println(occupiedTerritories.toString());
	}

	private int checkMatch() {
		// According to game rules, the first 5 times that a match is traded in, the bonus increments by 2.
		// After the 5th time, the bonus increments by 5.

		if (matchNum >= 6) {
			cardBonus += 5;
		} else {
			cardBonus += 2;
		}
		return cardBonus;
	}

	public void nextPlayer() {
		currentPlayer++;
		if (currentPlayer >= players.size()) {
			currentPlayer = 0;
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	private void drawCard() throws DeckEmptyException {
		BattalionType card = deck.drawCard();
		players.get(currentPlayer).addCard(card);

		String icon = getImageIcon(card.name());

		JOptionPane.showMessageDialog(null, "You have earned a " + card.name() + " \nterritory card", "Card",
				JOptionPane.PLAIN_MESSAGE, new ImageIcon(getClass().getResource(icon)));
	}

	private String getImageIcon(String card) {
		switch (card) {
		case "CAVALRY":
			return "/Images/cavalry.png";
		case "CANNON":
			return "/Images/cannon.png";
		case "INFINTRY":
			return "/Images/infantry.png";
		}
		return null;

	}

	public void setConquer(boolean conquer) {
		this.conquer = conquer;
	}
}
