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

        public TypePokemon getType() {
            return types.isEmpty() ? null : types.get(0);
        }

        @Override
        public Attaque getAttaqueParDefaut() {
            return null; // pas utilisé dans ces tests
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

    @Test
    void multiplicateurTypeEstApplique() {
        TestPokemon cible = new TestPokemon("Bulbizarre", 100, List.of(TypePokemon.PLANTE));
        AttaqueSimple atkFeu = new AttaqueSimple("Flamme", TypePokemon.FEU, 20);

        // FEU > PLANTE, multiplicateur 2.0 => 20 * 2 = 40
        atkFeu.appliquer(null, cible);
        assertEquals(60, cible.getPv());
    }
}
