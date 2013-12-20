package game.model;

import java.io.IOException;

/**
 * Created by rkh on 2013-12-13.
 */
public class GameModel {

    private WordModel mWordModel;
    private int mMaxNumberOfGuesses;
    private int mGuesses = 0;

    public GameModel(WordModel wordmodel, int maxNumberOfGuesses) {
        mWordModel = wordmodel;
        mMaxNumberOfGuesses = maxNumberOfGuesses;
    }

    public boolean guess(char[] guess) {
        if (guess.length > 1) {
            mGuesses++;
            return mWordModel.guessWord(guess);
        } else {
            boolean ret = false;
            if (!mWordModel.isGuessedCharacter(guess[0])) {
                mGuesses++;
                ret = mWordModel.doWordContainChar(guess[0]);
            }
            return ret;
        }
    }

    public boolean validGuess(char[] guess) {
        if (guess.length == 0) return false;

        for(char ch : guess) {
            char lower = mWordModel.toLowerCase(ch);
            if (lower == 32) continue; //space
            if (lower < 'a' || lower > 'z') return false;
        }
        return true;
    }

    public int getNumberOfGuesses() {
        return mGuesses;
    }

    public int getMaxNumberOfGuesses() {
        return mMaxNumberOfGuesses;
    }

    public String getSecretWordWithGuessedChars() {
        return mWordModel.getSecretWordWithGuessedChars();
    }

    public String getGuessedChars() {
        return mWordModel.getGuessedChars();
    }

    public String getSecretWord() {
        return mWordModel.getSecretWord();
    }
}