package fr.pokeduel.data.entity;

import fr.pokeduel.pokemon.Attaque;
import fr.pokeduel.pokemon.Stats;
import fr.pokeduel.pokemon.TypePokemon;

import java.util.List;

public class PokemonEntity {

    protected int id;
    protected String nom;
    protected Stats stats;
    protected List<Integer> types;
    protected List<Integer> attaques;
    protected String backSprite;
    protected String frontSprite;

    public PokemonEntity(String nom, Stats stats, List<Integer> types,  List<Integer> attaques) {
        this.nom = nom;
        this.stats = stats;
        this.types = types;
    }

}
