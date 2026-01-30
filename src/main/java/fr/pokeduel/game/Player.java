package fr.pokeduel.game;


import fr.pokeduel.data.entity.Pokemon;

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
