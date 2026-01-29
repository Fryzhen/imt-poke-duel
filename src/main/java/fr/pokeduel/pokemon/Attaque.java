package fr.pokeduel.pokemon;

public interface Attaque {

    String getNom();
    TypePokemon getType();
    int getDegats();

    /**
     * Applique l'attaque sur un Pokémon cible
     * → permet le polymorphisme
     */
    void appliquer(Pokemon attaquant, Pokemon cible);
}

