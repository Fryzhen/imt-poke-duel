package fr.pokeduel.pokeApi;

import java.util.ArrayList;
import java.util.List;

public class PokeApiParser {
    public static PokeApiPokemonDTO parse(String json) {

        String nom = extraire(json, "\"name\":\"", "\"");
        int pv = Integer.parseInt(extraire(json, "\"base_stat\":", ","));

        List<String> types = new ArrayList<>();
        int index = 0;

        while ((index = json.indexOf("\"type\"", index)) != -1) {
            String type = extraire(json.substring(index), "\"name\":\"", "\"");
            types.add(type);
            index++;
        }

        return new PokeApiPokemonDTO(nom, pv, types);
    }

    private static String extraire(String texte, String debut, String fin) {
        int start = texte.indexOf(debut) + debut.length();
        int end = texte.indexOf(fin, start);
        return texte.substring(start, end);
    }
}
