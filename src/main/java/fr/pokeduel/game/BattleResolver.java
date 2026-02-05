package fr.pokeduel.game;

import fr.pokeduel.actions.Action;

import java.util.ArrayList;

public class BattleResolver {
    public static void resolveActions(Game game, Action playerAction) {
        ArrayList<Action> actionList = new ArrayList<>();
        actionList.add(playerAction);
        actionList.add(game.bot.decideAction(game));

        actionList.sort((a1, a2) -> {
            if (a1.getPriority() != a2.getPriority()) {
                return Integer.compare(a2.getPriority(), a1.getPriority());
            }
            int speed1 = a1.player.getActivePokemon().stats.vitesse;
            int speed2 = a2.player.getActivePokemon().stats.vitesse;
            if (speed1 != speed2) {
                return Integer.compare(speed2, speed1);
            }
            return Math.random() < 0.5 ? -1 : 1;
        });
    }

    public static void resolveBattle(Game game) {
        if (game.player.getActivePokemon().isKO()) {
            if (game.player.hasAblePokemon()) {
                // TODO Prompt player to switch Pokemon
                return;
            } else {
                // TODO Player loses
                return;
            }
        } else if (game.bot.getActivePokemon().isKO()) {
            if (game.player.hasAblePokemon()) {
                game.bot.pokemonActifIndex = game.bot.getSwitchInPokemonIndex(game);
                // TODO Prompt player to switch Pokemon
                return;
            } else {
                // TODO Player loses
                return;
            }
        }

        Action action = game.actions.removeFirst();

        // TODO Execute action

        

    }
}
