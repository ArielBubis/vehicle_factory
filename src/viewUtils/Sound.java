/**
 *
 */
package viewUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class Sound implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4201247468066792788L;
	/**
	 *
	 */
	// private static File soundFile;
	private static Clip clip;
	private static AudioInputStream inputStream;
	private static InputStream audioSrc;
	private static BufferedInputStream bufferedIn;
	private Boolean soundBoolean;
	private static Sound sound;

	private Sound() {
		this.soundBoolean = true;
	}
	public static Sound create() {
		if (sound == null)
			sound = new Sound();
		return sound;
	}
	public void soundCheck(Boolean checkBoxSelection) {
		this.soundBoolean = checkBoxSelection;
		if(soundBoolean) {
			playSound("blop");
			JOptionPane.showMessageDialog(null, "Sound ON", "Sound Check",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Sound OFF", "Sound Check",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void playSound(String soundName) {
		if (soundBoolean) {
			audioSrc = Sound.class.getClassLoader().getResourceAsStream("Sounds/" + soundName + ".wav");
			bufferedIn = new BufferedInputStream(audioSrc);
			try {
				clip = AudioSystem.getClip();
				inputStream = AudioSystem.getAudioInputStream(bufferedIn);
				clip.open(inputStream);

			} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.start();
		}
	}
	/**
	 * @return the soundBoolean
	 */
	public Boolean getSoundBoolean() {
		return soundBoolean;
	}
}
