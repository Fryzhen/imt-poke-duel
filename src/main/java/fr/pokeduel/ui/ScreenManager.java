package fr.pokeduel.ui;

import fr.pokeduel.actions.Action;
import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import fr.pokeduel.ui.battle.BattleActionResolveUI;
import fr.pokeduel.ui.battle.BattleMenuUI;
import fr.pokeduel.ui.battle.BattleScreenUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ScreenManager {
    //----- MENU DISPLAY -----//

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

    //----- BATTLE DISPLAY -----//

    public static void initDisplayBattle(Game game) {
        game.stage = new Stage();
        game.resetGame();
        Scene scene = new Scene(new VBox(), game.width, game.height);
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
        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game), BattleMenuUI.getMenuAreaNode(game));
        game.stage.getScene().setRoot(root);
    }

    public static void displayBattleResolve(Game game, Action action) {
        VBox root = new VBox();
        root.getStyleClass().add("battle-root");

        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game), BattleActionResolveUI.getMessageBoxNode(game, action.description));

        game.stage.getScene().setRoot(root);
    }

    public static void displayMessage(Game game, String message) {
        VBox root = new VBox();
        root.getStyleClass().add("battle-root");

        root.getChildren().addAll(BattleScreenUI.getBattleAreaNode(game), BattleActionResolveUI.getMessageBoxNode(game, message));

        game.stage.getScene().setRoot(root);
    }

    public static void displayForceSwitchMenu(Game game, Consumer<Integer> onPokemonSelected) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menu-root");

        Label titleLabel = new Label("Votre Pokémon est KO !\nChoisissez qui envoyer au combat :");
        titleLabel.getStyleClass().add("menu-title");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-text-alignment: center;");

        GridPane teamGrid = new GridPane();
        teamGrid.setHgap(15);
        teamGrid.setVgap(15);
        teamGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < game.player.pokemons.size(); i++) {
            Pokemon p = game.player.pokemons.get(i);
            int index = i;

            Button pBtn = createPokemonButton(p);

            if (p.isKO()) {
                pBtn.setDisable(true);
                pBtn.getStyleClass().add("pokemon-btn-ko");
            } else {
                pBtn.setOnAction(e -> {
                    onPokemonSelected.accept(index);
                });
                pBtn.getStyleClass().add("pokemon-btn-valid");
            }

            teamGrid.add(pBtn, i % 2, i / 2);
        }

        root.getChildren().addAll(titleLabel, teamGrid);

        game.stage.getScene().setRoot(root);
    }

    private static Button createPokemonButton(Pokemon p) {
        Button btn = new Button();
        btn.setPrefSize(200, 80);

        VBox content = new VBox(5);
        content.setAlignment(Pos.CENTER_LEFT);

        Label nameLabel = new Label(p.nom);
        nameLabel.setStyle("-fx-font-weight: bold;");

        ProgressBar hpBar = new ProgressBar((double) p.pvRestant / p.stats.pv);
        hpBar.setPrefWidth(180);

        String barColor = "green";
        if (p.pvRestant == 0) barColor = "grey";
        else if ((double) p.pvRestant / p.stats.pv < 0.2) barColor = "red";
        else if ((double) p.pvRestant / p.stats.pv < 0.5) barColor = "orange";

        hpBar.setStyle("-fx-accent: " + barColor + ";");

        Label hpText = new Label(p.pvRestant + " / " + p.stats.pv + " PV");

        content.getChildren().addAll(nameLabel, hpBar, hpText);
        btn.setGraphic(content);

        return btn;
    }
}
