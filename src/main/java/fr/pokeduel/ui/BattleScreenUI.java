package fr.pokeduel.ui;

import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BattleScreenUI {
    public static Node getBattleAreaNode(Game game, double width, double height) {
        Pane battleArea = new Pane();
        battleArea.setPrefHeight(height * 0.7);

        BackgroundImage bgImage = new BackgroundImage(new Image(BattleScreenUI.class.getClassLoader().getResource("img/battle_scene.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
        battleArea.setBackground(new Background(bgImage));


        Pokemon playerPoke = game.player1.pokemons.get(game.player1.pokemonActifIndex);
        ImageView playerSprite = new ImageView(new Image(playerPoke.backSprite != null ? playerPoke.backSprite : playerPoke.frontSprite));
        playerSprite.setFitWidth(200);
        playerSprite.setPreserveRatio(true);
        playerSprite.setScaleX(1.2);
        playerSprite.setScaleY(1.2);
        playerSprite.setX(width * 0.38);
        playerSprite.setY(height * 0.4);

        Pokemon enemyPoke = game.player2.pokemons.get(game.player2.pokemonActifIndex);
        ImageView enemySprite = new ImageView(new Image(game.player2.pokemons.get(game.player2.pokemonActifIndex).frontSprite));
        enemySprite.setFitWidth(180);
        enemySprite.setPreserveRatio(true);
        enemySprite.setScaleX(1.2);
        enemySprite.setScaleY(1.2);
        enemySprite.setX(width * 0.60);
        enemySprite.setY(height * 0.22);

        // Barre de l'adversaire (en haut à gauche du sprite ennemi)
        Node enemyStatus = createHealthBlock(enemyPoke, false, width, height);
        enemyStatus.setLayoutX(width * 0.85);
        enemyStatus.setLayoutY(height * 0);

        // Barre du joueur (souvent placée en bas à droite du sprite pour le style "rétro")
        Node playerStatus = createHealthBlock(playerPoke, true, width, height);
        playerStatus.setLayoutX(width * 0);
        playerStatus.setLayoutY(height * 0.61);

        // On ajoute tout au Pane
        battleArea.getChildren().addAll(playerSprite, enemySprite, enemyStatus, playerStatus);

        return battleArea;
    }

    private static VBox createHealthBlock(Pokemon pokemon, boolean isPlayer, double width, double height) {
        VBox container = new VBox(2);
        // Style du bloc blanc (bordures arrondies, fond blanc cassé, bordure grise)
        container.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.9);" +
                        "-fx-background-radius: 15 0 15 0;" + // Style un peu asymétrique comme GBA
                        "-fx-border-color: #505050;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 15 0 15 0;" +
                        "-fx-padding: 8;"
        );
        container.setPrefWidth(width * 0.15);

        // Nom du Pokémon
        Label nameLabel = new Label(pokemon.nom.toUpperCase());
        nameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #303030;");

        // Barre de vie
        ProgressBar hpBar = new ProgressBar();
        double progress = (double) pokemon.pvRestant / pokemon.stats.pv;
        hpBar.setProgress(progress);
        hpBar.setPrefWidth(width * 0.14);
        hpBar.setPrefHeight(15);

        // Couleur dynamique de la barre (Vert, Orange, Rouge)
        String color = progress > 0.5 ? "#7cfc00" : (progress > 0.2 ? "#ffaa00" : "#ff0000");
        hpBar.setStyle("-fx-accent: " + color + "; -fx-control-inner-background: #e0e0e0;");

        // Texte des PV (uniquement pour le joueur, comme dans les jeux)
        HBox hpTextContainer = new HBox();
        if (isPlayer) {
            Label hpLabel = new Label(pokemon.pvRestant + " / " + pokemon.stats.pv);
            hpLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #303030;");
            hpTextContainer.getChildren().add(hpLabel);
            hpTextContainer.setStyle("-fx-alignment: CENTER-RIGHT;");
        }

        container.getChildren().addAll(nameLabel, hpBar, hpTextContainer);
        return container;
    }
}