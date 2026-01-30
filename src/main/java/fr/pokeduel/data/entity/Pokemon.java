package fr.pokeduel.data.entity;

import java.util.List;

public class Pokemon {

    protected int id;
    protected String nom;
    protected Stats stats;
    protected List<Integer> types;
    protected List<Integer> attaques;
    protected String backSprite;
    protected String frontSprite;

    public Pokemon(String nom, Stats stats, List<Integer> types, List<Integer> attaques) {
        this.nom = nom;
        this.stats = stats;
        this.types = types;
    }

}
