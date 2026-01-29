package fr.pokeduel.pokemon;

import fr.pokeduel.pokemon.type.TypeEffectivite;

/**
 * Implémentation basique d'une attaque.
 * Réduit les PV de la cible d'un montant fixe (getDegats) modulé par l'efficacité des types.
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
        if (attaquant != null && attaquant.estKO()) {
            throw new fr.pokeduel.pokemon.PokemonKOException(attaquant.getNom() + " est KO et ne peut pas attaquer");
        }

        // Calcul du multiplicateur selon les types de la cible
        double multiplicateur = 1.0;
        if (cible.getTypes() != null && !cible.getTypes().isEmpty()) {
            for (TypePokemon defType : cible.getTypes()) {
                multiplicateur *= TypeEffectivite.getMultiplicateur(this.type, defType);
            }
        }

        int degatsFinaux = (int) Math.round(this.degats * multiplicateur);
        if (degatsFinaux < 0) degatsFinaux = 0;

        cible.subirDegats(degatsFinaux);
    }
}
