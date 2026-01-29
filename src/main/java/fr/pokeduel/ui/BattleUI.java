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

    public static void menuScene( Game game) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();

        // --- PANNEAU DE DROITE (Menu) ---
        VBox menuSidePanel = new VBox(15); // Espacement de 15px entre les boutons
        menuSidePanel.setPadding(new Insets(20));
        menuSidePanel.setAlignment(Pos.CENTER);
        menuSidePanel.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 0 0 0 2;");
        menuSidePanel.setPrefWidth(250); // Largeur fixe pour le menu

        // --- BOUTONS ---
        Button btnBattle = new Button("Lancer un combat");
        Button btnTeam = new Button("Changer mon équipe");
        Button btnName = new Button("Changer mon nom");
        Button btnQuit = new Button("Quitter");

        // Style commun pour les boutons (largeur uniforme)
        String btnStyle = "-fx-font-size: 14px; -fx-cursor: hand;";
        for (Button b : new Button[]{btnBattle, btnTeam, btnName, btnQuit}) {
            b.setMaxWidth(Double.MAX_VALUE); // Les boutons prendront toute la largeur du VBox
            b.setStyle(btnStyle);
        }

        // --- ACTIONS DES BOUTONS ---

        btnBattle.setOnAction(e -> {
//            stage.setScene(getBattleScene()); // On lance le combat
//            stage.centerOnScreen();
        });

        btnName.setOnAction(e -> {
//            stage.setScene(getPlayerInitScene(stage)); // On retourne à l'écran de saisie
        });

        btnQuit.setOnAction(e -> stage.close()); // Ferme l'application

        // On peut imaginer que btnTeam ouvrira une autre scène plus tard

        // Ajout des boutons au panneau
        menuSidePanel.getChildren().addAll(btnBattle, btnTeam, btnName, btnQuit);

        // Placement dans le layout principal
        root.setRight(menuSidePanel);

        // --- ESPACE CENTRAL (Placeholder) ---
        // Ici on pourra mettre plus tard un logo ou les stats du joueur
        Label placeholder = new Label("Bienvenue dans PokeDuel !");
        placeholder.setStyle("-fx-font-size: 24px; -fx-text-fill: #888888;");
        root.setCenter(placeholder);

        stage.setScene(new Scene(root, 1280, 720));
        stage.show();

    }

    public static void playerNammingScene() {
        Stage stage = new Stage();
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label nameLabel = new Label("Entrez votre nom :");
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
                menuScene(new Game(name));
            }
        });

        root.getChildren().addAll(nameLabel, nameInput, startButton);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("PokeDuel - Player Initialization");
        stage.setScene(scene);
        stage.show();
    }
}
