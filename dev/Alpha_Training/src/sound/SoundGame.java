package sound;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundGame  implements Runnable{
	
		private File sound;
		
	public SoundGame(boolean bool){
		
		if(bool) this.sound = new File("sounds/right.WAV");
		else this.sound = new File("sounds/false.WAV");
	}

	public void run() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
