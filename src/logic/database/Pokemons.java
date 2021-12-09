package logic.database;

import logic.things.PokemonBuilder;
import logic.things.Type;

import java.util.HashMap;

public class Pokemons {
    public static HashMap<String, PokemonBuilder> pokemons = new HashMap<>();

    public static void initialize() {
        PokemonBuilder alakazam = new PokemonBuilder("Alakazam", Type.PSYCHIC, Type.PSYCHIC, 55, 50, 45, 135, 120);
        PokemonBuilder dodrio = new PokemonBuilder("Dodrio", Type.NORMAL, Type.FLYING, 60, 110, 70, 60, 110);
        PokemonBuilder jolteon = new PokemonBuilder("Jolteon", Type.ELECTRIC, Type.ELECTRIC, 65, 65, 60, 110, 130);
        PokemonBuilder lapras = new PokemonBuilder("Lapras", Type.WATER, Type.ICE, 130, 85, 80, 95, 60);
        PokemonBuilder scyther = new PokemonBuilder("Scyther", Type.BUG, Type.FLYING, 70, 110, 80, 55, 105);
        PokemonBuilder snorlax = new PokemonBuilder("Snorlax", Type.NORMAL, Type.NORMAL, 160, 110, 65, 65, 30);
        PokemonBuilder starmie = new PokemonBuilder("Starmie", Type.WATER, Type.PSYCHIC, 60, 75, 85, 100, 115);
        PokemonBuilder tauros = new PokemonBuilder("Tauros", Type.NORMAL, Type.NORMAL, 75, 100, 95, 70, 110);
        PokemonBuilder zapdos = new PokemonBuilder("Zapdos", Type.ELECTRIC, Type.FLYING, 90, 90, 85, 125, 100);
        pokemons.put("Alakazam", alakazam);
        pokemons.put("Dodrio", dodrio);
        pokemons.put("Jolteon", jolteon);
        pokemons.put("Lapras", lapras);
        pokemons.put("Scyther", scyther);
        pokemons.put("Snorlax", snorlax);
        pokemons.put("Starmie", starmie);
        pokemons.put("Tauros", tauros);
        pokemons.put("Zapdos", zapdos);
    }

    public static PokemonBuilder getPokemon(String name) {
        return pokemons.get(name);
    }
}
