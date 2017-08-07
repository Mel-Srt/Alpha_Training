package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreFile {

    private static ScoreFile instance;
    private File f;

    //Using: call ScoreFile.getInstance().method();
    public static synchronized ScoreFile getInstance() {
        if (instance == null) {
            instance = new ScoreFile();
        }
        return instance;
    }

    //Save a new Score in a file, return the new ScoreLine added
    public ScoreLine addNewScore(int gameType, String score, String pseudo) {
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
            case 4:
                pathFile = "scores/L4.sc";
                break;
            default:
                pathFile = "";
                break;
        }
        BufferedWriter out;
        Calendar now = Calendar.getInstance();
        DecimalFormat df = new DecimalFormat("#00"); // Set your desired format here.
        String sDate = df.format(Float.valueOf(now.get(Calendar.MONTH) + 1)) + "-"
                + df.format(Float.valueOf(now.get(Calendar.DATE) + "")) + "-"
                + df.format(Float.valueOf(now.get(Calendar.YEAR) + "")) + " "
                + df.format(Float.valueOf(now.get(Calendar.HOUR_OF_DAY) + "")) + ":"
                + df.format(Float.valueOf(now.get(Calendar.MINUTE) + "")) + ":"
                + df.format(Float.valueOf(now.get(Calendar.SECOND) + ""));
        try {
            out = new BufferedWriter(new FileWriter(pathFile, true));
            // get instance of Calendar class
            out.write(pseudo + ";" + sDate + ";" + score + ";" + '\n');
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ScoreFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        ScoreLine aScoreLine = new ScoreLine(sDate, pseudo, Float.parseFloat(score));
        return aScoreLine;
    }

    public List<ScoreLine> readFile(int gameType) {
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
            case 4:
                pathFile = "scores/L4.sc";
                break;
            default:
                pathFile = "";
                break;
        }
        List<ScoreLine> scoreLines = new ArrayList<ScoreLine>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] parts = sCurrentLine.split(";");
                String sPseudo = parts[0];
                String sDate = parts[1];
                String sScore = parts[2];
                ScoreLine aScoreLine = new ScoreLine(sDate, sPseudo, Float.parseFloat(sScore));
                scoreLines.add(aScoreLine);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(scoreLines);

        //After the sort, write only the 10 top score
        BufferedWriter out;
        int nbScoreLines = scoreLines.size();
        int limit = 10;
        if (nbScoreLines < 10) {
            limit = nbScoreLines;
        }
        try {
            out = new BufferedWriter(new FileWriter(pathFile, false));
            out.write("");
            out = new BufferedWriter(new FileWriter(pathFile, true));
            for (int i = 0; i < limit; i++) {
                System.out.println((scoreLines.get(i).getPseudo()+ ";" + scoreLines.get(i).getDate() + ";" + scoreLines.get(i).getScore() + ";"));
                out.write(scoreLines.get(i).getPseudo()+ ";" + scoreLines.get(i).getDate() + ";" + scoreLines.get(i).getScore() + ";" + '\n'); 
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ScoreFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scoreLines;
    }

    /**
     * ****** Bucket sort (tri casier) *****
     */
    public static void triCasier(int[] tableau, int longueurDuTableau) {
        int longueur = longueurDuTableau;

        //On parcourt le tableau afin d'en récupérer les valeurs minimales et maximales
        int mini = tableau[0];
        int maxi = tableau[0];

        for (int i = 1; i < longueur; i++) {
            if (mini > tableau[i]) {
                mini = tableau[i];
            }
            if (maxi < tableau[i]) {
                maxi = tableau[i];
            }
        }

        //On construit un tableau contenant le nombre d'entiers maxi-mini+1
        int tmpLong = maxi - mini + 1;
        int[] tmpTableau = new int[tmpLong];

        //on initilise le tableau à 0
        for (int i = 0; i < tmpLong; i++) {
            tmpTableau[i] = 0;
        }

        //Puis on incrémente le compteur correspondant à chaque entier trouvé
        for (int i = 0; i < longueur; i++) {
            tmpTableau[tableau[i] - mini]++;
        }

        //Enfin, on reconstitue le tableau initial classé
        int compt = 0;
        for (int i = 0; i < tmpLong; i++) {
            while (tmpTableau[i] > 0) {
                tableau[compt] = mini + i;
                tmpTableau[i]--;
                compt++;
            }
        }
    }
}
