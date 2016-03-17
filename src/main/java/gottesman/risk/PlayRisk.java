package gottesman.risk;

import gottesman.risk.map.BoardView;
import gottesman.risk.map.DeployingController;
import gottesman.risk.map.FortifyController;
import gottesman.risk.map.MoveOrAttackController;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

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
	private JButton phaseButton;
	private MusicThread playMusic;

	public PlayRisk() throws IOException {

		setTitle("Risk");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);

		final Container container = getContentPane();
		container.setLayout(new BorderLayout());

		dataManager = new DataManager();
		boardView = new BoardView(dataManager.getTerritories());

		gameState = new GameState(dataManager, this, 3);
		deployingController = new DeployingController(gameState);
		moveOrAttackController = new MoveOrAttackController(gameState, dataManager);
		fortifyController = new FortifyController(gameState, dataManager);
		boardView.setGameController(deployingController);

		final StartPanel startPanel = new StartPanel();
		container.add(startPanel, BorderLayout.CENTER);
		final JButton startButton = new JButton("PLAY GAME!");
		startButton.setPreferredSize(new Dimension(150, 40));
		container.add(startButton, BorderLayout.NORTH);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startPanel.setVisible(false);
				container.add(boardView, BorderLayout.CENTER);
				container.add(phaseButton, BorderLayout.SOUTH);
				startButton.setVisible(false);
			}
		});

		phaseButton = new JButton();
		phaseButton.setOpaque(true);
		phaseButton.setPreferredSize(new Dimension(120, 30));
		phaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameState.nextPhase();
			}
		});

		gameState.startGame();

		// InputStream in = new FileInputStream("Sound/risk music .wav");
		// AudioStream music = new AudioStream(in);
		// AudioPlayer.player.start(music);
		// playMusic = new MusicThread();
		// playMusic.run();
	}

	public void onPhaseChange(GameState.Phase phase) {
		switch (phase) {
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

		phaseButton.setBackground(gameState.getActivePlayer().getColor());
		phaseButton.setText(phase.name());
	}

	public MusicThread getMusic() {
		return this.playMusic;
	}

	public static void main(String[] args) {
		try {
			new PlayRisk().setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
