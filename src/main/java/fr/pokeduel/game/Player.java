package fr.pokeduel.game;


import fr.pokeduel.entity.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String nom;
    public boolean isHuman;
    public int pokemonActifIndex = 0;
    public List<Pokemon> pokemons;

    public Player(String nom, boolean isHuman) {
        this.nom = nom;
        this.isHuman = isHuman;
        this.pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        if ( this.pokemons.size() >= 6 ) {
            throw new RuntimeException("Un joueur ne peut pas avoir plus de 6 pokémons");
        }
        this.pokemons.add(pokemon);
    }

    public void removePokemon(Pokemon pokemon) {
        if ( !this.pokemons.contains(pokemon) ) {
            throw new RuntimeException("Le pokémon avec l'id " + pokemon.id + " n'appartient pas au joueur " + this.nom);
        }
        this.pokemons.remove(pokemon);
    }

    public void reset() {
        this.pokemonActifIndex = 0;
        for (Pokemon pokemon : pokemons) {
            pokemon.resetPokemon();
        }
    }

    public boolean isPokemonEmpty() {
        return this.pokemons.isEmpty();
    }

    public void setActivePokemon(int id) {
        for (int i = 0; i < this.pokemons.size(); i++) {
            if (this.pokemons.get(i).id == id) {
                this.pokemonActifIndex = i;
                return;
            }
        }
        throw new RuntimeException("Le pokémon avec l'id " + id + " n'appartient pas au joueur " + this.nom);
    }

    public Pokemon getActivePokemon() {
        return this.pokemons.get(this.pokemonActifIndex);
    }
}
