package fr.pokeduel.game;


import fr.pokeduel.data.entity.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String nom;
    public boolean isHuman;
    public List<Integer> pokemons;

    public Player(String nom, boolean isHuman) {
        this.nom = nom;
        this.isHuman = isHuman;
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(int pokemonId) {
        if ( this.pokemons.size() >= 6 ) {
            throw new RuntimeException("Un joueur ne peut pas avoir plus de 6 pokémons");
        }
        this.pokemons.add(pokemonId);
    }

    public void removePokemon(int pokemonId) {
        if ( !this.pokemons.contains(pokemonId) ) {
            throw new RuntimeException("Le pokémon avec l'id " + pokemonId + " n'appartient pas au joueur " + this.nom);
        }
        this.pokemons.remove(pokemonId);
    }
}
