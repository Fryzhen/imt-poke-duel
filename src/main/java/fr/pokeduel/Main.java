package fr.pokeduel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    // Composants UI accessibles pour mise à jour
    private ProgressBar playerHpBar;
    private ProgressBar enemyHpBar;
    private Label logLabel;

    @Override
    public void start(Stage primaryStage) {
        // --- LAYOUT PRINCIPAL ---
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #333;"); // Mode sombre pour le style

        // --- ZONE DE COMBAT ---
        HBox arena = new HBox(50);
        arena.setAlignment(Pos.CENTER);
        arena.setPrefHeight(250);

        VBox playerSide = createStatBox("Pikachu (Joueur)", playerHpBar = new ProgressBar(1.0));
        VBox enemySide = createStatBox("Dracaufeu (Ennemi)", enemyHpBar = new ProgressBar(1.0));

        arena.getChildren().addAll(playerSide, enemySide);
        root.setCenter(arena);

        // --- ZONE DE CONTRÔLE ---
        VBox controls = new VBox(10);
        controls.setPadding(new Insets(20));
        controls.setAlignment(Pos.CENTER);

        logLabel = new Label("Un Dracaufeu sauvage apparaît !");
        logLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        GridPane moveGrid = new GridPane();
        moveGrid.setHgap(10);
        moveGrid.setVgap(10);
        moveGrid.setAlignment(Pos.CENTER);

        // Ajout des boutons d'attaque
        String[] moves = {"Tonnerre", "Vive-Attaque", "Souplesse", "Fatal-Foudre"};
        for (int i = 0; i < moves.length; i++) {
            Button btn = new Button(moves[i]);
            btn.setPrefSize(150, 40);
            final String moveName = moves[i];
            // Action au clic
            btn.setOnAction(e -> gererAttaque(moveName));
            moveGrid.add(btn, i % 2, i / 2);
        }

        controls.getChildren().addAll(logLabel, moveGrid);
        root.setBottom(controls);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("JavaFX Pokémon Battle Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper pour créer les barres de statut
    private VBox createStatBox(String name, ProgressBar bar) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        Label lbl = new Label(name);
        lbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        bar.setPrefWidth(200);
        bar.setStyle("-fx-accent: #2ecc71;"); // Couleur verte
        box.getChildren().addAll(lbl, bar);
        return box;
    }

    // --- LOGIQUE DE COMBAT ---
    private void gererAttaque(String attaque) {
        // On lance la logique dans un nouveau Thread pour ne pas "geler" l'UI
        new Thread(() -> {
            try {
                // 1. Mise à jour du log (Via Platform.runLater !)
                Platform.runLater(() -> logLabel.setText("Pikachu utilise " + attaque + " !"));

                Thread.sleep(1000); // Pause dramatique

                // 2. Calcul des dégâts et mise à jour de la barre
                Platform.runLater(() -> {
                    double currentHp = enemyHpBar.getProgress();
                    enemyHpBar.setProgress(currentHp - 0.2); // On enlève 20%
                    logLabel.setText("C'est super efficace !");
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
