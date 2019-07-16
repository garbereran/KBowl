package kbowl;

public class Game {

    private static final int PERFECT_GAME_SCORE = 300;
    Frame[] game;
    private int currentFrame;
    private int currentRoll;
    private int gameLength;
    private int maxFrameScore;


    public Game(int gameLength, int maxFrameScore){
        this.gameLength = gameLength;
        this.maxFrameScore = maxFrameScore;
        game = new Frame[gameLength+1];
        currentFrame = 0;
        currentRoll = 0;
    }

    public void roll(int pins){
        if(game[currentFrame] == null){
            game[currentFrame] = new Frame(maxFrameScore);
        }

        Frame frame = game[currentFrame];
        //first roll of frame
        if(currentRoll %2 == 0){
            handleFirstRoll(pins, frame);
        } else { //second roll of frame
            handleSecondFrame(pins, frame);
        }

        currentRoll++;
    }

    private void handleFirstRoll(int pins, Frame frame) {
        frame.setFirstRoll(pins);
        handleSpareBonus(frame);
        if (frame.isStrike()){
            handleStrikeBonus(frame);
            currentRoll++;
            currentFrame++;
        }
    }

    private void handleSpareBonus(Frame frame) {
        if(currentFrame > 0){
            Frame prevFrame = getFrame(currentFrame-1);
            if(prevFrame.isSpare()){
                int firstRoll = frame.getFirstRoll();
                prevFrame.setBonusScore(firstRoll);
            }
        }
    }

    private void handleSecondFrame(int pins, Frame frame) {
        frame.setSecondRoll(pins);
        handleStrikeBonus(frame);

        if(!frame.isStrike() && !frame.isSpare()){
            frame.setBonusScore(0);
        }
        currentFrame++;
    }

    private void handleStrikeBonus(Frame frame) {
        if(currentFrame > 0){
            Frame prevFrame = getFrame(currentFrame-1);
            if(prevFrame.isStrike()){
                int bonus;
                bonus = frame.getFirstRoll();
                if(!frame.isStrike()){
                    bonus += frame.getSecondRoll();
                }

                prevFrame.setBonusScore(bonus);
            }
        }
    }

    public int score(){
        int score = 0;
        boolean perfectGame = true;
        for(int i = 0 ; i < currentFrame ; i++){
            Frame frame = getFrame(i);
            if(frame.isFrameOver()){
                score += frame.getFrameScore();
                if(!frame.isStrike()){
                    perfectGame = false;
                }
            }
        }

        return perfectGame ? PERFECT_GAME_SCORE : score;
    }

    protected Frame getFrame(int frameNumber){
        return game[frameNumber];
    }



    public int getGameLength(){
        return gameLength;
    }

}
