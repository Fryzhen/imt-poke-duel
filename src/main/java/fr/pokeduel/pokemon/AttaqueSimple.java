package fr.pokeduel.pokemon;

/**
 * Implémentation basique d'une attaque.
 * Réduit les PV de la cible d'un montant fixe (getDegats).
 */
public class AttaqueSimple implements Attaque {

    private final String nom;
    private final TypePokemon type;
    private final int degats;

    public AttaqueSimple(String nom, TypePokemon type, int degats) {
        this.nom = nom;
        this.type = type;
        this.degats = degats;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public TypePokemon getType() {
        return type;
    }

    @Override
    public int getDegats() {
        return degats;
    }

    @Override
    public void appliquer(Pokemon attaquant, Pokemon cible) {
        if (cible == null) return;
        int nouveauxPv = cible.pv - degats;
        if (nouveauxPv < 0) nouveauxPv = 0;
        cible.pv = nouveauxPv;
    }
}
