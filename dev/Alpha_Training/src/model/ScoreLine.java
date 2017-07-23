package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ScoreLine implements Comparable<ScoreLine> {

    private String date;
    private String pseudo;
    private float score;
    private String scoreFormat;

    public ScoreLine(String date, String pseudo, float score) {
        this.date = date;
        this.score = score;
        this.pseudo = pseudo;

        DecimalFormat df = new DecimalFormat("#0.00"); // Set your desired format here.
        this.scoreFormat = df.format(score);
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    public int compareTo(ScoreLine score2) {
        float compareScore2 = score2.getScore();

        //If Score 2 < this score, this score is first, we return -1
        if (compareScore2 - this.score < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * @return the scoreFormat
     */
    public String getScoreFormat() {
        return scoreFormat;
    }

    /**
     * @param scoreFormat the scoreFormat to set
     */
    public void setScoreFormat(String scoreFormat) {
        this.scoreFormat = scoreFormat;
    }

}
