package fr.pokeduel.data.entity;

import java.util.List;

public class Pokemon {

    public int id;

    public int pvRestant;

    public String nom;

    public Stats stats;

    public List<Integer> types;

    public List<Integer> attaques;

    public List<Integer> attaquesApprenables;

    public String backSprite;

    public String frontSprite;

    public Pokemon() {
    }
}
