package model;

public class ScoreLine implements Comparable<ScoreLine> {

    private String date;
    private float score;

    public ScoreLine(String date, float score) {
        this.date = date;
        this.score = score;
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
        if(compareScore2 - this.score  < 0)return -1;
        else return 1;
    }

 
}
