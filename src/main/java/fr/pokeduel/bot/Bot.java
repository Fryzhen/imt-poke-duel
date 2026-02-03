package fr.pokeduel.bot;

import fr.pokeduel.actions.Action;
import fr.pokeduel.game.Game;
import fr.pokeduel.game.Player;

public abstract class Bot extends Player {
    public Bot(String nom) {
        super(nom, false);
    }

    public abstract Action decideAction(Game game);

    public abstract int getSwitchInPokemonIndex(Game game);
}
