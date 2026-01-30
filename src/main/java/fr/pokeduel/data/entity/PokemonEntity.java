package fr.pokeduel.data.entity;

import fr.pokeduel.pokemon.Attaque;
import fr.pokeduel.pokemon.Stats;
import fr.pokeduel.pokemon.TypePokemon;

import java.util.List;

public class PokemonEntity {

    protected int id;
    protected String nom;
    protected Stats stats;
    protected List<TypePokemon> types;
    protected List<AttaqueEntity> attaques;
    protected String backSprite;
    protected String frontSprite;

    public PokemonEntity(String nom, Stats stats, List<TypePokemon> types,  List<AttaqueEntity> attaques) {
        this.nom = nom;
        this.stats = stats;
        this.types = types;
    }

}
