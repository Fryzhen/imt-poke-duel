package fr.pokeduel.bot;

import fr.pokeduel.actions.Action;
import fr.pokeduel.actions.Echanger;
import fr.pokeduel.game.Game;

public class SimpleBot extends Bot {
    public SimpleBot(String nom) {
        super(nom);
    }

    Action getMove(Game game) {
        return new Echanger(this, pokemons.get(pokemonActifIndex + 1 % pokemons.size()).id);
    }
}
