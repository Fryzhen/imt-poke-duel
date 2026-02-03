package fr.pokeduel;

import fr.pokeduel.game.Game;
import fr.pokeduel.ui.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ScreenManager.displayMenu(new Game());
    }

    static void main(String[] args) {
        launch();
    }
}