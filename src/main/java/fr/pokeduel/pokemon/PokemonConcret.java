package fr.pokeduel.pokemon;

import java.util.List;

public class PokemonConcret extends Pokemon {

    private final Attaque attaqueParDefaut;

    public PokemonConcret(
            String nom,
            int pvMax,
            List<TypePokemon> types,
            Attaque attaqueParDefaut
    ) {
        super(nom, pvMax, types);
        this.attaqueParDefaut = attaqueParDefaut;
    }

    @Override
    public TypePokemon getType() {
        return (types == null || types.isEmpty()) ? null : types.get(0);
    }

    @Override
    public int getDegats() {
        return attaqueParDefaut != null ? attaqueParDefaut.getDegats() : 0;
    }

    @Override
    public void appliquer(Pokemon attaquant, Pokemon cible) {
        if (attaqueParDefaut != null) {
            attaqueParDefaut.appliquer(attaquant != null ? attaquant : this, cible);
        }
    }
}
