package gottesman.risk;

import gottesman.risk.map.BoardView;
import gottesman.risk.map.MoveOrAttackController;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

public class PlayRisk extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayRisk() throws IOException {

		setTitle("Risk");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		GameState gameState = new GameState();
		DataManager dataManager = new DataManager();

		BoardView boardView = new BoardView(dataManager.getTerritories(), new MoveOrAttackController(gameState,
				dataManager));

		container.add(boardView, BorderLayout.CENTER);

		List<Territory> territories = dataManager.getTerritories();
		territories.get(0).occupy(gameState.getActivePlayer(), 52);

		// InputStream in = new FileInputStream("Sound/risk music .wav");
		//
		// AudioStream music = new AudioStream(in);
		//
		// AudioPlayer.player.start(music);

	}

	public static void main(String[] args) {
		try {
			new PlayRisk().setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
