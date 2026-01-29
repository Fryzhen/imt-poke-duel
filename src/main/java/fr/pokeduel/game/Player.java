package fr.pokeduel.game;

import fr.pokeduel.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String nom;
    public boolean isHuman;
    public List<Pokemon> pokemons;

    public Player(String nom, boolean isHuman) {
        this.nom = nom;
        this.isHuman = isHuman;
    }
}
