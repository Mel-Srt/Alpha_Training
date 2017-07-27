package model;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class LetterAlphabet extends Character {

	public LetterAlphabet(char name){
		this.name = name;
		this.sound = new File("sounds/" + name + ".WAV");
	}

	public void playSound() {
		try {
                        Thread.sleep(200);
                        
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.sound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
