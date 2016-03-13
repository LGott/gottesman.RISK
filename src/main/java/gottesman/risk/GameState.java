package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameState {

	private List<Player> players;
	private int currentPlayer;

	public GameState() {
		players = new ArrayList<Player>();
		players.add(new Player(Color.GREEN));
		players.add(new Player(Color.RED));
		players.add(new Player(Color.BLUE));
		players.add(new Player(Color.ORANGE));
	}

	public Player getActivePlayer() {
		return players.get(currentPlayer);
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
