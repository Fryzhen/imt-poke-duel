package fr.pokeduel.ui;

import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MenuUI {
    public static Node getPanelNode(Game game) {
        VBox menuSidePanel = new VBox(15);
        menuSidePanel.getStyleClass().add("side-panel");
        menuSidePanel.setPadding(new Insets(20));
        menuSidePanel.setAlignment(Pos.CENTER);
        menuSidePanel.setPrefWidth(250);

        Button btnBattle = new Button("Lancer un combat");
        Button btnTeam = new Button("Choisir mon équipe");
        Button btnName = new Button("Changer mon nom");
        Button btnQuit = new Button("Quitter");

        for (Button b : new Button[]{btnBattle, btnTeam, btnName, btnQuit}) {
            b.setMaxWidth(Double.MAX_VALUE);
            b.getStyleClass().add("menu-button");
        }

        btnBattle.setDisable(game.player.pokemons.isEmpty());
        btnBattle.setOnAction(e -> {
            game.stage.close();
            ScreenManager.initDisplayBattle(game);
        });
        btnQuit.setOnAction(e -> game.stage.close());
        btnName.setOnAction(e -> {
            game.stage.close();
            ScreenManager.displayNameChange(game);
        });

        menuSidePanel.getChildren().addAll(btnBattle, btnTeam, btnName, btnQuit);
        return menuSidePanel;
    }

    public static Node getTeamPanelNode(Game game) {
        GridPane teamPanel = new GridPane();
        teamPanel.getStyleClass().add("team-panel");
        teamPanel.setPadding(new Insets(20));
        teamPanel.setHgap(30);
        teamPanel.setVgap(30);
        teamPanel.setAlignment(Pos.CENTER);

        int i = 0;
        for (Pokemon pokemon : game.player.pokemons) {
            ImageView iv = new ImageView(pokemon.frontSprite);
            iv.getStyleClass().add("team-pokemon-sprite");

            iv.setPreserveRatio(true);
            teamPanel.add(iv, i % 3, i / 3);
            i++;
        }
        return teamPanel;
    }
}
