package fr.pokeduel.ui;
import fr.pokeduel.game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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

    public static void menuScene(Game game) {
        Stage stage = new Stage();
    }

    public static void playerInitScene() {
        Stage stage = new Stage();
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label nameLabel = new Label("Enter your name:");
        nameLabel.setStyle("-fx-font-weight: bold;");

        TextField nameInput = new TextField();
        nameInput.setPromptText("e.g. Player 1");
        nameInput.setMaxWidth(200);

        Button startButton = new Button("Start Game");
        startButton.setDefaultButton(true);

        // L'action magique se passe ici
        startButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (!name.isEmpty()) {
                System.out.println("Starting game for: " + name);
                stage.close();
                battleScene(new Game(name));
            }
        });

        root.getChildren().addAll(nameLabel, nameInput, startButton);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("PokeDuel - Player Initialization");
        stage.setScene(scene);
        stage.show();
    }
}
