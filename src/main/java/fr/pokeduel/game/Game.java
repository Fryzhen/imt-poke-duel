package fr.pokeduel.game;

import fr.pokeduel.data.DataLoader;
import fr.pokeduel.data.entity.Pokemon;

public class Game {
    public Player player1;
    public Player player2;
    public Player curentPlayer;

    public Game() {
        player1 = new Player("Joueur 1", true);
        player2 = new Player("Bot", false);
        DataLoader<Pokemon> dl = new DataLoader<Pokemon>(Pokemon.class);
        player1.pokemons.add(dl.loadById(1));
        player1.pokemons.add(dl.loadById(4));
        player1.pokemons.add(dl.loadById(7));
        player1.pokemons.add(dl.loadById(25));
        curentPlayer = player1;
    }

    public void switchPlayer() {
        curentPlayer = (curentPlayer == player2) ? player1 : player2;
    }


}
