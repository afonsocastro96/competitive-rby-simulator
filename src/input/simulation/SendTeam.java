package input.simulation;

import logic.database.Moves;
import logic.database.Pokemons;
import logic.things.Pokemon;
import logic.things.PokemonBuilder;

public class SendTeam {
    public static Pokemon[] sendPlayer1Team() {
        Pokemon[] team = new Pokemon[1];
        PokemonBuilder starmie = Pokemons.getPokemon("Starmie");
        starmie.setNewMove(Moves.getMove("Thunder Wave"));
        starmie.setNewMove(Moves.getMove("Surf"));
        starmie.setNewMove(Moves.getMove("Recover"));
        starmie.setNewMove(Moves.getMove("Thunderbolt"));
        team[0] = starmie.build();

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
        PokemonBuilder alakazam = Pokemons.getPokemon("Alakazam");
        alakazam.setNewMove(Moves.getMove("Thunder Wave"));
        alakazam.setNewMove(Moves.getMove("Psychic"));
        alakazam.setNewMove(Moves.getMove("Seismic Toss"));
        alakazam.setNewMove(Moves.getMove("Recover"));
        team[0] = alakazam.build();

        /*PokemonBuilder tauros = Pokemons.getPokemon("Tauros");
        tauros.setNewMove(Moves.getMove("Body Slam"));
        tauros.setNewMove(Moves.getMove("Earthquake"));
        tauros.setNewMove(Moves.getMove("Blizzard"));
        tauros.setNewMove(Moves.getMove("Hyper Beam"));
        team[1] = tauros.build();*/

        return team;
    }
}
