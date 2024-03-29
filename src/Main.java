import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import engine.Board;
import engine.GameEngine;
import input.InputHandler;
import logic.database.Moves;
import logic.database.Pokemons;
import logic.database.effects.FreezeEffectTen;
import output.OutputHandler;

public class Main {
    public static final long NUMBER_OF_SIMULATIONS = 1;

    public static void main(String[] args) {
        Moves.initialize();
        Pokemons.initialize();

        long player1_won = 0;
        long player2_won = 0;
        long tie = 0;
        long turnCount = 0;

        for (long i = 0; i < NUMBER_OF_SIMULATIONS; ++i) {
            if (NUMBER_OF_SIMULATIONS > 10 && i % (NUMBER_OF_SIMULATIONS / 10) == 0)
                System.out.println(i * 100 / NUMBER_OF_SIMULATIONS + "% complete.");
            GameEngine ge = new GameEngine(InputHandler.SIMULATION, OutputHandler.STDOUT);
            switch (ge.game()) {
                case PLAYER1:
                    ++player1_won;
                    break;
                case PLAYER2:
                    ++player2_won;
                    System.out.println(OutputHandler.getBattleLog());
                    break;
                case BOTH:
                    ++tie;
            }
            if(ge.getBoard().getTurn()>2)
                System.out.println(ge.getBoard().getTurn());
            turnCount += ge.getBoard().getTurn()-1;
        }
        System.out.println("Number of times Player 1 (Chansey) won: " + player1_won + " (" + (double) player1_won * 100 / NUMBER_OF_SIMULATIONS + "%)");
        System.out.println("Number of times Player 2 (Golem) won: " + player2_won + " (" + (double) player2_won * 100 / NUMBER_OF_SIMULATIONS + "%)");
        System.out.println("Number of ties: " + tie + " (" + (double) tie * 100 / NUMBER_OF_SIMULATIONS + "%)");
        System.out.println("Average amount of turns: " + ((float)turnCount/NUMBER_OF_SIMULATIONS));

        /*PokemonBuilder pokemonbuilder1 = Pokemons.getPokemon("Zapdos");
        PokemonBuilder pokemonbuilder2 = Pokemons.getPokemon("Starmie");
        Move move = Moves.getMove("Thunderbolt");
        boolean criticalHit = false;

        Pokemon pokemon1 = pokemonbuilder1.build();
        Pokemon pokemon2 = pokemonbuilder2.build();

        int damage = DamageFormula.calcDamage(pokemon1, pokemon2, move, criticalHit);

        System.out.println(pokemon1.getSpecies() + " " + move.getName() + " vs. " + pokemon2.getSpecies() + ": " + damage + "/" + pokemon2.getHp() + " (" + (int)((double)damage / pokemon2.getHp() * 100) + "%)");*/
    }
}
