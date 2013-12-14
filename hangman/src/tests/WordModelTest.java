package tests;

import game.model.WordModel;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rkh on 2013-12-14.
 */
public class WordModelTest {

    WordModel sut;

    @Before
    public void setUp() throws Exception {
        sut = new WordModel();
    }

    @Test
    public void testReadFile5Inputs() throws Exception {
        ArrayList<String> actual = sut.readFile("src/tests/5.txt");
        int expected = 5;

        Assert.assertEquals(expected, actual.size());
    }

    @Test
    public void testReadFile0Inputs() throws Exception {
        ArrayList<String> actual = sut.readFile("src/tests/0.txt");
        int expected = 0;

        Assert.assertEquals(expected, actual.size());
    }

    @Test (expected= FileNotFoundException.class)
    public void testReadFileFileNotFound() throws IOException {
        sut.readFile("src/tests/not_a_file.txt");
    }

    @Test (expected= IllegalArgumentException.class)
    public void testCreateSecretWordFromListEmptyList() throws IllegalArgumentException {
        sut.createSecretWordFromList(new ArrayList<String>());
    }

    @Test
    public void testDoWordContainCharRightdGuess() {
        sut.createSecretWordFromList(new ArrayList<String>() {{ add("Test"); }});
        boolean actual = sut.doWordContainChar('e');

        Assert.assertTrue(actual);
    }

    @Test
    public void testDoWordContainCharWrongGuess() {
        sut.createSecretWordFromList(new ArrayList<String>() {{ add("Test"); }});
        boolean actual = sut.doWordContainChar('a');

        Assert.assertFalse(actual);
    }

    @Test
    public void testLowerCaseWithLowerCaseChar() {
        Character guess = 'a';
        Character expected = 'a';
        Character actual = sut.toLowerCase(guess);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLowerCaseWithUpperCaseChar() {
        Character guess = 'A';
        Character expected = 'a';
        Character actual = sut.toLowerCase(guess);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testLowerCaseWithNumber() {
        Character guess = 'A';
        Character expected = 'a';
        Character actual = sut.toLowerCase(guess);

        Assert.assertEquals(expected, actual);
    }

}
