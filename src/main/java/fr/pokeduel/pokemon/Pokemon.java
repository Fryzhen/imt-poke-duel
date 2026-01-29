package fr.pokeduel.pokemon;

import java.util.List;

public abstract class Pokemon {

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

    public int getPvMax() {
        return pvMax;
    }

    public void subirDegats(int degats) {
        if (degats < 0) return;
        this.pv -= degats;
        if (this.pv < 0) this.pv = 0;
    }

    public boolean estKO() {
        return this.pv <= 0;
    }

    // Méthode abstraite demandée
    public abstract Attaque getAttaqueParDefaut();
}
