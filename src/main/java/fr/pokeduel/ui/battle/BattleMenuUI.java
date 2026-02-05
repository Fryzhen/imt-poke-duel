package fr.pokeduel.ui.battle;

import fr.pokeduel.actions.Attaquer;
import fr.pokeduel.actions.Echanger;
import fr.pokeduel.entity.Attaque;
import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.BattleResolver;
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

    public static Node getMenuAreaNode(Game game) {
        HBox menuArea = new HBox(100);
        menuArea.setPrefHeight(game.height * 0.3);
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

        var team = game.player.pokemons;
        for (int i = 0; i < team.size(); i++) {
            Pokemon pokemon = team.get(i);
            Button pokeBtn = new Button();
            ImageView pokeImg = new ImageView(pokemon.frontSprite);
            pokeImg.setFitWidth(60);
            pokeImg.setPreserveRatio(true);

            pokeBtn.setGraphic(pokeImg);
            pokeBtn.getStyleClass().add("team-button");

            if (game.player.pokemons.get(i).id == game.player.getActivePokemon().id) {
                pokeBtn.setDisable(true);
                pokeBtn.getStyleClass().add("active-pokemon");
            } else {
                pokeBtn.setOnAction(e -> {
                    BattleResolver.resolveActions(game, new Echanger(game, game.player, pokemon.id));
                });
            }


            teamGrid.add(pokeBtn, i % 3, i / 3);
        }
        VBox vbox = new VBox(15, label, teamGrid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public static Node getForceSwitchPokemonNode(Game game) {
        HBox menuArea = new HBox(100);
        menuArea.setPrefHeight(game.height * 0.3);
        menuArea.setAlignment(Pos.CENTER);
        menuArea.getStyleClass().add("battle-menu-area");

        Label label = new Label("Veuillez choisir un Pokémon pour continuer le combat");
        label.getStyleClass().add("menu-title");

        GridPane teamGrid = new GridPane();
        teamGrid.setHgap(10);
        teamGrid.setVgap(10);
        teamGrid.setAlignment(Pos.CENTER);

        var team = game.player.pokemons;
        for (int i = 0; i < team.size(); i++) {
            Pokemon pokemon = team.get(i);
            Button pokeBtn = new Button();
            ImageView pokeImg = new ImageView(pokemon.frontSprite);
            pokeImg.setFitWidth(60);
            pokeImg.setPreserveRatio(true);

            pokeBtn.setGraphic(pokeImg);
            pokeBtn.getStyleClass().add("team-button");

            if (game.player.pokemons.get(i).id == game.player.getActivePokemon().id) {
                pokeBtn.setDisable(true);
                pokeBtn.getStyleClass().add("active-pokemon");
            } else {
                pokeBtn.setOnAction(e -> {
                    game.player.setActivePokemon(pokemon.id);
                    BattleResolver.resolveBattle(game);
                });
            }


            teamGrid.add(pokeBtn, i % 3, i / 3);
        }
        VBox vbox = new VBox(15, label, teamGrid);
        vbox.setAlignment(Pos.CENTER);

        menuArea.getChildren().add(vbox);
        return menuArea;
    }

    private static Node getAttackGridNode(Game game) {
        Label label = new Label("Choisir une attaque");
        label.getStyleClass().add("menu-title");

        GridPane attackGrid = new GridPane();
        attackGrid.setHgap(15);
        attackGrid.setVgap(15);
        attackGrid.setAlignment(Pos.CENTER);

        Pokemon pActif = game.player.getActivePokemon();

        for (int i = 0; i < 4; i++) {
            boolean hasMove = i < pActif.attaques.size();
            Button atkBtn;

            if (!hasMove) {
                atkBtn = new Button("-");
                atkBtn.getStyleClass().add("attack-button");
                atkBtn.setDisable(true);
            } else {
                Attaque attaque = pActif.attaques.get(i);
                atkBtn = new Button(attaque.name.substring(0, 1).toUpperCase() + attaque.name.substring(1).toLowerCase());
                atkBtn.getStyleClass().add("attack-button");

                String hexColor = getTypeColorById(attaque.typeId);
                atkBtn.setStyle("-fx-background-color: " + hexColor + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5;");

                atkBtn.setOnAction(e -> {
                    BattleResolver.resolveActions(game, new Attaquer(game, game.player, attaque.id));
                });
            }
            attackGrid.add(atkBtn, i % 2, i / 2);
        }

        VBox vbox = new VBox(15, label, attackGrid);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private static String getTypeColorById(int typeId) {
        return switch (typeId) {
            case 1 -> "#A8A878";  // Normal
            case 2 -> "#C03028";  // Fighting
            case 3 -> "#A890F0";  // Flying
            case 4 -> "#A040A0";  // Poison
            case 5 -> "#E0C068";  // Ground
            case 6 -> "#B8A038";  // Rock
            case 7 -> "#A8B820";  // Bug
            case 8 -> "#705898";  // Ghost
            case 9 -> "#B8B8D0";  // Steel
            case 10 -> "#F08030"; // Fire
            case 11 -> "#6890F0"; // Water
            case 12 -> "#78C850"; // Grass
            case 13 -> "#F8D030"; // Electric
            case 14 -> "#F85888"; // Psychic
            case 15 -> "#98D8D8"; // Ice
            case 16 -> "#7038F8"; // Dragon
            case 17 -> "#705848"; // Dark
            case 18 -> "#EE99AC"; // Fairy
            default -> "#717171";
        };
    }
}