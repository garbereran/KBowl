package kbowl;

public class Frame {
    private int firstRoll;
    private int secondRoll;
    private int bonusScore;
    private int maxFrameScore;

    public Frame(int maxFrameScore){
        firstRoll = -1;
        secondRoll = -1;
        bonusScore = -1;
        this.maxFrameScore = maxFrameScore;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public void setFirstRoll(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
    }

    public int getBonusScore() {
        return bonusScore;
    }

    public void setBonusScore(int bonusScore) {
        this.bonusScore = bonusScore;
    }

    public boolean isFrameOver() {
        return this.bonusScore != -1;
    }

    public boolean isStrike(){
        return this.firstRoll == maxFrameScore;
    }

    public boolean isSpare(){
        return !isStrike() && (this.firstRoll + this.secondRoll == maxFrameScore);
    }

    protected int getFrameScore(){
        int score = -1;
        if(isFrameOver()) {
            score = getFirstRoll() +
                    getBonusScore();
            if(!isStrike()){
                score += getSecondRoll();
            }
        }
        return score;
    }

    public int getMaxFrameScore() {
        return maxFrameScore;
    }
}
