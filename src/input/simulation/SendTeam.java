package input.simulation;

import logic.database.Moves;
import logic.database.Pokemons;
import logic.database.statuses.ParalysisStatus;
import logic.database.statuses.ReflectStatus;
import logic.things.Pokemon;
import logic.things.PokemonBuilder;

public class SendTeam {
    public static Pokemon[] sendPlayer1Team() {
        Pokemon[] team = new Pokemon[1];

        PokemonBuilder snorlax = Pokemons.getPokemon("Chansey");
        snorlax.setNewMove(Moves.getMove("Thunder Wave"));
        snorlax.setNewMove(Moves.getMove("Seismic Toss"));
        snorlax.setNewMove(Moves.getMove("Reflect"));
        snorlax.setNewMove(Moves.getMove("Softboiled"));
        team[0] = snorlax.build();
        team[0].setStatus(new ParalysisStatus());
        team[0].setParalysisSpeedDropCounter(1);
        team[0].addVolatileStatus(new ReflectStatus());

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

        PokemonBuilder golem = Pokemons.getPokemon("Golem");
        golem.setNewMove(Moves.getMove("Earthquake"));
        golem.setNewMove(Moves.getMove("Rock Slide"));
        golem.setNewMove(Moves.getMove("Explosion"));
        golem.setNewMove(Moves.getMove("Body Slam"));
        team[0] = golem.build();

        return team;
    }
}
