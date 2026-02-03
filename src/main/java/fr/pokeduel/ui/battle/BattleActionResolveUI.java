package fr.pokeduel.ui.battle;

import fr.pokeduel.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class BattleActionResolveUI {
    public static Node getActionResolveNode(Game game, double width, double height, String actionDescription) {
        HBox menuArea = new HBox(100);
        menuArea.setPrefHeight(height * 0.3);
        menuArea.setAlignment(Pos.CENTER);
        menuArea.getStyleClass().add("battle-menu-area");

        javafx.scene.control.Label resolvingLabel = new javafx.scene.control.Label(actionDescription);
        resolvingLabel.getStyleClass().add("menu-title");
        menuArea.getChildren().add(resolvingLabel);

        return menuArea;
    }
}
