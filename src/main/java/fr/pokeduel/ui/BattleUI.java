package fr.pokeduel.ui;

import fr.pokeduel.game.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class BattleUI {
    public static void battleScene(Game game) {
        Stage stage = new Stage();
        game.resetGame();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double width = screenBounds.getWidth();
        double height = screenBounds.getHeight();

        VBox root = new VBox();
        root.getStyleClass().add("battle-root");
        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game, width, height), BattleMenuUI.getMenuAreaNode(game, width, height));

        Scene scene = new Scene(root, width, height);

        String css = BattleMenuUI.class.getResource("/css/battleScene.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("PokeDuel - Battle");
        stage.setScene(scene);
        stage.show();
    }
}