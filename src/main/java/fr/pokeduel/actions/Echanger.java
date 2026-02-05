package fr.pokeduel.actions;

import fr.pokeduel.game.Game;
import fr.pokeduel.game.Player;

public class Echanger extends Action {
    public Echanger(Game game, Player player, int valueId) {
        super(game, player, valueId);
    }

    public int getPriority() {
        return 1;
    }

    public void execute() {
        player.setActivePokemon(valueId);
    }

    public String getMessage() {
        return player.nom + " échange son Pokémon contre " + player.getActivePokemon().nom + " !";
    }
}
