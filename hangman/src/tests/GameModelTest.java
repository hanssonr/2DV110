package tests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import game.model.GameModel;

/**
 * Created by rkh on 2013-12-13.
 */
public class GameModelTest {

    private String filename = "src/tests/test.txt";
    private GameModel sut;

    @Before
    public void setUp() throws Exception {
        sut = new GameModel(filename);
    }

    @Test
    public void testGuessCharacterEmptyList() {
        char[] guess = {'a'};
        boolean actual = sut.guess(guess);

        Assert.assertFalse(actual);
    }

    @Test
    public void testGuessCharacterAfterTwoEqualGuesses() {
        sut.guess(new char[] {'a'});
        sut.guess(new char[] {'a'});

        int expected = 1;
        int actual = sut.getNumberOfGuesses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testValidGuessValidInput() {
        char[] guess = {'d'};
        boolean actual = sut.validGuess(guess);

        Assert.assertTrue(actual);
    }

    @Test
    public void testValidGuessInvalidInput() {
        char[] guess = {63}; // 63 == '?'
        boolean actual = sut.validGuess(guess);

        Assert.assertFalse(actual);
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
        sut.guess(new char[] {'a'});
        sut.guess(new char[] {'b'});

        int expected = 2;
        int actual = sut.getNumberOfGuesses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGuessWordRightWord() {
        boolean actual = sut.guess("Banana".toCharArray());
        Assert.assertTrue(actual);
    }

}
