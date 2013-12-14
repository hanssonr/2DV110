package tests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import game.GameModel;

/**
 * Created by rkh on 2013-12-13.
 */
public class GameModelTest {

    private String filename = "assets/words.txt";
    private GameModel sut;

    @Before
    public void setUp() throws Exception {
        sut = new GameModel(filename);
    }

    @Test
    public void testGuessCharacterEmptyList() {
        Character guess = 'a';
        boolean expected = false;
        boolean actual = sut.guessCharacter(guess);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGuessCharacterAfterTwoEqualGuesses() {
        sut.guessCharacter('a');
        sut.guessCharacter('a');

        int expected = 1;
        int actual = sut.getNumberOfGuesses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testValidGuessValidInput() {
        Character guess = 'd';
        boolean expected = true;
        boolean actual = sut.validGuess(guess);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testValidGuessInvalidInput() {
        Character guess = 63; // 63 == '?'
        boolean expected = false;
        boolean actual = sut.validGuess(guess);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetNumberOfGuesses() {
        int expected = 0;
        int actual = sut.getNumberOfGuesses();

        Assert.assertEquals(expected, actual);
    }

    @Test
         public void testGetNumberOfGuessesWith2Guesses() {
        //guess twice
        sut.guessCharacter('a');
        sut.guessCharacter('b');

        int expected = 2;
        int actual = sut.getNumberOfGuesses();

        Assert.assertEquals(expected, actual);
    }

}
