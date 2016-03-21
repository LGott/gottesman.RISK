package gottesman.risk.playGame;

import gottesman.risk.DataManager;
import gottesman.risk.battle.DeckEmptyException;
import gottesman.risk.controllers.DeployingController;
import gottesman.risk.controllers.FortifyController;
import gottesman.risk.controllers.MoveOrAttackController;
import gottesman.risk.map.BoardView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
	private JTextArea terLabel;
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
		final JPanel phasePanel = new JPanel();
		final JButton startButton = new JButton("PLAY GAME!");
		final JButton cardButton = new JButton();

		container.add(startPanel, BorderLayout.CENTER);
		container.add(startButton, BorderLayout.NORTH);
		container.add(phasePanel, BorderLayout.SOUTH);

		startButton.setPreferredSize(new Dimension(150, 40));
		phasePanel.setPreferredSize(new Dimension(80, 80));
		cardButton.setPreferredSize(new Dimension(200, 50));
		phasePanel.setBackground(Color.BLACK);
		cardButton.setText("View Current Player Cards");

		terLabel = new JTextArea("");
		terLabel.setForeground(Color.WHITE);
		terLabel.setPreferredSize(new Dimension(150, 50));
		terLabel.setOpaque(false);
		terLabel.setLineWrap(true);
		terLabel.setWrapStyleWord(true);
		terLabel.setBackground(Color.BLACK);
		terLabel.setEditable(false);

		phasePanel.setVisible(false);
		phasePanel.add(phaseButton = new JButton());
		phasePanel.add(cardButton);
		phasePanel.add(terLabel);

		phaseButton.setOpaque(true);
		phaseButton.setPreferredSize(new Dimension(200, 50));
		phaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameState.nextPhase();
				} catch (DeckEmptyException e1) {
					e1.printStackTrace();
				}
			}
		});

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startPanel.setVisible(false);
				container.add(boardView, BorderLayout.CENTER);
				startButton.setVisible(false);
				phasePanel.setVisible(true);
			}
		});
		cardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameState.getActivePlayer().getCards().size() == 0) {
					JOptionPane.showMessageDialog(null, "No Cards Yet");
				} else {
					JOptionPane.showMessageDialog(null, gameState.getActivePlayer().getCards().toString());
				}
			}
		});

		playMusic = new MusicThread();

		try {
			gameState.startGame();
		} catch (DeckEmptyException e1) {
			e1.printStackTrace();
		}
		startMusic();
	}

	@Override
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
		phaseButton.setText("Current Phase: " + phase.name());
		terLabel.setText("Player 1: " + String.valueOf(gameState.getPlayers().get(0).getTerritories().size())
				+ " Territories\n" + "Player 2: "
				+ String.valueOf(gameState.getPlayers().get(1).getTerritories().size()) + " Territories\n"
				+ "Player 3: " + String.valueOf(gameState.getPlayers().get(2).getTerritories().size() + " Territories"));
	}

	public void startMusic() {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				playMusic.run();
			}
		}, 0, 158, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		try {
			new PlayRisk().setVisible(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
