package fr.pokeduel.pokemon;

import java.util.List;

public abstract class Pokemon implements Attaque {

    protected String nom;
    protected int pv;
    protected int pvMax;
    protected List<TypePokemon> types;

    public Pokemon(String nom, int pvMax, List<TypePokemon> types) {
        this.nom = nom;
        this.pvMax = pvMax;
        this.pv = pvMax;
        this.types = types;
    }

    public List<TypePokemon> getTypes() {
        return types;
    }

    public String getNom() {
        return nom;
    }

    public int getPv() {
        return pv;
    }

//    public abstract int attaquer();
}
