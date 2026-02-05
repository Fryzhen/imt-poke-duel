package fr.pokeduel.actions;

import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.game.Game;
import fr.pokeduel.game.Player;

public class Attaquer extends Action {
    public Attaquer(Game game, Player player, int valueId) {
        super(game, player, valueId);
    }

    public int getPriority() {
        return 0;
    }

    public void execute() {
        Pokemon pokemon = this.game.player.getActivePokemon();
        pokemon.pvRestant -= this.player.getActivePokemon().calculateDamage(pokemon, valueId);
    }

    public String getMessage() {
        return player.nom + " utilise " + player.getActivePokemon().getAttackNameById(valueId) + " !";
    }
}
