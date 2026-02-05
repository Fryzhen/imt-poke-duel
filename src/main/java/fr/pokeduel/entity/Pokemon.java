package fr.pokeduel.entity;

import fr.pokeduel.data.DataLoader;
import fr.pokeduel.exceptions.AttaqueNonApprenableException;
import fr.pokeduel.exceptions.MaximumAttaqueAppriseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Pokemon {
    public int id;
    public int pvRestant;
    public String nom;
    public Stats stats;
    public List<Integer> types;
    public List<Attaque> attaques;
    public List<Integer> attaquesApprenables;
    public String backSprite;
    public String frontSprite;

    public Pokemon() {
        this.attaques = new ArrayList<>();
    }

    public void ajouterAttaque(int attaqueId) throws AttaqueNonApprenableException, MaximumAttaqueAppriseException {
        if (attaques.size() == 4) {
            throw new MaximumAttaqueAppriseException();
        }

        if (!attaquesApprenables.contains(attaqueId)) {
            throw new AttaqueNonApprenableException();
        }

        DataLoader<Attaque> dl = new DataLoader<Attaque>(Attaque.class);
        Attaque attaque = dl.loadById(attaqueId);
        if (!attaques.contains(attaque)) {
            attaques.add(attaque);
        }
    }

    public void supprimerAttaque(Attaque attaque) {
        attaques.remove(attaque);
    }

    public boolean isKO() {
        return this.pvRestant <= 0;
    }

    public void resetPokemon() {
        this.pvRestant = this.stats.pv;
        for (Attaque attaque : attaques) {
            if (attaque != null) {
                attaque.ppRestants = attaque.pp;
            }
        }
    }

    public int calculateDamage(Pokemon defender, int attaqueId) {
        Attaque attaque = attaques.stream().filter(a -> a.id == attaqueId).findFirst().orElse(null);

        if (attaque == null) {
            throw new RuntimeException("Le pokémon " + this.nom + " n'a pas l'attaque avec l'id " + attaqueId);
        }

        int statAttaque = attaque.damageClassId == 2 ? this.stats.attaque : this.stats.attaqueSpe;
        int statDefense = attaque.damageClassId == 2 ? defender.stats.defense : defender.stats.defenseSpe;

        Random random = new Random();
        if (random.nextInt(100) >= attaque.accuracy) {
            return 0;
        }

        int damage = (attaque.power * statAttaque) / (statDefense * 2);

        if (types.contains(attaque.typeId)) {
            damage = (int) (1.5 * damage);
        }

        DataLoader<Type> dlType = new DataLoader<Type>(Type.class);

        Type attaqueType = dlType.loadById(attaque.typeId);
        for (Integer defenderTypeId : defender.types) {
            if (new ArrayList<>(IntStream.of(attaqueType.double_damage_to).boxed().toList()).contains(defenderTypeId)) {
                damage *= 2;
            }
            if (new ArrayList<>(IntStream.of(attaqueType.half_damage_to).boxed().toList()).contains(defenderTypeId)) {
                damage /= 2;
            }
            if (new ArrayList<>(IntStream.of(attaqueType.no_damage_to).boxed().toList()).contains(defenderTypeId)) {
                damage = 0;
            }
        }
        return damage;
    }

    public String getAttackNameById(int attaqueId) {
        Attaque attaque = attaques.stream().filter(a -> a.id == attaqueId).findFirst().orElse(null);
        if (attaque == null) {
            throw new RuntimeException("Le pokémon " + this.nom + " n'a pas l'attaque avec l'id " + attaqueId);
        }
        return attaque.name;
    }
}
