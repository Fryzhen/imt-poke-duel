package fr.pokeduel.ui.battle;

import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BattleScreenUI {
    public static Node getBattleAreaNode(Game game) {
        Pane battleArea = new Pane();
        battleArea.setPrefHeight(game.height * 0.7);

        BackgroundImage bgImage = new BackgroundImage(new Image(BattleScreenUI.class.getClassLoader().getResource("img/battle_scene.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, true, true));
        battleArea.setBackground(new Background(bgImage));


        Pokemon playerPoke = game.player.pokemons.get(game.player.pokemonActifIndex);
        ImageView playerSprite = createPokemonSprite(playerPoke, true);
        playerSprite.setX(game.width * 0.38);
        playerSprite.setY(game.height * 0.4);

        Pokemon enemyPoke = game.bot.getActivePokemon();
        ImageView enemySprite = createPokemonSprite(playerPoke, false);
        enemySprite.setX(game.width * 0.60);
        enemySprite.setY(game.height * 0.22);

        Node enemyStatus = createHealthBlock(enemyPoke, false, game.width, game.height);
        enemyStatus.setLayoutX(game.width * 0.85);
        enemyStatus.setLayoutY(game.height * 0);

        Node playerStatus = createHealthBlock(playerPoke, true, game.width, game.height);
        playerStatus.setLayoutX(game.width * 0);
        playerStatus.setLayoutY(game.height * 0.61);

        battleArea.getChildren().addAll(playerSprite, enemySprite, enemyStatus, playerStatus);

        return battleArea;
    }

    private static VBox createHealthBlock(Pokemon pokemon, boolean isPlayer, double width, double height) {
        VBox container = new VBox(2);
        container.getStyleClass().add("health-block");
        container.setPrefWidth(width * 0.15);

        Label nameLabel = new Label(pokemon.nom.toUpperCase());
        nameLabel.getStyleClass().add("pokemon-name");

        ProgressBar hpBar = new ProgressBar();
        double progress = (double) pokemon.pvRestant / pokemon.stats.pv;
        hpBar.setProgress(progress);
        hpBar.setPrefWidth(width * 0.14);
        hpBar.setPrefHeight(15);

        hpBar.getStyleClass().add("hp-bar");
        if (progress > 0.5) {
            hpBar.getStyleClass().add("hp-high");
        } else if (progress > 0.2) {
            hpBar.getStyleClass().add("hp-medium");
        } else {
            hpBar.getStyleClass().add("hp-low");
        }

        HBox hpTextContainer = new HBox();
        hpTextContainer.getStyleClass().add("hp-text-container");

        if (isPlayer) {
            Label hpLabel = new Label(pokemon.pvRestant + " / " + pokemon.stats.pv);
            hpLabel.getStyleClass().add("hp-label");
            hpTextContainer.getChildren().add(hpLabel);
        }

        container.getChildren().addAll(nameLabel, hpBar, hpTextContainer);
        return container;
    }

    private static ImageView createPokemonSprite(Pokemon pokemon, boolean isPlayer) {
        ImageView spriteView = new ImageView(new Image(isPlayer && pokemon.backSprite != null ? pokemon.backSprite : pokemon.frontSprite));
        spriteView.setFitWidth(150);
        spriteView.setPreserveRatio(true);
        spriteView.setScaleX(1.2);
        spriteView.setScaleY(1.2);
        return spriteView;
    }
}