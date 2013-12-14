package game.controller;

import game.model.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by rkh on 2013-12-14.
 */
public class GameController {

    GameModel mModel;
    Stage mStage;

    public GameController() {
        try {
            mModel = new GameModel("assets/words.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        mStage = stage;
    }

    @FXML
    protected void changeLabelText(ActionEvent event) {
        TextField tf = (TextField)mStage.getScene().lookup("#usernameField");

        mModel.guess(new char['a']);
        tf.setText(String.valueOf(mModel.getNumberOfGuesses()));
    }
}
