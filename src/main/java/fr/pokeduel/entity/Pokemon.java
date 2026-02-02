package fr.pokeduel.entity;

import fr.pokeduel.data.DataLoader;
import fr.pokeduel.exceptions.AttaqueNonApprenableException;
import fr.pokeduel.exceptions.MaximumAttaqueAppriseException;

import java.util.ArrayList;
import java.util.List;

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
}
