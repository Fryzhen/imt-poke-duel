package fr.pokeduel.api;

import java.util.List;

public class PokeApiPokemonDTO {
    private final String nom;
    private final int pv;
    private final List<String> types;

    public PokeApiPokemonDTO(String nom, int pv, List<String> types) {
        this.nom = nom;
        this.pv = pv;
        this.types = types;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public List<String> getTypes() {
        return types;
    }
}
