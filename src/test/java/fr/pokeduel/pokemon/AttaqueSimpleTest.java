package fr.pokeduel.pokemon;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttaqueSimpleTest {

    // Classe de test minimale pour instancier des Pokémons
    static class TestPokemon extends Pokemon {
        public TestPokemon(String nom, int pvMax, List<TypePokemon> types) {
            super(nom, pvMax, types);
        }

        @Override
        public TypePokemon getType() {
            return types.isEmpty() ? null : types.get(0);
        }

        @Override
        public int getDegats() {
            return 0;
        }

        @Override
        public void appliquer(Pokemon attaquant, Pokemon cible) {
            // Pas utilisé dans ce test
        }
    }

    @Test
    void attaqueSimpleReduitPv() {
        TestPokemon cible = new TestPokemon("Cible", 100, List.of());
        AttaqueSimple atk = new AttaqueSimple("Punch", null, 30);

        assertEquals(100, cible.getPv());
        atk.appliquer(null, cible);
        assertEquals(70, cible.getPv());
    }

    @Test
    void pvNeTombePasNegatif() {
        TestPokemon cible = new TestPokemon("Cible", 10, List.of());
        AttaqueSimple atk = new AttaqueSimple("Super", null, 50);

        atk.appliquer(null, cible);
        assertEquals(0, cible.getPv());
    }
}
