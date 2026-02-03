package fr.pokeduel.actions;

import fr.pokeduel.game.Player;

public abstract class Action {
    public Player player;
    public int valueId;

    public Action(Player player, int valueId) {
        this.player = player;
        this.valueId = valueId;
    }
}
