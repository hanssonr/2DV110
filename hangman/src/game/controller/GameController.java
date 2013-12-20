package game.controller;

import game.model.GameModel;
import game.model.WordModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Created by rkh on 2013-12-14.
 */
public class GameController {

    private WordModel mWordModel;
    private GameModel mModel;
    private Stage mStage;

    private String mWordfile = "assets/words.txt";
    private int mNumberOfGuesses = 10;

    private TextField mGuessField;
    private Label mWordLabel, mGuessesLabel, mOutputLabel, mGuessedCharLabel;
    private Button mGuessButton;
    private boolean mGameWon = false;

    public GameController() {
        init();
    }

    private void init() {
        try {
            mWordModel = new WordModel();
            mWordModel.createSecretWordFromList(mWordModel.readFile(mWordfile));
            mModel = new GameModel(mWordModel, mNumberOfGuesses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bindEventHandlers() {
        mGuessButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                doGuess(event);
            }
        });

        mGuessField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                    if(keyEvent.getCode() == KeyCode.ENTER) {
                        mGuessButton.fire();
                    }
                }
            }
        });
    }

    private void restart() {
        init();

        mGameWon = false;
        mGuessButton.setText("Guess");
        mGuessField.setText("");
        mOutputLabel.setText("");
        mGuessedCharLabel.setText("");

        mGuessesLabel.setText(String.valueOf(mModel.getMaxNumberOfGuesses() - mModel.getNumberOfGuesses()));
        mWordLabel.setText(mModel.getSecretWordWithGuessedChars());
        bindEventHandlers();
    }

    public void setupStage(Stage stage) {
        mStage = stage;

        mGuessField = (TextField)stage.getScene().lookup("#guessField");
        mWordLabel = (Label)stage.getScene().lookup("#wordLabel");
        mGuessesLabel = (Label)stage.getScene().lookup("#guessesLabel");
        mOutputLabel = (Label)stage.getScene().lookup("#outputLabel");
        mGuessedCharLabel = (Label)stage.getScene().lookup("#guessedCharacters");
        mGuessButton = (Button)stage.getScene().lookup("#guessButton");

        mWordLabel.setText(mModel.getSecretWordWithGuessedChars());
        mGuessesLabel.setText(String.valueOf(mModel.getMaxNumberOfGuesses()-mModel.getNumberOfGuesses()));
        bindEventHandlers();

    }

    @FXML
    protected void doGuess(ActionEvent event) {
        mOutputLabel.setText("");

        char[] guess = mGuessField.getText().trim().toCharArray();

        if (mModel.validGuess(guess)) {
            if (mModel.guess(guess)) {
                if(guess.length > 1) {
                    mGameWon = true;
                    mWordLabel.setText(mModel.getSecretWord());
                } else {
                    mWordLabel.setText(mModel.getSecretWordWithGuessedChars());
                }
            } else {
                if(guess.length > 1) {
                    mOutputLabel.setText(String.valueOf(guess) + " was not the secret word");
                } else {
                    mOutputLabel.setText(String.valueOf(guess) + " was not in the word!");
                }
            }

            mGuessedCharLabel.setText(mModel.getGuessedChars());
        } else {
            mOutputLabel.setText("Guess must either be a character (a-z) or a word!");
        }

        mGuessField.setText("");
        isGameOver();
    }

    private void isGameOver() {
        mGuessesLabel.setText(String.valueOf(mModel.getMaxNumberOfGuesses() - mModel.getNumberOfGuesses()));
        if (mModel.getMaxNumberOfGuesses() - mModel.getNumberOfGuesses() == 0 || mGameWon) {
            mGuessButton.setText("Retry");
            mGuessButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setupStage(mStage);
                    restart();
                }
            });

            if (mGameWon) {
                mOutputLabel.setText("You guessed the correct word!");
            } else {
                mOutputLabel.setText("The secret word was: " + mModel.getSecretWord());
            }

        }
    }
}
