package fr.pokeduel.pokemon;

import fr.pokeduel.data.entity.AttaqueEntity;

import java.lang.reflect.Type;

public class Attaque extends AttaqueEntity {
   private int ppRestant;


   public Attaque(String name, Type type, int power, int accuracy, int pp, String effect) {
      super(name, type, power, accuracy, pp, effect);
      this.ppRestant = pp;
   }

   public static Attaque getAttaqueById(int id) {
      return null;
   }
}