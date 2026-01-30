package fr.pokeduel.data.entity;

import java.lang.reflect.Type;

public class Attaque {

    public int id;
    public String name;
    public Type type;
    public int power;
    public int accuracy;
    public int pp;
    public String effect;

    public Attaque(String name, Type type, int power, int accuracy, int pp, String effect) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.effect = effect;
    }
}
