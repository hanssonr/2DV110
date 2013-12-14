package game.model;

import game.model.WordModel;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rkh on 2013-12-13.
 */
public class GameModel {

    private WordModel mWordModel;

    public GameModel(String filename) throws IOException {
        mWordModel = new WordModel();
        mWordModel.createSecretWordFromList(mWordModel.readFile(filename));
    }

    public boolean guess(char[] guess) {
        if (guess.length > 1) {
            return mWordModel.guessWord(guess);
        } else {
            return mWordModel.guessCharacter(guess[0]);
        }
    }

    public boolean validGuess(char[] guess) {
        for(char ch : guess) {
            char lower = mWordModel.toLowerCase(ch);
            if (lower < 'a' || lower > 'z') return false;
        }
        return true;
    }

    public int getNumberOfGuesses() {
        return mWordModel.getGuessedCharacterSize();
    }

}
