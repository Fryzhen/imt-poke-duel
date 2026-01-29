package fr.pokeduel.pokemon;

public class PokemonKOException extends RuntimeException {
    public PokemonKOException() {
        super();
    }

    public PokemonKOException(String message) {
        super(message);
    }

    public PokemonKOException(String message, Throwable cause) {
        super(message, cause);
    }
}
