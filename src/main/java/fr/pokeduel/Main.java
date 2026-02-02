package fr.pokeduel;

import fr.pokeduel.game.Game;
import fr.pokeduel.ui.MenuUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MenuUI.menuScene(new Game());
    }

    static void main(String[] args) {
        launch();
    }
}