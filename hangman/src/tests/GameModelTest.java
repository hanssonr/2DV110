package tests;

import game.model.WordModel;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import game.model.GameModel;

/**
 * Created by rkh on 2013-12-13.
 */
public class GameModelTest {

    private String mFilename = "src/tests/test.txt";
    private int mMaxNumberOfGuesses = 10;
    private GameModel sut;
    private WordModel mWordModel;

    @Before
    public void setUp() throws Exception {
        mWordModel = new WordModel();
        mWordModel.createSecretWordFromList(mWordModel.readFile(mFilename));
        sut = new GameModel(mWordModel, mMaxNumberOfGuesses);
    }

    @Test
    public void testGuessCharacterAfterTwoEqualGuesses() {
        char[] input = new char[] {'a'};
        sut.guess(input);
        sut.guess(input);

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
    public void testValidGuessZeroLengthArray() {
        char[] guess = {};
        boolean actual = sut.validGuess(guess);

        Assert.assertFalse(actual);
    }

    @Test
    public void testValidGuessInvalidLowerBoundry() {
        char[] guess = {96};
        boolean actual = sut.validGuess(guess);

        Assert.assertFalse(actual);
    }

    @Test
    public void testValidGuessInvalidUpperBoundry() {
        char[] guess = {123};
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

    @Test
    public void testGetMaxNumberOfGuesses() {
        int expected = mMaxNumberOfGuesses;
        int actual = sut.getMaxNumberOfGuesses();
        Assert.assertEquals(expected, actual);
    }

    // Redundant?
    @Test
    public void testGetSecretWordWithGuessedChars() {
        sut.guess(new char[] {'b'});
        sut.guess(new char[] {'a'});

        String expected = "Ba*a*a";
        String actual = sut.getSecretWordWithGuessedChars();

        Assert.assertEquals(expected, actual);
    }

    @Test
         public void testGetGuessedCharacters() {
        sut.guess(new char[] {'a'});
        sut.guess(new char[] {'b'});

        String expected = "[a, b]";
        String actual = sut.getGuessedChars();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSecretWord() {
        String expected = "Banana";
        String actual = sut.getSecretWord();

        Assert.assertEquals(expected, actual);
    }
}
