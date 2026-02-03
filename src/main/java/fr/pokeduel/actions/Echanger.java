package fr.pokeduel.actions;

import fr.pokeduel.game.Player;

public class Echanger extends Action {
    public Echanger(Player player, int valueId) {
        super(player, valueId);
    }

    public int getPriority() {
        return 1;
    }
}
