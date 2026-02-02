package fr.pokeduel;

import fr.pokeduel.data.DataLoader;
import fr.pokeduel.data.entity.Attaque;
import fr.pokeduel.data.entity.Pokemon;
import fr.pokeduel.data.entity.Type;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonLoaderTest {

    @Test
    void testLoadPokemons() throws Exception {
        DataLoader<Pokemon> loader = new DataLoader<>(Pokemon.class);
        List<Pokemon> result = loader.loadList();

        assertNotNull(result, "La liste ne devrait pas être nulle");
        assertEquals(1024, result.size(), "La liste devrait contenir 2 Pokémon");

        Pokemon bulba = result.stream()
                .filter(p -> p.id == 1)
                .findFirst()
                .orElseThrow();

        assertEquals("bulbasaur", bulba.nom);
        assertEquals(45, bulba.stats.pv);
        assertTrue(bulba.types.contains(12));

        System.out.println("Test réussi : " + result.size() + " Pokémon chargés correctement !");
    }

    @Test
    void testLoadAttaques() throws Exception {
        DataLoader<Attaque> loader = new DataLoader<>(Attaque.class);
        List<Attaque> result = loader.loadList();

        assertNotNull(result, "La liste ne devrait pas être nulle");
        assertEquals(919, result.size(), "La liste devrait contenir 2 Attaques");

        Attaque pound = result.stream()
                .filter(p -> p.id == 1)
                .findFirst()
                .orElseThrow();

        assertEquals("pound", pound.name);
        assertEquals(40, pound.power);
        assertEquals(1, pound.typeId);

        System.out.println("Test réussi : " + result.size() + " Attaques chargés correctement !");
    }

    @Test
    void testLoadType() throws Exception {
        DataLoader<Type> loader = new DataLoader<>(Type.class);
        List<Type> result = loader.loadList();

        assertNotNull(result, "La liste ne devrait pas être nulle");
        assertEquals(18, result.size(), "La liste devrait contenir 2 Types");

        Type normal = result.stream()
                .filter(p -> p.id == 1)
                .findFirst()
                .orElseThrow();

        assertEquals("normal", normal.name);
        assertEquals(1, normal.id);
        assertTrue(normal.double_damage_from.length > 0);

        System.out.println("Test réussi : " + result.size() + " Types chargés correctement !");
    }
}