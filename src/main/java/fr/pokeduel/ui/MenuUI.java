package fr.pokeduel.ui;

import fr.pokeduel.data.entity.Pokemon;
import fr.pokeduel.game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuUI {
    public static void menuScene(Game game) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(game.getClass().getResource("/css/menu.css").toExternalForm());

        root.setCenter(getTeamPanelNode(stage, game));
        root.setRight(getPanelNode(stage, game));

        Label placeholder = new Label("BIENVENUE DANS POKEDUEL " + game.player1.nom + " !");
        placeholder.getStyleClass().add("welcome-label");
//        root.setCenter(placeholder);

        stage.setScene(scene);
        stage.setTitle("PokeDuel - Menu Principal");
        stage.show();

    }

    public static void playerNammingScene(Game game) {
        Stage stage = new Stage();
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 400, 250); // Un peu plus grand pour respirer
        scene.getStylesheets().add(game.getClass().getResource("/css/inputName.css").toExternalForm());

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
                menuScene(game);
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

        btnBattle.setDisable(game.player1.pokemons.isEmpty());
        btnQuit.setOnAction(e -> stage.close());
        btnName.setOnAction(e -> {
            playerNammingScene(game);
            stage.close();
        });

        menuSidePanel.getChildren().addAll(btnBattle, btnTeam, btnName, btnQuit);
        return menuSidePanel;
    }

    private static Node getTeamPanelNode(Stage stage, Game game) {
        GridPane teamPanel = new GridPane();
        teamPanel.getStyleClass().add("team-panel");
        teamPanel.setPadding(new Insets(20));
        teamPanel.setHgap(30);
        teamPanel.setVgap(30);
        teamPanel.setAlignment(Pos.CENTER);

        int i = 0;
        for (Pokemon pokemon : game.player1.pokemons) {
            ImageView iv = new ImageView(pokemon.frontSprite);
            iv.getStyleClass().add("team-pokemon-sprite");

            iv.setPreserveRatio(true);
            teamPanel.add(iv, i % 3, i / 3);
            i++;
        }
        return teamPanel;
    }
}
