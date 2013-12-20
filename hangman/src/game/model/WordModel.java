package game.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by rkh on 2013-12-14.
 */
public class WordModel {

    private ArrayList<Character> mGuessedCharacters = new ArrayList<Character>();
    private String mSecretWord;
    private Random rand = new Random();

    public void createSecretWordFromList(ArrayList<String> words) throws IllegalArgumentException {
        if (words.size() == 0) throw new IllegalArgumentException("List size must be greater than zero");
        mSecretWord = words.get(rand.nextInt(words.size()));
    }

    public boolean doWordContainChar(char ch) {
        for(char wordchar : mSecretWord.toCharArray()) {
            if (toLowerCase(wordchar) == toLowerCase(ch)) {
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

    public boolean isGuessedCharacter(char guess) {
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

    public String getSecretWordWithGuessedChars() {
        char[] ret = new char[mSecretWord.length()];
        char[] secret = mSecretWord.toCharArray();
        Arrays.fill(ret, '*');

        for(char guess : mGuessedCharacters) {
            for (int i = 0; i < mSecretWord.length(); i++) {
                if (toLowerCase(secret[i]) == guess) {
                    ret[i] = secret[i];
                }
            }
        }

        return new String(ret);
    }

    public char toLowerCase(char old) {
        return Character.toLowerCase(old);
    }

    public ArrayList<String> readFile(String filename) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(filename));

        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        br.close();

        return list;
    }

    public String getGuessedChars() {
        return String.valueOf(mGuessedCharacters);
    }

    public String getSecretWord() {
        return mSecretWord;
    }
}
