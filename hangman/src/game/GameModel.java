package game;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rkh on 2013-12-13.
 */
public class GameModel {

    private ArrayList<Character> mGuessedCharacters;
    private WordModel mWordModel;

    public GameModel(String filename) throws IOException {
        mWordModel = new WordModel();
        mGuessedCharacters = new ArrayList<Character>(10);
        mWordModel.createSecretWordFromList(mWordModel.readFile(filename));
    }

    public boolean guessCharacter(char guess) {
        boolean found = false;
        char guessToLower = mWordModel.toLowerCase(guess);

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

    public boolean validGuess(char guess) {
        char guessToLower = mWordModel.toLowerCase(guess);
        return guessToLower >= 'a' && guessToLower <= 'z';
    }

    public int getNumberOfGuesses() {
        return mGuessedCharacters.size();
    }

}
