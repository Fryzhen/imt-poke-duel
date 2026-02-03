package fr.pokeduel.ui.battle;

import fr.pokeduel.actions.Action;
import fr.pokeduel.actions.Attaquer;
import fr.pokeduel.entity.Attaque;
import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BattleMenuUI {
    private static HBox menuArea;

    public static Node getMenuAreaNode(Game game, double width, double height) {
        menuArea = new HBox(100);
        menuArea.setPrefHeight(height * 0.3);
        menuArea.setAlignment(Pos.CENTER);
        menuArea.getStyleClass().add("battle-menu-area");

        menuArea.getChildren().addAll(getAttackGridNode(game), getSwitchPokemonNode(game));

        return menuArea;
    }

    private static Node getSwitchPokemonNode(Game game) {
        Label label = new Label("Changer de Pokémon");
        label.getStyleClass().add("menu-title");

        GridPane teamGrid = new GridPane();
        teamGrid.setHgap(10);
        teamGrid.setVgap(10);
        teamGrid.setAlignment(Pos.CENTER);

        var team = game.players.get(0).pokemons;
        for (int i = 0; i < team.size(); i++) {
            Button pokeBtn = new Button();
            ImageView pokeImg = new ImageView(team.get(i).frontSprite);
            pokeImg.setFitWidth(60);
            pokeImg.setPreserveRatio(true);

            pokeBtn.setGraphic(pokeImg);
            pokeBtn.getStyleClass().add("team-button");

            if (i == game.players.get(0).pokemonActifIndex) {
                pokeBtn.setDisable(true);
                pokeBtn.getStyleClass().add("active-pokemon");
            }

            teamGrid.add(pokeBtn, i % 3, i / 3);
        }
        VBox vbox = new VBox(15, label, teamGrid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private static Node getAttackGridNode(Game game) {
        Label label = new Label("Choisir une attaque");
        label.getStyleClass().add("menu-title");

        GridPane attackGrid = new GridPane();
        attackGrid.setHgap(15);
        attackGrid.setVgap(15);
        attackGrid.setAlignment(Pos.CENTER);

        Pokemon pActif = game.players.get(0).pokemons.get(game.players.get(0).pokemonActifIndex);

        for (int i = 0; i < 4; i++) {
            boolean hasMove = i < pActif.attaques.size();
            Button atkBtn = null;
            if (!hasMove) {
                atkBtn = new Button("-");
                atkBtn.getStyleClass().add("attack-button");
                atkBtn.setDisable(true);
            } else {
                Attaque attaque = pActif.attaques.get(i);
                atkBtn = new Button(attaque.name.substring(0, 1).toUpperCase() + attaque.name.substring(1).toLowerCase());
                atkBtn.getStyleClass().add("attack-button");
                atkBtn.setOnAction(e -> {
                    switchToActionScreen(game, new Attaquer(game.players.get(0), attaque.id));
                });
            }
            attackGrid.add(atkBtn, i % 2, i / 2);
        }
        VBox vbox = new VBox(15, label, attackGrid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private static void switchToActionScreen(Game game, Action a) {
    }

    private static Node getActionScreenNode(Game game) {
        VBox actionBox = new VBox();
        actionBox.setAlignment(Pos.CENTER);
        Label placeholder = new Label("Action Screen - To be implemented");
        placeholder.getStyleClass().add("menu-title");
        actionBox.getChildren().add(placeholder);
        return actionBox;
    }
}