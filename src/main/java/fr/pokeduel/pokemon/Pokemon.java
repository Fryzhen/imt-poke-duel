package fr.pokeduel.pokemon;

import fr.pokeduel.data.entity.AttaqueEntity;
import fr.pokeduel.data.entity.PokemonEntity;

import java.util.List;

public abstract class Pokemon extends PokemonEntity {

    protected int pvRestant;


    public Pokemon(String nom, Stats stats, List<Integer> types, List<Integer> attaques) {
        super(nom, stats, types, attaques);
        this.pvRestant = stats.pv;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pvRestant;
    }

    public Stats getStats() {
        return stats;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public List<Integer> getAttaques() {
        return attaques;
    }

}
