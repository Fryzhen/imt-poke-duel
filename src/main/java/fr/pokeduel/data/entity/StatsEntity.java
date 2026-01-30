package fr.pokeduel.data.entity;

public class StatsEntity {

    public int pv;
    public int attaque;
    public int attaqueSpe;
    public int defense;
    public int defenseSpe;
    public int vitesse;

    public StatsEntity(int pv, int attaque, int attaqueSpe, int defense, int defenseSpe, int vitesse) {
        this.pv = pv;
        this.attaque = attaque;
        this.attaqueSpe = attaqueSpe;
        this.defense = defense;
        this.defenseSpe = defenseSpe;
        this.vitesse = vitesse;
    }

}
