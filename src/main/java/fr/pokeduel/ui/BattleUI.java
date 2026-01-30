package fr.pokeduel.ui;

import fr.pokeduel.game.Game;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BattleUI {

    public static void battleScene(Game game) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        Label battleLabel = new Label("Welcome to the Battle Scene " + game.curentPlayer.nom + " !");
        battleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        root.setCenter(battleLabel);

        Scene scene = new Scene(root, 1920, 1080);
        stage.setTitle("PokeDuel - Battle");
        stage.setScene(scene);
        stage.show();
    }
}
