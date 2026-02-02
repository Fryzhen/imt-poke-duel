package fr.pokeduel.game;

import fr.pokeduel.data.DataLoader;
import fr.pokeduel.entity.Attaque;
import fr.pokeduel.entity.Pokemon;

import java.util.Random;

public class Game {
    public Player player1;
    public Player player2;
    public Player curentPlayer;

    public Game() {
        player1 = new Player("Joueur 1", true);
        player2 = new Player("Bot", false);
        curentPlayer = player1;

        // Initialisation des pokémons des joueurs
        for (int i = 0; i < 6; i++) {
            player1.addPokemon(getRandomPokemon());
            player2.addPokemon(getRandomPokemon());
        }
    }

    public void switchPlayer() {
        curentPlayer = (curentPlayer == player2) ? player1 : player2;
    }

    public void resetGame() {
        curentPlayer = player1;
        player1.reset();
        player2.reset();
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


