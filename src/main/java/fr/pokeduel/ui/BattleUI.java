package fr.pokeduel.ui;

import fr.pokeduel.game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class BattleUI {

    public static void battleScene(Game game) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        Label battleLabel = new Label("Welcome to the Battle Scene " + game.curentPlayer.nom + " !");
        battleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        root.setCenter(battleLabel);

        // get height and width of the screen of the computer
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        stage.setTitle("PokeDuel - Battle");
        stage.setScene(scene);
        stage.show();
    }
}
