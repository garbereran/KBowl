package kbowl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;
    private static int GAME_LENGTH = 10;
    private static int MAX_FRAME_SCORE = 10;

    @Before
    public void setup(){
        game = new Game(GAME_LENGTH, MAX_FRAME_SCORE);

    }

    @Test
    public void testKnockNotAllBallsInFrame(){
        //roles for frame 1
        game.roll(1);
        game.roll(2);
        //third roll to complete frame 1
        game.roll(3);

        Frame frame = game.getFrame(0);
        //verify frame was created correctly
        Assert.assertEquals(frame.getFirstRoll(), 1);
        Assert.assertEquals(frame.getSecondRoll(), 2);

        int frameScore = frame.getFrameScore();
        Assert.assertEquals(3, frameScore);
    }

    @Test
    public void testKnockDownNoneInFrame(){
        //roles for frame 1
        game.roll(0);
        game.roll(0);
        //third roll to complete frame 1
        game.roll(3);

        Frame frame = game.getFrame(0);

        int frameScore = frame.getFrameScore();
        Assert.assertEquals(0, frameScore);
    }

    @Test
    public void testKnockDownSpare(){
        //spare turn
        game.roll(5);
        game.roll(5);
        Frame spareFrame = game.getFrame(0);
        Assert.assertEquals(-1, spareFrame.getFrameScore());
        //next turn
        game.roll(4);
        game.roll(3);

        Frame regularFrame = game.getFrame(1);

        Assert.assertEquals(14, spareFrame.getFrameScore()); // 14 = 5 + 5 + 4 (frame1Roll1 + frame1Roll2 + frame2Roll1)
        Assert.assertEquals(7, regularFrame.getFrameScore()); // 14 = 5 + 5 + 4 (frame1Roll1 + frame1Roll2 + frame2Roll1)
    }

    @Test
    public void testKnockDownStrike(){
        //spare turn
        game.roll(10);
        Frame spareFrame = game.getFrame(0);
        Assert.assertEquals(-1, spareFrame.getFrameScore());
        //next turn
        game.roll(4);
        game.roll(3);

        Frame regularFrame = game.getFrame(1);

        Assert.assertEquals(17, spareFrame.getFrameScore()); // 17 = 10 + 4 + 3 (10 + frame3Roll1 + frame2Roll2)
        Assert.assertEquals(7, regularFrame.getFrameScore()); // 14 = 5 + 5 + 4 (frame1Roll1 + frame1Roll2 + frame2Roll1)
    }


    @Test
    public void exampleGame(){
        game.roll(1);
        game.roll(4);

        game.roll(4);
        game.roll(5);

        game.roll(4);
        game.roll(6);

        game.roll(5);
        game.roll(5);

        game.roll(10);

        game.roll(0);
        game.roll(1);

        game.roll(7);
        game.roll(3);

        game.roll(6);
        game.roll(4);

        game.roll(10);

        game.roll(2);
        game.roll(8);
        game.roll(6);

        Assert.assertEquals(133, game.score());
    }
    @Test
    public void testPerfectGame(){
        //spare turn
        for(int i = 0 ; i < game.getGameLength()+1 ; i++){
            game.roll(MAX_FRAME_SCORE);
        }

        Assert.assertEquals(300, game.score());
    }
}