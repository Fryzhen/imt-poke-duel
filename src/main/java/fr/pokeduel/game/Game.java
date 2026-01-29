package fr.pokeduel.game;

public class Game {
    public Player player1;
    public Player player2;
    public Player curentPlayer;

    public Game(String username) {
        player1 = new Player(username, true);
        player2 = new Player("Bot", false);
        curentPlayer = player1;
    }

    public void switchPlayer() {
        curentPlayer = (curentPlayer == player2) ? player1 : player2;
    }


}
