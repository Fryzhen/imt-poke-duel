package fr.pokeduel.ui;

import fr.pokeduel.game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuUI {
    public static void menuScene(Game game) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(game.getClass().getResource("/menu.css").toExternalForm());

        root.setRight(getPanelNode(stage, game));

        Label placeholder = new Label("BIENVENUE DANS POKEDUEL");
        placeholder.getStyleClass().add("welcome-label");
        root.setCenter(placeholder);

        stage.setScene(scene);
        stage.setTitle("PokeDuel - Menu Principal");
        stage.show();

    }

    public static void playerNammingScene(Game game) {
        Stage stage = new Stage();
        VBox root = new VBox(20); // Augmenté un peu l'espacement
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        // Application du CSS
        Scene scene = new Scene(root, 400, 250); // Un peu plus grand pour respirer
        scene.getStylesheets().add(game.getClass().getResource("/inputName.css").toExternalForm());

        Label nameLabel = new Label("ENTREZ VOTRE NOM");
        nameLabel.getStyleClass().add("label-title");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Ex: Red, Blue...");
        nameInput.setMaxWidth(250);

        Button startButton = new Button("COMMENCER");
        startButton.getStyleClass().add("start-button");
        startButton.setDefaultButton(true);

        startButton.setOnAction(e -> {
            String name = nameInput.getText().trim();
            if (!name.isEmpty()) {
                game.player1.nom = name;
                stage.close();
            } else {
                // Petit effet visuel si vide (optionnel)
                nameInput.setStyle("-fx-border-color: #ff7675;");
            }
        });

        root.getChildren().addAll(nameLabel, nameInput, startButton);

        stage.setTitle("PokeDuel - Inscription");
        stage.setScene(scene);
        stage.setResizable(false); // Plus propre pour une petite fenêtre
        stage.show();
    }

    private static Node getPanelNode(Stage stage, Game game) {
        VBox menuSidePanel = new VBox(15);
        menuSidePanel.getStyleClass().add("side-panel");
        menuSidePanel.setPadding(new Insets(20));
        menuSidePanel.setAlignment(Pos.CENTER);
        menuSidePanel.setPrefWidth(250);

        Button btnBattle = new Button("Lancer un combat");
        Button btnTeam = new Button("Choisir mon équipe");
        Button btnName = new Button("Changer mon nom");
        Button btnQuit = new Button("Quitter");

        for (Button b : new Button[]{btnBattle, btnTeam, btnName, btnQuit}) {
            b.setMaxWidth(Double.MAX_VALUE);
            b.getStyleClass().add("menu-button");
        }

        btnQuit.setOnAction(e -> stage.close());
        btnName.setOnAction(e -> playerNammingScene(game));

        menuSidePanel.getChildren().addAll(btnBattle, btnTeam, btnName, btnQuit);
        return menuSidePanel;
    }
}
