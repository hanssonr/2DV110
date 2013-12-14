package game.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rkh on 2013-12-14.
 */
public class WordModel {

    private ArrayList<Character> mGuessedCharacters;
    private String mSecretWord;
    private Random rand;

    public WordModel() {
        mGuessedCharacters = new ArrayList<Character>(10);
        rand = new Random();
    }

    public void createSecretWordFromList(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() == 0) throw new IllegalArgumentException("List size must be greater than zero");
        mSecretWord = words.get(rand.nextInt(words.size()));
    }

    public boolean doWordContainChar(char ch) {
        for(Character wordch : mSecretWord.toCharArray()) {
            if (toLowerCase(wordch) == ch) {
                return true;
            }
        }
        return false;
    }

    public boolean guessWord(char[] guess) {
        if (guess.length != mSecretWord.length()) return false;

        for(int i=0; i < mSecretWord.length(); i++) {
            if (toLowerCase(mSecretWord.toCharArray()[i]) != toLowerCase(guess[i])) return false;
        }

        return true;
    }

    public boolean guessCharacter(char guess) {
        boolean found = false;
        char guessToLower = toLowerCase(guess);

        //check for old guesses
        for(char old : mGuessedCharacters) {
            if (old == guessToLower) {
                found = true;
                break;
            }
        }

        //add guess
        if (!found) {
            mGuessedCharacters.add(guessToLower);
        }

        return found;
    }

    public char toLowerCase(char old) {
        return Character.toLowerCase(old);
    }

    public ArrayList<String> readFile(String filename) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader br;
        String line;

        br = new BufferedReader(new FileReader(filename));

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        br.close();

        return list;
    }

    public int getGuessedCharacterSize() {
        return mGuessedCharacters.size();
    }
}
