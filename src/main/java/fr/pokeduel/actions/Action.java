package fr.pokeduel.actions;

import fr.pokeduel.game.Game;
import fr.pokeduel.game.Player;

public abstract class Action {
    public Game game;
    public Player player;
    public int valueId;
    public String description = "";

    public Action(Game game, Player player, int valueId) {
        this.game = game;
        this.player = player;
        this.valueId = valueId;
    }

    public abstract int getPriority();

    public abstract void execute();

    public abstract String getMessage();
}
