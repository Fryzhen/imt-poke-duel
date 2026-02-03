package fr.pokeduel.game;

import fr.pokeduel.actions.Action;
import fr.pokeduel.actions.Attaquer;
import fr.pokeduel.actions.Echanger;
import fr.pokeduel.entity.Pokemon;
import fr.pokeduel.ui.ScreenManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BattleResolver {
    public static void resolveBattle(Game game, Action playerAction) {
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

        Queue<Action> actionQueue = new LinkedList<>(actionList);
        processNextAction(game, actionQueue);
    }

    private static void processNextAction(Game game, Queue<Action> queue) {
        // 1. Si plus d'actions, on relance le menu principal
        if (queue.isEmpty()) {
            ScreenManager.displayBattleChooseAction(game);
            return;
        }

        Action action = queue.poll();
        Player attacker = action.player;

        // 2. Vérif de sécurité : Si l'attaquant est déjà mort (tué par l'action précédente)
        // On annule son action et on continue (ou on arrête le tour, selon les règles)
        if (attacker.getActivePokemon().isKO()) {
            // L'action est perdue car le Pokémon n'est plus là
            processNextAction(game, queue);
            return;
        }

        // 3. Calcul et Logique
        boolean targetSurvived = applyActionLogic(game, action);

        // 4. Affichage de l'action
        Runnable onActionAnimationFinished = () -> {
            if (targetSurvived) {
                // Tout va bien, on passe à l'action suivante
                processNextAction(game, queue);
            } else {
                // KO DETECTÉ !
                // On vide la file d'attente car le tour est perturbé (l'adversaire est mort)
                queue.clear();
                handlePokemonFainted(game, action, queue);
            }
        };

        ScreenManager.displayBattleResolve(game, action, onActionAnimationFinished);
    }

    private static void handlePokemonFainted(Game game, Action fatalAction, Queue<Action> queue) {
        Player defender = (fatalAction.player == game.player) ? game.bot : game.player;
        Pokemon deadPokemon = defender.getActivePokemon();

        // 1. Message "X est KO"
        String koMessage = deadPokemon.nom + " est KO !";

        ScreenManager.displayMessage(game, koMessage, () -> {
            // Une fois le message lu :

            // 2. Vérification Victoire / Défaite
            if (!defender.hasAblePokemon()) {
                handleBattleEnd(game, defender != game.player); // Si le défenseur (perdant) n'est pas le joueur, le joueur gagne
            }
            // 3. Switch Forcé
            else {
                performForcedSwitch(game, defender);
            }
        });
    }

    private static void performForcedSwitch(Game game, Player playerWhoNeedsSwitch) {
        if (playerWhoNeedsSwitch == game.bot) {
            // --- LOGIQUE BOT ---
            // Le bot prend le premier Pokémon non-KO
            int newIndex = game.bot.getSwitchInPokemonIndex(game);
            playerWhoNeedsSwitch.pokemonActifIndex = newIndex;

            String message = game.bot.nom + " envoie " + game.bot.getActivePokemon().nom + " !";
            ScreenManager.displayMessage(game, message, () -> {
                // Le nouveau Pokémon est là, on relance un nouveau tour de choix
                ScreenManager.displayBattleChooseAction(game);
            });

        } else {
            // --- LOGIQUE JOUEUR ---
            // On affiche l'écran de sélection de Pokémon, mais en mode "Forcé"
            ScreenManager.displayForceSwitchMenu(game, (newPokemonIndex) -> {
                game.player.pokemonActifIndex = newPokemonIndex;
                String message = "Go ! " + game.player.getActivePokemon().nom + " !";

                ScreenManager.displayMessage(game, message, () -> {
                    // Retour au choix d'action pour le nouveau tour
                    ScreenManager.displayBattleChooseAction(game);
                });
            });
        }
    }

    private static boolean applyActionLogic(Game game, Action action) {
        if (action instanceof Attaquer) {
            Player attacker = action.player;
            Player defender = (attacker == game.player) ? game.bot : game.player;

            // Calcul des dégâts
            int damage = attacker.getActivePokemon().calculateDamage(
                    defender.getActivePokemon(),
                    action.valueId
            );
            defender.getActivePokemon().pvRestant -= damage;

            String attackName = attacker.getActivePokemon().getAttackNameById(action.valueId);
            action.description = attacker.nom + " utilise " + attackName + " !";

            return defender.getActivePokemon().pvRestant > 0;

        } else if (action instanceof Echanger) {
            action.player.pokemonActifIndex = action.valueId;
            action.description = action.player.nom + " échange son Pokémon.";
            return true;
        }

        throw new RuntimeException("Action inconnue");
    }

    private static void handleBattleEnd(Game game, boolean playerWon) {
        if (playerWon) {
            System.out.println("Félicitations ! Vous avez gagné le combat !");
            // ScreenManager.displayVictoryScreen(game);
        } else {
            System.out.println("Vous avez perdu le combat. Bonne chance pour la prochaine fois !");
            // ScreenManager.displayDefeatScreen(game);
        }
    }
}
