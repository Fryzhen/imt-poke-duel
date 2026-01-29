package fr.pokeduel.pokemon.type;

import fr.pokeduel.pokemon.TypePokemon;

import java.util.EnumMap;
import java.util.Map;

public final class TypeEffectivite {

    private static final Map<TypePokemon, Map<TypePokemon, Double>> CHART = new EnumMap<>(TypePokemon.class);

    static {
        // initialiser toutes les maps
        for (TypePokemon t : TypePokemon.values()) {
            CHART.put(t, new EnumMap<>(TypePokemon.class));
        }

        // par défaut 1.0
        for (TypePokemon atk : TypePokemon.values()) {
            for (TypePokemon def : TypePokemon.values()) {
                CHART.get(atk).put(def, 1.0);
            }
        }

        // Règles spécifiées
        CHART.get(TypePokemon.FEU).put(TypePokemon.PLANTE, 2.0);
        CHART.get(TypePokemon.EAU).put(TypePokemon.FEU, 2.0);
        CHART.get(TypePokemon.PLANTE).put(TypePokemon.EAU, 2.0);
        CHART.get(TypePokemon.ELECTRIK).put(TypePokemon.EAU, 2.0);

        // SOL immunisé à ELECTRIK (0.0) => ELECTRIK vs SOL = 0
        CHART.get(TypePokemon.ELECTRIK).put(TypePokemon.SOL, 0.0);
    }

    private TypeEffectivite() {
        // utilitaire statique
    }

    public static double getMultiplicateur(TypePokemon attaque, TypePokemon defense) {
        if (attaque == null || defense == null) return 1.0;
        Map<TypePokemon, Double> inner = CHART.get(attaque);
        if (inner == null) return 1.0;
        return inner.getOrDefault(defense, 1.0);
    }
}
