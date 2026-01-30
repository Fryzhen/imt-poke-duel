package fr.pokeduel.data.entity;

import fr.pokeduel.pokemon.Stats;
import fr.pokeduel.pokemon.TypePokemon;

import java.util.List;

public class PokemonEntity {

    protected int id;
    protected String nom;
    protected Stats stats;
    protected List<TypePokemon> types;
    protected List<TypePokemon> attaques;
    protected String backSprite;
    protected String frontSprite;
}
