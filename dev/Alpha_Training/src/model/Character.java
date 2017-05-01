package model;
import java.io.File;


public abstract class Character {
	char name;
	File sound;
	
	public char getName(){
		return name;
	}
	public abstract void playSound();	
}
