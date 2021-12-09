package logic.database;

import logic.database.effects.*;
import logic.things.Move;
import logic.things.Type;

import java.util.HashMap;

public class Moves {
    public static HashMap<String, Move> moves = new HashMap<>();

    public static void initialize() {
        Move agility = new Move("Agility", 48, Type.PSYCHIC, 0, 100, new SpeedSharpRaiseEffect());
        Move blizzard = new Move("Blizzard", 8, Type.ICE, 120, 90, new FreezeEffectTen());
        Move bodyslam = new Move("Body Slam", 24, Type.NORMAL, 85, 100, new ParalyzeEffectThirty());
        Move bubblebeam = new Move("Bubblebeam", 32, Type.WATER, 65, 100, new SpeedDropEffectThirtyThree());
        Move confusiondamage = new Move("Confusion Damage", 1, Type.TYPELESS, 40, 100, new NoEffect());
        Move drillpeck = new Move("Drill Peck", 32, Type.FLYING, 80, 100, new NoEffect());
        Move earthquake = new Move("Earthquake", 16, Type.GROUND, 100, 100, new NoEffect());
        Move explosion = new Move("Explosion", 8, Type.NORMAL, 340, 100, new ExplodeEffect()); // Virtually the same, coded like this for simplicity
        Move fireblast = new Move("Fire Blast", 8, Type.FIRE, 120, 85, new BurnEffectThirty());
        Move hypnosis = new Move("Hypnosis", 32, Type.FIRE, 0, 60, new SleepEffect());
        Move icebeam = new Move("Ice Beam", 16, Type.ICE, 95, 100, new FreezeEffectTen());
        Move lightscreen = new Move("Light Screen", 48, Type.FIRE, 0, 100, new LightScreenEffect());
        Move lovelykiss = new Move("Lovely Kiss", 16, Type.NORMAL, 0, 75, new SleepEffect());
        Move megadrain = new Move("Mega Drain", 16, Type.GRASS, 40, 100, new DrainEffect());
        Move hyperbeam = new Move("Hyper Beam", 8, Type.NORMAL, 150, 90, new HBRechargeEffect());
        Move psychic = new Move("Psychic", 16, Type.PSYCHIC, 90, 100, new SpecialDropEffectThirty());
        Move recover = new Move("Recover", 32, Type.NORMAL, 0, 100, new RecoverEffect());
        Move reflect = new Move("Reflect", 32, Type.NORMAL, 0, 100, new ReflectEffect());
        Move rest = new Move("Rest", 16, Type.PSYCHIC, 0, 100, new RecoverEffect());
        Move seismictoss = new Move("Seismic Toss", 32, Type.FIGHTING, 1, 100, new NoEffect());
        Move selfdestruct = new Move("Selfdestruct", 8, Type.NORMAL, 260, 100, new ExplodeEffect()); // Virtually the same, coded like this for simplicity
        Move slash = new Move("Slash", 32, Type.NORMAL, 70, 100, new NoEffect(), true);
        Move sleeppowder = new Move("Sleep Powder", 24, Type.GRASS, 0, 75, new SleepEffect());
        Move sing = new Move("Sing", 24, Type.NORMAL, 0, 55, new SleepEffect());
        Move struggle = new Move("Struggle", 256, Type.NORMAL, 50, 0, new StruggleRecoilEffect());
        Move stunspore = new Move("Stun Spore", 48, Type.GRASS, 0, 75, new ParalyzeEffect());
        Move swordsdance = new Move("Swords Dance", 48, Type.NORMAL, 0, 100, new AttackSharpRaiseEffect());
        Move surf = new Move("Surf", 24, Type.WATER, 95, 100, new NoEffect());
        Move thunderbolt = new Move("Thunderbolt", 24, Type.ELECTRIC, 95, 100, new ParalyzeEffectTen());
        Move thunderwave = new Move("Thunder Wave", 32, Type.ELECTRIC, 0, 100, new ParalyzeEffect());
        moves.put("Agility", agility);
        moves.put("Blizzard", blizzard);
        moves.put("Body Slam", bodyslam);
        moves.put("Bubblebeam", bubblebeam);
        moves.put("Confusion Damage", confusiondamage);
        moves.put("Drill Peck", drillpeck);
        moves.put("Earthquake", earthquake);
        moves.put("Explosion", explosion);
        moves.put("Fire Blast", fireblast);
        moves.put("Hypnosis", hypnosis);
        moves.put("Ice Beam", icebeam);
        moves.put("Light Screen", lightscreen);
        moves.put("Lovely Kiss", lovelykiss);
        moves.put("Mega Drain", megadrain);
        moves.put("Hyper Beam", hyperbeam);
        moves.put("Psychic", psychic);
        moves.put("Recover", recover);
        moves.put("Reflect", reflect);
        moves.put("Rest", rest);
        moves.put("Seismic Toss", seismictoss);
        moves.put("Selfdestruct", selfdestruct);
        moves.put("Sing", sing);
        moves.put("Slash", slash);
        moves.put("Sleep Powder", sleeppowder);
        moves.put("Struggle", struggle);
        moves.put("Stun Spore", stunspore);
        moves.put("Surf", surf);
        moves.put("Swords Dance", swordsdance);
        moves.put("Thunder Wave", thunderwave);
        moves.put("Thunderbolt", thunderbolt);
    }

    public static Move getMove(String name) {
        return moves.get(name);
    }
}
