package input.simulation;

import logic.database.Moves;
import logic.database.Pokemons;
import logic.things.Pokemon;
import logic.things.PokemonBuilder;

public class SendTeam {
    public static Pokemon[] sendPlayer1Team() {
        Pokemon[] team = new Pokemon[1];
        PokemonBuilder snorlax = Pokemons.getPokemon("Snorlax");
        snorlax.setNewMove(Moves.getMove("Body Slam"));
        snorlax.setNewMove(Moves.getMove("Reflect"));
        snorlax.setNewMove(Moves.getMove("Rest"));
        snorlax.setNewMove(Moves.getMove("Selfdestruct"));
        team[0] = snorlax.build();

        /*PokemonBuilder tauros = Pokemons.getPokemon("Tauros");
        tauros.setNewMove(Moves.getMove("Body Slam"));
        tauros.setNewMove(Moves.getMove("Earthquake"));
        tauros.setNewMove(Moves.getMove("Blizzard"));
        tauros.setNewMove(Moves.getMove("Hyper Beam"));
        team[1] = tauros.build();*/

        return team;
    }

    public static Pokemon[] sendPlayer2Team() {
        Pokemon[] team = new Pokemon[1];
        /*PokemonBuilder alakazam = Pokemons.getPokemon("Alakazam");
        alakazam.setNewMove(Moves.getMove("Thunder Wave"));
        alakazam.setNewMove(Moves.getMove("Psychic"));
        alakazam.setNewMove(Moves.getMove("Seismic Toss"));
        alakazam.setNewMove(Moves.getMove("Recover"));
        team[0] = alakazam.build();*/

        PokemonBuilder tauros = Pokemons.getPokemon("Tauros");
        tauros.setNewMove(Moves.getMove("Body Slam"));
        tauros.setNewMove(Moves.getMove("Earthquake"));
        tauros.setNewMove(Moves.getMove("Blizzard"));
        tauros.setNewMove(Moves.getMove("Hyper Beam"));
        team[0] = tauros.build();

        return team;
    }
}
