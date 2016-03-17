package gottesman.risk;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class MusicThread extends Thread {

	private AudioClip clip;

	public MusicThread() {
	}

	@Override
	public void run() {

		URL url = getClass().getResource("/Sound/risk music .wav");
		clip = Applet.newAudioClip(url);
		clip.play();
	}

	public void stopMusic() {
		clip.stop();
	}

}
