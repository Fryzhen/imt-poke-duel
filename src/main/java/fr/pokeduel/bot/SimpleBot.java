package fr.pokeduel.bot;

import fr.pokeduel.actions.Action;
import fr.pokeduel.actions.Echanger;
import fr.pokeduel.game.Game;

public class SimpleBot extends Bot {
    public SimpleBot(String nom) {
        super(nom);
    }

    public Action decideAction(Game game) {
        return new Echanger(this, getSwitchInPokemonIndex(game));
    }

    public int getSwitchInPokemonIndex(Game game) {
        for (int i = 0; i < game.bot.pokemons.size(); i++) {
            if (!game.bot.pokemons.get(i).isKO() && i != game.bot.pokemonActifIndex) {
                return i;
            }
        }
        return -1;
    }
}
