package gottesman.risk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class PlayRisk extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button;

	public PlayRisk() throws IOException {

		setTitle("Risk");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.BLACK);
		StartPanel panel = new StartPanel();
		container.add(panel, BorderLayout.CENTER);

		button = new JButton("PLAY GAME!");
		button.setPreferredSize(new Dimension(150, 40));
		container.add(button, BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				// dispose();
				// new GameBoard().setVisible(true);

			}
		});

		/*
		 * InputStream in = new FileInputStream("risk music .wav");
		 * 
		 * AudioStream music = new AudioStream(in);
		 * 
		 * AudioPlayer.player.start(music);
		 */
	}

	public static void main(String[] args) {
		try {
			new PlayRisk().setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
