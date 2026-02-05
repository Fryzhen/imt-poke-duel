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
        Pokemon attaquant;
        Pokemon defenseur;
        if (this.game.player == this.player) {
            attaquant = this.game.player.getActivePokemon();
            defenseur = this.game.bot.getActivePokemon();
        } else {
            attaquant = this.game.bot.getActivePokemon();
            defenseur = this.game.player.getActivePokemon();
        }

        int damage = attaquant.calculateDamage(defenseur, valueId);
        defenseur.pvRestant -= damage;
    }

    public String getMessage() {
        return player.nom + " utilise " + player.getActivePokemon().getAttackNameById(valueId) + " !";
    }
}
