package fr.pokeduel.game;

import fr.pokeduel.bot.SimpleBot;
import fr.pokeduel.data.DataLoader;
import fr.pokeduel.entity.Attaque;
import fr.pokeduel.entity.Pokemon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public ArrayList<Player> players = new ArrayList<Player>();
    public int curentIndexPlayer;
    public Stage stage;

    public Game() {
        players.add(new Player("Joueur 1", true));
        players.add(new SimpleBot("Bot"));
        curentIndexPlayer = 0;

        for (int i = 0; i < 6; i++) {
            for (Player player : players) {
                Pokemon pokemon = getRandomPokemon();
                player.addPokemon(pokemon);
            }
        }
    }

    public void switchPlayer() {
        curentIndexPlayer = (curentIndexPlayer + 1) % players.size();
    }

    public void resetGame() {
        curentIndexPlayer = 0;
        for (Player player : players) {
            player.reset();
        }
    }

    private Pokemon getRandomPokemon() {
        DataLoader<Pokemon> dl = new DataLoader<Pokemon>(Pokemon.class);
        Random rand = new Random();
        int randomId = rand.nextInt(1024) + 1; // IDs from 1 to 151
        Pokemon pokemon = dl.loadById(randomId);
        for (int i = 0; i < rand.nextInt(4) + 1; i++) {
            pokemon.attaques.add(getRandomAttaque(pokemon));
        }
        return pokemon;
    }

    private Attaque getRandomAttaque(Pokemon p) {
        DataLoader<Attaque> dl = new DataLoader<Attaque>(Attaque.class);
        Random rand = new Random();
        int randomId = rand.nextInt(p.attaquesApprenables.size());
        Attaque attaque = dl.loadById(randomId);
        return attaque;
    }
}


