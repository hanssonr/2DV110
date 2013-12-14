package game;

import game.controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/game.fxml"));
        Parent root = (Parent) loader.load();
        stage.setTitle("Guess my word");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();

        GameController master = (GameController)loader.getController();
        master.setStage(stage);
    }
}
