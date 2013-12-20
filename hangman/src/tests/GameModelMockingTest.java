package tests;

import game.model.GameModel;
import game.model.WordModel;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by rkh on 2013-12-20.
 */
public class GameModelMockingTest {

    private String mFilename = "src/tests/test.txt";
    private int mMaxNumberOfGuesses = 10;
    private GameModel sut;
    private WordModel mockedWordModel;

    @Before
    public void setUp() throws Exception {
        mockedWordModel = mock(WordModel.class);
        sut = new GameModel(mockedWordModel, mMaxNumberOfGuesses);
    }

    @Test
    public void testNotBeforeGuessedChar() {
        char[] input = new char[] {'a'};

        when(mockedWordModel.isGuessedCharacter(input[0])).thenReturn(false);
        sut.guess(input);
        verify(mockedWordModel).doWordContainChar(input[0]);
    }

    @Test
    public void testAlreadyGuessedChar() {
        char[] input = new char[] {'a'};

        when(mockedWordModel.isGuessedCharacter(input[0])).thenReturn(true);
        sut.guess(input);
        verify(mockedWordModel, never()).doWordContainChar(input[0]);
    }

    @Test
    public void testValidGuess() {
        when(mockedWordModel.toLowerCase('A')).thenReturn('a');
        sut.validGuess(new char[] {'A'});
        verify(mockedWordModel).toLowerCase('A');
    }
}
