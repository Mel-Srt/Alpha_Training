package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreFile {

    private static ScoreFile instance;
    File f;

    //Using: call ScoreFile.getInstance().method();
    public static synchronized ScoreFile getInstance() {
        if (instance == null) {
            instance = new ScoreFile();
        }
        return instance;
    }

    //New Score
    public void addNewScore(int gameType, String score) {
        //        1 -> Alphabet
        //        2 -> Vowels
        //        3 -> Consonnants

        String pathFile;
        switch (gameType) {
            case 1:
                pathFile = "scores/L1.sc";
                break;
            case 2:
                pathFile = "scores/L2.sc";
                break;
            case 3:
                pathFile = "scores/L3.sc";
                break;
            default:
                pathFile = "";
                break;
        }
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter(pathFile, true));
            // get instance of Calendar class
            Calendar now = Calendar.getInstance();

            out.write(now.get(Calendar.DATE) + "-" + (now.get(Calendar.MONTH) + 1)
                    + "-" + now.get(Calendar.YEAR) + " "
                    + now.get(Calendar.HOUR_OF_DAY) + ":"
                    + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND)
                    + ";" + score + '\n');
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ScoreFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile(int gameType) {
        String pathFile;
        switch (gameType) {
            case 1:
                pathFile = "scores/L1.sc";
                break;
            case 2:
                pathFile = "scores/L2.sc";
                break;
            case 3:
                pathFile = "scores/L3.sc";
                break;
            default:
                pathFile = "";
                break;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(";");
                String sDate= parts[0]; 
                String sScore = parts[1]; 
                System.out.println("Date: " + sDate + " Score: " + sScore);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
