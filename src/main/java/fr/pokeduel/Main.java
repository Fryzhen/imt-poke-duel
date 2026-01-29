package fr.pokeduel;

import fr.pokeduel.ui.BattleUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        BattleUI.playerNammingScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}