package fr.pokeduel.pokemon;

import fr.pokeduel.data.entity.PokemonEntity;

import java.util.List;

public abstract class Pokemon extends PokemonEntity {

    protected int pv;


    public Pokemon(String nom, Stats stats, List<TypePokemon> types,  List<Attaque> attaques) {
        this.nom = nom;
        this.stats = stats;
        this.pv = stats.pv;
        this.types = types;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

    public Stats getStats() {
        return stats;
    }

    public List<TypePokemon> getTypes() {
        return types;
    }

    public List<TypePokemon> getAttaques() {
        return attaques;
    }

}
