package fr.pokeduel.pokemon;

import java.util.List;

public abstract class Pokemon {

    protected String nom;
    protected int pv;
    protected Stats stats;
    protected List<TypePokemon> types;
    protected List<TypePokemon> attaques;
    protected String backSprite;
    protected String frontSprite;

    public Pokemon(String nom, Stats stats, List<TypePokemon> types,  List<Attaque> attaques) {
        this.nom = nom;
        this.stats = stats;
        this.pv = stats.pv;
        this.types = types;
    }
}
