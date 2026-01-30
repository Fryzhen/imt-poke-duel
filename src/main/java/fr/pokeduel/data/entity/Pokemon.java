package fr.pokeduel.data.entity;

import java.util.List;

public class Pokemon {

    public int id;
    public String nom;
    public Stats stats;
    public List<Integer> types;
    public List<Integer> attaques;
    public String backSprite;
    public String frontSprite;

    public Pokemon(String nom, Stats stats, List<Integer> types, List<Integer> attaques) {
        this.nom = nom;
        this.stats = stats;
        this.types = types;
    }

}
