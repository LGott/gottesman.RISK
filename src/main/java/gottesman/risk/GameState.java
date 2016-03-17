package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

public class GameState {

	/**
	 * Represents the phases of turn;
	 */
	public enum Phase {
		DEPLOY, MOVE, FORTIFY
	}

	private static final Color colors[] = new Color[] { Color.WHITE, Color.GREEN, Color.BLUE, Color.RED, Color.MAGENTA,
			Color.CYAN };

	private List<Player> players;
	private int currentPlayer;
	private Phase phase;

	private GameStateListener gameStateListener;

	private DataManager dataManager;

	public GameState(DataManager dataManager, GameStateListener gameStateListener, int numPlayers) {
		players = new ArrayList<Player>();
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player(colors[i]));
		}
		this.dataManager = dataManager;
		this.gameStateListener = gameStateListener;
	}

	/**
	 * Divide up the map randomly
	 */
	public void startGame() {
		List<Player> players = getPlayers();
		final int numPlayers = players.size();
		final int battalionsPerPlayer = 40 - ((numPlayers - 2) * 5);
		final Random random = new Random();
		List<Territory> territories = dataManager.getTerritories();
		Collections.shuffle(territories);
		int territoryIndex = 0;

		// For each player, occupy a random list of territories with a
		// random amount of battalions in each territory

		for (Player player : players) {
			int battalionsLeft = battalionsPerPlayer;
			while ((battalionsLeft > 0)) {
				Territory t = territories.get(territoryIndex);
				int maxBattalions = Math.min(6, battalionsLeft);
				int battalions = random.nextInt(maxBattalions) + 1;
				t.occupy(player);
				t.setBattalions(battalions);
				player.addTerritory(t);
				battalionsLeft -= battalions;
				territoryIndex++;
			}
		}

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
	 */
	public Phase nextPhase() {
		switch (phase) {
		case DEPLOY:
			phase = Phase.MOVE;
			break;
		case MOVE:
			phase = Phase.FORTIFY;
			break;
		case FORTIFY:
			phase = Phase.DEPLOY;
			nextPlayer();
			calculateBattalionsToDeploy();
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
			// Check for winner before each turn starts
			if (occupiedTerritories.containsAll(territories)) {
				JOptionPane.showMessageDialog(null, player + " has conquered the world. Game over!");
			}
		}

		int battalionsToDeploy = (int) Math.floor(occupiedTerritories.size() / 3.00);

		List<Continent> continents = dataManager.getContinents();
		for (Continent c : continents) {
			if (c.isFullyOccupied(occupiedTerritories)) {
				battalionsToDeploy += c.getBonus();
			}
		}

		player.setBattalionsToDeploy(Math.max(battalionsToDeploy, 3));
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

}
