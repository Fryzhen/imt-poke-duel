package fr.pokeduel.data.entity;

import java.lang.reflect.Type;

public class Attaque {

    protected int id;
    protected String name;
    protected Type type;
    protected int power;
    protected int accuracy;
    protected int pp;
    protected String effect;

    public Attaque(String name, Type type, int power, int accuracy, int pp, String effect) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.effect = effect;
    }
}
