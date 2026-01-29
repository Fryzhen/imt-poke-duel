package fr.pokeduel.pokemon;

import java.util.List;

public class PokemonConcret extends Pokemon {

    public PokemonConcret(
            String nom,
            int pvMax,
            List<TypePokemon> types,
            Attaque attaqueParDefaut
    ) {
        super(nom, pvMax, types);
    }

    @Override
    public TypePokemon getType() {
        return null;
    }

    @Override
    public int getDegats() {
        return 0;
    }

    @Override
    public void appliquer(Pokemon attaquant, Pokemon cible) {

    }
}

