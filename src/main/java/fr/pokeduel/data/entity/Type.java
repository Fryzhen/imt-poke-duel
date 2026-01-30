package fr.pokeduel.data.entity;

public class Type {

    private int id;
    private String name;
    private String sprite;
    private int[] double_damage_to;
    private int[] double_damage_from;
    private int[] half_damage_to;
    private int[] half_damage_from;
    private int[] no_damage_to;
    private int[] no_damage_from;

    public Type(int id, String name, String sprite, int[] doubleDamageTo, int[] doubleDamageFrom, int[] halfDamageTo, int[] halfDamageFrom, int[] noDamageTo, int[] noDamageFrom) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
        this.double_damage_to = doubleDamageTo;
        this.double_damage_from = doubleDamageFrom;
        this.half_damage_to = halfDamageTo;
        this.half_damage_from = halfDamageFrom;
        this.no_damage_to = noDamageTo;
        this.no_damage_from = noDamageFrom;
    }
}
