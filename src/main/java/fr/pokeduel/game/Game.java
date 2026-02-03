package fr.pokeduel.game;

import fr.pokeduel.bot.Bot;
import fr.pokeduel.bot.SimpleBot;
import fr.pokeduel.data.DataLoader;
import fr.pokeduel.entity.Attaque;
import fr.pokeduel.entity.Pokemon;
import javafx.stage.Stage;

import java.util.Random;

public class Game {
    public Player player;
    public Bot bot;
    public Stage stage;

    public Game() {
        this.player = new Player("Joueur 1", true);
        this.bot = new SimpleBot("Bot");

        for (int i = 0; i < 6; i++) {
            this.player.addPokemon(getRandomPokemon());
            this.bot.addPokemon(getRandomPokemon());
        }
    }


    public void resetGame() {
        this.player.reset();
        this.bot.reset();
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


