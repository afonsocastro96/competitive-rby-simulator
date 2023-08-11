package logic.database;

import logic.things.PokemonBuilder;
import logic.things.Type;

import java.util.HashMap;

public class Pokemons {
    public static HashMap<String, PokemonBuilder> pokemons = new HashMap<>();

    public static void initialize() {
        pokemons.put("Alakazam", new PokemonBuilder("Alakazam", Type.PSYCHIC, Type.PSYCHIC, 55, 50, 45, 135, 120));
        pokemons.put("Chansey", new PokemonBuilder("Chansey", Type.NORMAL, Type.NORMAL, 250, 5, 5, 105, 50));
        pokemons.put("Dodrio", new PokemonBuilder("Dodrio", Type.NORMAL, Type.FLYING, 60, 110, 70, 60, 110));
        pokemons.put("Golem", new PokemonBuilder("Golem", Type.ROCK, Type.GROUND, 80, 110, 130, 55, 45));
        pokemons.put("Jolteon", new PokemonBuilder("Jolteon", Type.ELECTRIC, Type.ELECTRIC, 65, 65, 60, 110, 130));
        pokemons.put("Lapras", new PokemonBuilder("Lapras", Type.WATER, Type.ICE, 130, 85, 80, 95, 60));
        pokemons.put("Rhydon", new PokemonBuilder("Rhydon", Type.GROUND, Type.ROCK, 105, 130, 120, 45, 40));
        pokemons.put("Scyther", new PokemonBuilder("Scyther", Type.BUG, Type.FLYING, 70, 110, 80, 55, 105));
        pokemons.put("Snorlax", new PokemonBuilder("Snorlax", Type.NORMAL, Type.NORMAL, 160, 110, 65, 65, 30));
        pokemons.put("Starmie", new PokemonBuilder("Starmie", Type.WATER, Type.PSYCHIC, 60, 75, 85, 100, 115));
        pokemons.put("Tauros", new PokemonBuilder("Tauros", Type.NORMAL, Type.NORMAL, 75, 100, 95, 70, 110));
        pokemons.put("Zapdos", new PokemonBuilder("Zapdos", Type.ELECTRIC, Type.FLYING, 90, 90, 85, 125, 100));
    }

    public static PokemonBuilder getPokemon(String name) {
        return pokemons.get(name);
    }
}
