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

    public PokemonConcret(
            String nom,
            int pvMax,
            List<TypePokemon> types,
            Attaque attaqueParDefaut,
            String imagePath
    ) {
        super(nom, pvMax, types, imagePath);
        this.attaqueParDefaut = attaqueParDefaut;
    }

    // getType n'est pas déclaré dans la superclasse, on ne met pas @Override
    public TypePokemon getType() {
        return (types == null || types.isEmpty()) ? null : types.get(0);
    }

    @Override
    public Attaque getAttaqueParDefaut() {
        return attaqueParDefaut;
    }
}
