package fr.pokeduel.pokemon;

import java.lang.reflect.Type;

public class Attaque {
   protected int id;
   protected String name;
   protected Type type;
   protected int power;
   protected int accuracy;
   protected int pp;
   protected int ppRestants;
   protected String effect;

   public static Attaque getAttaqueById(int id) {
      return null;
   }
}