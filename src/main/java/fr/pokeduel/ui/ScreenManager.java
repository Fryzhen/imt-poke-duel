package fr.pokeduel.ui;

import fr.pokeduel.actions.Action;
import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import fr.pokeduel.ui.battle.BattleActionResolveUI;
import fr.pokeduel.ui.battle.BattleMenuUI;
import fr.pokeduel.ui.battle.BattleScreenUI;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.function.Consumer;

public class ScreenManager {
    private static final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    static double width = screenBounds.getWidth();
    static double height = screenBounds.getHeight();

    public static void displayMenu(Game game) {
        game.stage = new Stage();
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(game.getClass().getResource("/css/menu.css").toExternalForm());

        root.setCenter(MenuUI.getTeamPanelNode(game));
        root.setRight(MenuUI.getPanelNode(game));

        game.stage.setScene(scene);
        game.stage.setTitle("PokeDuel - Menu Principal");
        game.stage.show();
    }

    public static void initDisplayBattle(Game game) {
        game.stage = new Stage();
        game.resetGame();
        Scene scene = new Scene(new VBox(), width, height);
        String css = BattleMenuUI.class.getResource("/css/battle.css").toExternalForm();
        scene.getStylesheets().add(css);
        game.stage.setTitle("PokeDuel - Battle");
        game.stage.setScene(scene);
        game.stage.show();
        displayBattleChooseAction(game);
    }

    public static void displayBattleChooseAction(Game game) {
        VBox root = new VBox();
        root.getStyleClass().add("battle-root");
        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game, width, height), BattleMenuUI.getMenuAreaNode(game, width, height));
        game.stage.getScene().setRoot(root);
    }

    public static void displayBattleResolve(Game game, Action action, Runnable onFinished) {
        VBox root = new VBox();
        root.getStyleClass().add("battle-root");

        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game, width, height), BattleActionResolveUI.getActionResolveNode(game, width, height, action.description));

        game.stage.getScene().setRoot(root);

        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 secondes

        pause.setOnFinished(event -> {
            if (onFinished != null) {
                onFinished.run();
            }
        });

        pause.play();
    }

    public static void displayMessage(Game game, String message, Runnable onFinished) {
        VBox root = new VBox();
        root.getStyleClass().add("battle-root");

        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game, width, height), BattleActionResolveUI.getMessageBoxNode(width, height, message));

        game.stage.getScene().setRoot(root);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            if (onFinished != null) onFinished.run();
        });
        pause.play();
    }

    public static void displayNameChange(Game game) {
        Stage stage = new Stage();
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 400, 250);
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
                game.player.nom = name;
                stage.close();
                displayMenu(game);
            } else {
                nameInput.setStyle("-fx-border-color: #ff7675;");
            }
        });

        root.getChildren().addAll(nameLabel, nameInput, startButton);

        stage.setTitle("PokeDuel - Inscription");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void displayForceSwitchMenu(Game game, Consumer<Integer> onPokemonSelected) {
        VBox root = new VBox(20); // Espacement vertical de 20px
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-root"); // Pour ton CSS (fond, etc.)

        // 1. Titre informatif
        Label titleLabel = new Label("Votre Pokémon est KO !\nChoisissez qui envoyer au combat :");
        titleLabel.getStyleClass().add("menu-title");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-text-alignment: center;");

        // 2. Grille des Pokémons (2 colonnes, 3 lignes par exemple)
        GridPane teamGrid = new GridPane();
        teamGrid.setHgap(15);
        teamGrid.setVgap(15);
        teamGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < game.player.pokemons.size(); i++) {
            Pokemon p = game.player.pokemons.get(i);
            int index = i; // Variable finale pour la lambda

            // Création du bouton pour ce Pokémon
            Button pBtn = createPokemonButton(p);

            // LOGIQUE DE VALIDATION
            if (p.isKO()) {
                pBtn.setDisable(true); // On ne peut pas choisir un mort
                pBtn.getStyleClass().add("pokemon-btn-ko"); // Style gris/rouge
            } else {
                pBtn.setOnAction(e -> {
                    // C'est ici qu'on déclenche le callback vers BattleSystem
                    onPokemonSelected.accept(index);
                });
                pBtn.getStyleClass().add("pokemon-btn-valid");
            }

            // Ajout à la grille (colonne, ligne)
            teamGrid.add(pBtn, i % 2, i / 2);
        }

        root.getChildren().addAll(titleLabel, teamGrid);

        // On change la scène. Note qu'il n'y a PAS de bouton "Retour".
        game.stage.getScene().setRoot(root);
    }

    // Méthode utilitaire pour créer une jolie "carte" de Pokémon dans un bouton
    private static Button createPokemonButton(Pokemon p) {
        Button btn = new Button();
        btn.setPrefSize(200, 80);

        VBox content = new VBox(5);
        content.setAlignment(Pos.CENTER_LEFT);

        // Nom et Niveau
        Label nameLabel = new Label(p.nom);
        nameLabel.setStyle("-fx-font-weight: bold;");

        // Barre de vie (approximative avec une ProgressBar)
        ProgressBar hpBar = new ProgressBar((double) p.pvRestant / p.stats.pv);
        hpBar.setPrefWidth(180);

        // Couleur de la barre selon les PV
        String barColor = "green";
        if (p.pvRestant == 0) barColor = "grey";
        else if ((double) p.pvRestant / p.stats.pv < 0.2) barColor = "red";
        else if ((double) p.pvRestant / p.stats.pv < 0.5) barColor = "orange";

        hpBar.setStyle("-fx-accent: " + barColor + ";");

        // Texte PV
        Label hpText = new Label(p.pvRestant + " / " + p.stats.pv + " PV");

        content.getChildren().addAll(nameLabel, hpBar, hpText);
        btn.setGraphic(content);

        return btn;
    }
}
