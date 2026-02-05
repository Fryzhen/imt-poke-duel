package fr.pokeduel.bot;

import fr.pokeduel.actions.Action;
import fr.pokeduel.actions.Attaquer;
import fr.pokeduel.actions.Echanger;
import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;

public class SimpleBot extends Bot {
    public SimpleBot(String nom) {
        super(nom);
    }

    public Action decideAction(Game game) {
        return getSwitchInPokemon(game) == -1 ? new Attaquer(game, this, getActivePokemon().attaques.getFirst().id) : new Echanger(game, this, getSwitchInPokemon(game));
    }

    public int getSwitchInPokemon(Game game) {
        for (Pokemon pokemon : game.bot.pokemons) {
            if (!pokemon.isKO() && pokemon.id != getActivePokemon().id) {
                return pokemon.id;
            }
        }
        return -1;
    }
}
