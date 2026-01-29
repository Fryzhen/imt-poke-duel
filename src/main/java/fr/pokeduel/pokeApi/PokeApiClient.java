package fr.pokeduel.pokeApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeApiClient {

    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static String getPokemonJson(String nom) throws Exception {
        URL url = new URL(API_URL + nom.toLowerCase());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        reader.close();
        return json.toString();
    }
}
