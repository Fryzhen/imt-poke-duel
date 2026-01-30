package fr.pokeduel;

import fr.pokeduel.data.PokeDataClient;
import fr.pokeduel.game.Game;
import fr.pokeduel.pokemon.Pokemon;
import fr.pokeduel.ui.MenuUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MenuUI.menuScene(new Game());
    }

    public static void main(String[] args) {
//        launch(args);
        PokeDataClient client = new PokeDataClient();
        List<Pokemon> pokemons = client.getPokemonList();

        System.out.println(pokemons.get(0).getNom());
        System.out.println(pokemons.get(0).getStats().pv);
        System.out.println(pokemons.get(0).getTypes().get(0).toString());

    }
}