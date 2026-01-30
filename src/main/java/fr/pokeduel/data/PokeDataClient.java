package fr.pokeduel.data;

import fr.pokeduel.pokemon.Pokemon;
import fr.pokeduel.pokemon.Stats;
import fr.pokeduel.pokemon.TypePokemon;

import fr.pokeduel.pokemon.Attaque;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PokeDataClient {

    private static final String JSON_FILE = "/pokemons.json";

    private String lireJson(String chemin) {
        try (InputStream is = getClass().getResourceAsStream(chemin)) {

            if (is == null) {
                throw new RuntimeException("Fichier introuvable : " + chemin);
            }

            return new String(is.readAllBytes());

        } catch (Exception e) {
            throw new RuntimeException("Erreur lecture JSON", e);
        }
    }

    private String extraireString(String json, String cle) {
        int keyPos = json.indexOf(cle);
        if (keyPos == -1) {
            throw new IllegalArgumentException("Clé introuvable : " + cle);
        }

        // Se placer après le :
        int start = json.indexOf(":", keyPos);
        if (start == -1) {
            throw new IllegalArgumentException("':' introuvable après la clé : " + cle);
        }
        start++; // après :

        // Sauter espaces et retours ligne
        while (start < json.length()) {
            char c = json.charAt(start);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                start++;
            } else {
                break;
            }
        }

        // Le premier caractère doit être un "
        if (json.charAt(start) != '"') {
            throw new IllegalStateException("Valeur string invalide pour la clé : " + cle);
        }
        start++; // après le premier "

        int end = json.indexOf("\"", start);
        if (end == -1) {
            throw new IllegalStateException("Fin de string introuvable pour la clé : " + cle);
        }

        return json.substring(start, end);
    }


    private int extraireInt(String json, String cle) {
        int keyPos = json.indexOf(cle);
        if (keyPos == -1) {
            throw new IllegalArgumentException("Clé introuvable : " + cle);
        }

        // Se placer juste après le ":" qui suit la clé
        int start = json.indexOf(":", keyPos);
        if (start == -1) {
            throw new IllegalArgumentException("':' introuvable après la clé : " + cle);
        }
        start++; // après :

        // Sauter espaces, guillemets, retours ligne
        while (start < json.length()) {
            char c = json.charAt(start);
            if (c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == '"') {
                start++;
            } else {
                break;
            }
        }

        // Lire les chiffres (et éventuellement un signe -)
        int end = start;
        if (end < json.length() && json.charAt(end) == '-') {
            end++;
        }
        while (end < json.length() && Character.isDigit(json.charAt(end))) {
            end++;
        }

        String valeur = json.substring(start, end);
        return Integer.parseInt(valeur);
    }



    private List<Integer> extraireListeInt(String json, String cle) {
        List<Integer> valeurs = new ArrayList<>();

        int start = json.indexOf(cle);
        start = json.indexOf("[", start) + 1;
        int end = json.indexOf("]", start);

        String contenu = json.substring(start, end);
        for (String v : contenu.split(",")) {
            valeurs.add(Integer.parseInt(v.trim()));
        }

        return valeurs;
    }




    /**
     * Parse le json
     * @return
     */
    public List<Pokemon> getPokemonList() {
        List<Pokemon> pokemons = new ArrayList<>();

        String json = lireJson("/json/pokemon.json");

        // enlever [ ]
        json = json.trim().substring(1, json.length() - 1);

        // séparer chaque Pokémon
        String[] objets = json.split("\\},\\s*\\{");

        for (String objet : objets) {

            String data = objet;
            if (!data.startsWith("{")) data = "{" + data;
            if (!data.endsWith("}")) data = data + "}";

            // ----- NOM -----
            String nom = extraireString(data, "\"nom\"");

            // ----- STATS -----
            Stats stats = new Stats();
            stats.pv = extraireInt(data, "\"hp\"");
            stats.attaque = extraireInt(data, "\"attack\"");
            stats.defense = extraireInt(data, "\"defense\"");
            stats.attaqueSpe = extraireInt(data, "\"specialAttack\"");
            stats.defenseSpe = extraireInt(data, "\"specialDefense\"");
            stats.vitesse = extraireInt(data, "\"speed\"");

            // ----- TYPES -----
            List<TypePokemon> types = new ArrayList<>();
            List<Integer> typeIds = extraireListeInt(data, "\"typeIds\"");
            for (int id : typeIds) {
                types.add(TypePokemon.values()[id]);
            }

            // ----- ATTAQUES (vide pour l’instant) -----
            List<Attaque> attaques = new ArrayList<>();
            List<Integer> attaqueId = extraireListeInt(data, "\"attaquesIds\"");
            for (int id : attaqueId) {
                attaques.add(Attaque.getAttaqueById(id));
            }

            // ----- CRÉATION DU POKEMON -----
            Pokemon pokemon = new Pokemon(nom, stats, types, attaques) {};
            pokemons.add(pokemon);
        }

        return pokemons;

    }

    /**
     * return le pokémon
     */
    public Pokemon getPokemon(int id) {

        List<Pokemon> pokemons = getPokemonList();

        if (id < 0 || id >= pokemons.size()) {
            throw new IllegalArgumentException("ID de Pokémon invalide : " + id);
        }

        return pokemons.get(id);
    }
}
