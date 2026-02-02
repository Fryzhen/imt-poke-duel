package fr.pokeduel.ui;

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
    public static Node getMenuAreaNode(Game game, double width, double height) {
        HBox menuArea = new HBox(100);
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

        var team = game.player1.pokemons;
        for (int i = 0; i < team.size(); i++) {
            Button pokeBtn = new Button();
            ImageView pokeImg = new ImageView(team.get(i).frontSprite);
            pokeImg.setFitWidth(60);
            pokeImg.setPreserveRatio(true);

            pokeBtn.setGraphic(pokeImg);
            pokeBtn.getStyleClass().add("team-button");

            if (i == game.player1.pokemonActifIndex) {
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

        Pokemon pActif = game.player1.pokemons.get(game.player1.pokemonActifIndex);

        for (int i = 0; i < 4; i++) {
            boolean hasMove = i < pActif.attaques.size();
            String moveName = hasMove ? pActif.attaques.get(i).name : "-";

            Button atkBtn = new Button(moveName.substring(0, 1).toUpperCase() + moveName.substring(1).toLowerCase());
            atkBtn.getStyleClass().add("attack-button");

            if (!hasMove) {
                atkBtn.setDisable(true);
            }

            attackGrid.add(atkBtn, i % 2, i / 2);
        }
        VBox vbox = new VBox(15, label, attackGrid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}