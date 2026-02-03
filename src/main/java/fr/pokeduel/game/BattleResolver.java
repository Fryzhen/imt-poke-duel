package fr.pokeduel.game;

import fr.pokeduel.actions.Action;
import fr.pokeduel.actions.Attaquer;
import fr.pokeduel.actions.Echanger;
import fr.pokeduel.ui.ScreenManager;

import java.util.ArrayList;
import java.util.Objects;

public class BattleResolver {
    public static void resolveBattle(Game game, Action action) {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(action);

        actions.add(game.bot.decideAction(game));


        // trier les actions par priorité et vitesse
        actions.sort((a1, a2) -> {
            if (a1.getPriority() != a2.getPriority()) {
                return Integer.compare(a2.getPriority(), a1.getPriority());
            } else {
                int speed1 = a1.player.getActivePokemon().stats.vitesse;
                int speed2 = a2.player.getActivePokemon().stats.vitesse;
                return Integer.compare(speed2, speed1);
            }
        });
        for (Action a : actions) {
            if (!resolveAction(game, a)) {
                break;
            }
        }
        ScreenManager.displayBattleChooseAction(game);
    }

    private static boolean resolveAction(Game game, Action action) {
        if (action instanceof Attaquer) {
            Player attacker = action.player;
            Player defender = (attacker == game.player) ? game.bot : game.player;
            defender.getActivePokemon().pvRestant -= attacker.getActivePokemon().calculateDamage(
                    defender.getActivePokemon(),
                    action.valueId
            );

            action.description = attacker.nom + " utilise " + Objects.requireNonNull(action.player.getActivePokemon().attaques.stream().filter(a -> a.id == action.valueId).findFirst().orElse(null)).name + " !";
            ScreenManager.displayBattleResolve(game, action);
            return defender.getActivePokemon().pvRestant > 0;
        } else if (action instanceof Echanger) {
            action.player.pokemonActifIndex = action.valueId;
            action.description = action.player.nom + " échange son Pokémon.";
            ScreenManager.displayBattleResolve(game, action);
            return true;
        } else {
            throw new RuntimeException("Action inconnue");
        }
    }
}
