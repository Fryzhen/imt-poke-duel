package fr.pokeduel;

import fr.pokeduel.game.Game;
import fr.pokeduel.ui.BattleUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        BattleUI.playerInitScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}