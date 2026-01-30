package fr.pokeduel.data.entity;

import java.lang.reflect.Type;

public class AttaqueEntity {

    protected int id;
    protected String name;
    protected Type type;
    protected int power;
    protected int accuracy;
    protected int pp;
    protected int ppRestants;
    protected String effect;

    public AttaqueEntity(String name, Type type, int power, int accuracy, int pp, String effect) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.ppRestants = pp;
        this.effect = effect;
    }
}
