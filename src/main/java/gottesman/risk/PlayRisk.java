package gottesman.risk;

import gottesman.risk.map.BoardView;
import gottesman.risk.map.DeployingController;
import gottesman.risk.map.FortifyController;
import gottesman.risk.map.MoveOrAttackController;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayRisk extends JFrame implements GameStateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DeployingController deployingController;
	private MoveOrAttackController moveOrAttackController;
	private FortifyController fortifyController;
	private DataManager dataManager;
	private BoardView boardView;
	private GameState gameState;
	private JLabel phaseLabel;

	public PlayRisk() throws IOException {

		setTitle("Risk");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		dataManager = new DataManager();
		boardView = new BoardView(dataManager.getTerritories());

		gameState = new GameState(dataManager, this, 2);
		deployingController = new DeployingController(gameState);
		moveOrAttackController = new MoveOrAttackController(gameState, dataManager);
		fortifyController = new FortifyController(gameState, dataManager);
		
		boardView.setGameController(deployingController);

		container.add(boardView, BorderLayout.CENTER);
		
		phaseLabel = new JLabel();
		phaseLabel.setOpaque(true);
		container.add(phaseLabel, BorderLayout.SOUTH);

		gameState.startGame();
		
		onPhaseChange(GameState.Phase.DEPLOY);
		
		// InputStream in = new FileInputStream("Sound/risk music .wav");
		// AudioStream music = new AudioStream(in);
		// AudioPlayer.player.start(music);

	}
	
	public void onPhaseChange(GameState.Phase phase) {
		switch(phase) {
		case DEPLOY:
			boardView.setGameController(deployingController);
			break;
		case MOVE:
			boardView.setGameController(moveOrAttackController);
			break;
		case FORTIFY:
			boardView.setGameController(fortifyController);
			break;
		}
		
		phaseLabel.setBackground(gameState.getActivePlayer().getColor());
		phaseLabel.setText(phase.name());
	}

	public static void main(String[] args) {
		try {
			new PlayRisk().setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
