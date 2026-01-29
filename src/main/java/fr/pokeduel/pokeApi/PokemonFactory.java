package fr.pokeduel.pokeApi;

//import fr.pokeduel.combat.AttaqueSimple;
import fr.pokeduel.pokemon.AttaqueSimple;
import fr.pokeduel.pokemon.Pokemon;
import fr.pokeduel.pokemon.PokemonConcret;
import fr.pokeduel.pokemon.TypePokemon;

import java.util.List;
import java.util.stream.Collectors;


public class PokemonFactory {
    public static Pokemon creerDepuisApi(String nom) throws Exception {

        String json = PokeApiClient.getPokemonJson(nom);
        PokeApiPokemonDTO dto = PokeApiParser.parse(json);

        List<TypePokemon> types = dto.getTypes().stream()
                .map(t -> TypePokemon.valueOf(t.toUpperCase()))
                .collect(Collectors.toList());

        return new PokemonConcret(
                dto.getNom(),
                dto.getPv(),
                types,
                new AttaqueSimple("Attaque basique", types.get(0), 20)
        );
    }
}
