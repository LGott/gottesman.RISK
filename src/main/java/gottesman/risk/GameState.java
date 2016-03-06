package gottesman.risk;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameState {

	private List<Color> players;
	private int currentPlayer;
	
	public GameState() {
		players = new ArrayList<Color>();
		players.add(Color.RED);
		players.add(Color.GREEN);
		players.add(Color.BLUE);
		players.add(Color.ORANGE);
	}

	public Color getActivePlayer() {
		return players.get(currentPlayer);
	}
	
	public void nextPlayer() {
		currentPlayer++;
		if ( currentPlayer >= players.size() ) {
			currentPlayer = 0;
		}
	}

	public List<Color> getPlayers() {
		return players;
	}
	
}
