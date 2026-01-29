// Java
package fr.pokeduel.util;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.SnapshotParameters;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokeApiImageLoader {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    // Récupère l'image d'un Pokémon via l'API PokeAPI (official-artwork > front_default)
    public static Image loadPokemonImage(String nameOrId) {
        try {
            String endpoint = "https://pokeapi.co/api/v2/pokemon/" + nameOrId.toLowerCase();
            HttpRequest req = HttpRequest.newBuilder(URI.create(endpoint))
                    .header("User-Agent", "Pokeduel-App")
                    .GET()
                    .build();
            HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
            String body = resp.body();

            // Cherche official-artwork.front_default
            Pattern p = Pattern.compile("\"official-artwork\"\\s*:\\s*\\{[^}]*\"front_default\"\\s*:\\s*\"([^\"]+)\"");
            Matcher m = p.matcher(body);
            String url = null;
            if (m.find()) {
                url = m.group(1);
            } else {
                // fallback sur sprites.front_default
                p = Pattern.compile("\"front_default\"\\s*:\\s*\"([^\"]+)\"");
                m = p.matcher(body);
                if (m.find()) url = m.group(1);
            }

            if (url == null || url.isBlank()) return null;
            return new Image(url, true); // chargement asynchrone possible
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    // Génère une petite image/badge colorée pour un type (PokeAPI ne fournit pas d'icônes de type)
    public static Image loadTypeImage(String typeName) {
        String key = typeName == null ? "" : typeName.toLowerCase();
        Color color = TYPE_COLORS.getOrDefault(key, Color.GRAY);

        int w = 96, h = 48;
        Canvas canvas = new Canvas(w, h);
        var gc = canvas.getGraphicsContext2D();

        gc.setFill(color);
        gc.fillRoundRect(0, 0, w, h, 12, 12);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(16));
        String text = typeName == null ? "" : typeName.toUpperCase();
        double textX = 10;
        double textY = h * 0.65;
        gc.fillText(text, textX, textY);

        WritableImage img = new WritableImage(w, h);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        canvas.snapshot(params, img);
        return img;
    }

    private static final Map<String, Color> TYPE_COLORS = Map.ofEntries(
            Map.entry("normal", Color.web("#A8A77A")),
            Map.entry("fire", Color.web("#EE8130")),
            Map.entry("water", Color.web("#6390F0")),
            Map.entry("electric", Color.web("#F7D02C")),
            Map.entry("grass", Color.web("#7AC74C")),
            Map.entry("ice", Color.web("#96D9D6")),
            Map.entry("fighting", Color.web("#C22E28")),
            Map.entry("poison", Color.web("#A33EA1")),
            Map.entry("ground", Color.web("#E2BF65")),
            Map.entry("flying", Color.web("#A98FF3")),
            Map.entry("psychic", Color.web("#F95587")),
            Map.entry("bug", Color.web("#A6B91A")),
            Map.entry("rock", Color.web("#B6A136")),
            Map.entry("ghost", Color.web("#735797")),
            Map.entry("dragon", Color.web("#6F35FC")),
            Map.entry("dark", Color.web("#705746")),
            Map.entry("steel", Color.web("#B7B7CE")),
            Map.entry("fairy", Color.web("#D685AD"))
    );
}

