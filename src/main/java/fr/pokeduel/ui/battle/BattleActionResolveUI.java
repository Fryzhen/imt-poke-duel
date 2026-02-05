package fr.pokeduel.ui.battle;

import fr.pokeduel.game.BattleResolver;
import fr.pokeduel.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class BattleActionResolveUI {
    public static Node getMessageBoxNode(Game game, String message) {
        HBox menuArea = new HBox(100);
        menuArea.setPrefHeight(game.height * 0.3);
        menuArea.setAlignment(Pos.CENTER);
        menuArea.getStyleClass().add("battle-menu-area");

        javafx.scene.control.Label resolvingLabel = new javafx.scene.control.Label(message);
        resolvingLabel.getStyleClass().add("menu-title");
        menuArea.getChildren().add(resolvingLabel);

        menuArea.setOnMouseClicked(e -> {
            BattleResolver.resolveBattle(game);
        });

        return menuArea;
    }
}
