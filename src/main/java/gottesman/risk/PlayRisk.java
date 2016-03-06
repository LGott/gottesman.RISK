package gottesman.risk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import gottesman.risk.map.BoardView;
import gottesman.risk.map.MoveOrAttackController;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		DataManager dataManager = new DataManager(
				getClass().getResourceAsStream("/Territories.txt"), 
				getClass().getResourceAsStream("/Continents.txt"), 
				getClass().getResourceAsStream("/Adjacencies.txt"));
		dataManager.loadTerritories();
		BoardView boardView = new BoardView(dataManager.getTerritories(), new MoveOrAttackController());
		container.add(boardView, BorderLayout.CENTER);

//		InputStream in = new FileInputStream("Sound/risk music .wav");
//
//		AudioStream music = new AudioStream(in);
//
//		AudioPlayer.player.start(music);

	}

	public static void main(String[] args) {
		try {
			new PlayRisk().setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
