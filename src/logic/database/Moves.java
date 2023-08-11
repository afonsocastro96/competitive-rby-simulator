package logic.database;

import logic.database.effects.*;
import logic.things.Move;
import logic.things.Type;

import java.util.HashMap;

public class Moves {
    public static HashMap<String, Move> moves = new HashMap<>();

    public static void initialize() {
        moves.put("Agility", new Move("Agility", 48, Type.PSYCHIC, 0, 100, new SpeedSharpRaiseEffect()));
        moves.put("Amnesia", new Move("Amnesia", 32, Type.PSYCHIC, 0, 100, new SpecialSharpRaiseEffect()));
        moves.put("Bite", new Move("Bite", 40, Type.NORMAL, 60,100, new FlinchEffectTen()));
        moves.put("Blizzard", new Move("Blizzard", 8, Type.ICE, 120, 90, new FreezeEffectTen()));
        moves.put("Body Slam", new Move("Body Slam", 24, Type.NORMAL, 85, 100, new ParalyzeEffectThirty()));
        moves.put("Bone Club", new Move("Bone Club", 32, Type.GROUND, 65, 85, new FlinchEffectTen()));
        moves.put("Bubblebeam", new Move("Bubblebeam", 32, Type.WATER, 65, 100, new SpeedDropEffectThirtyThree()));
        moves.put("Comet Punch", new Move("Comet Punch", 24, Type.NORMAL, 18, 85, new TwoToFiveHitsEffect()));
        moves.put("Confuse Ray", new Move("Confuse Ray", 16, Type.GHOST, 0, 100, new ConfuseEffect()));
        moves.put("Confusion Damage", new Move("Confusion Damage", 1, Type.TYPELESS, 40, 100, new NoEffect()));
        moves.put("Crabhammer", new Move("Crabhammer", 16, Type.WATER, 90, 85, new HighCritRateEffect()));
        moves.put("Double Edge", new Move("Double-Edge", 24, Type.NORMAL, 100, 100, new RecoilEffect()));
        moves.put("Doubleslap", new Move("Doubleslap", 16, Type.NORMAL, 15, 85, new TwoToFiveHitsEffect()));
        moves.put("Double Kick", new Move("Double Kick", 48, Type.FIGHTING, 30, 100, new TwoHitsEffect()));
        moves.put("Drill Peck", new Move("Drill Peck", 32, Type.FLYING, 80, 100, new NoEffect()));
        moves.put("Earthquake", new Move("Earthquake", 16, Type.GROUND, 100, 100, new NoEffect()));
        moves.put("Explosion", new Move("Explosion", 8, Type.NORMAL, 340, 100, new ExplodeEffect())); // Virtually the same, coded like this for simplicity
        moves.put("Fire Blast", new Move("Fire Blast", 8, Type.FIRE, 120, 85, new BurnEffectThirty()));
        moves.put("Fire Punch", new Move("Fire Punch", 24, Type.FIRE, 75, 100, new BurnEffectTen()));
        moves.put("Flamethrower", new Move("Flamethrower", 16, Type.FIRE, 95, 100, new BurnEffectTen()));
        moves.put("Fury Attack", new Move("Fury Attack", 32, Type.NORMAL, 15, 85, new TwoToFiveHitsEffect()));
        moves.put("Gust", new Move("Gust", 56, Type.NORMAL, 40, 100, new NoEffect()));
        moves.put("Headbutt", new Move("Headbutt", 24, Type.NORMAL, 70, 100, new FlinchEffectThirty()));
        moves.put("Hi Jump Kick", new Move("Hi Jump Kick", 32, Type.FIGHTING, 85, 90, new NoEffect())); // Recoil effect handled directly in the game engine
        moves.put("Horn Attack", new Move("Horn Attack", 40, Type.NORMAL, 65, 100, new NoEffect()));
        moves.put("Hydro Pump", new Move("Hydro Pump", 8, Type.WATER, 120, 80, new NoEffect()));
        moves.put("Hyper Fang", new Move("Hyper Fang", 24, Type.NORMAL, 80, 90, new FlinchEffectTen()));
        moves.put("Hypnosis", new Move("Hypnosis", 32, Type.PSYCHIC, 0, 60, new SleepEffect()));
        moves.put("Ice Beam", new Move("Ice Beam", 16, Type.ICE, 95, 100, new FreezeEffectTen()));
        moves.put("Ice Punch", new Move("Ice Punch", 24, Type.ICE, 75, 100, new FreezeEffectTen()));
        moves.put("Jump Kick", new Move("Jump Kick", 32, Type.FIGHTING, 70, 95, new NoEffect())); // Recoil effect handled directly in the game engine
        moves.put("Karate Chop", new Move("Karate Chop", 40, Type.NORMAL, 50, 100, new HighCritRateEffect()));
        moves.put("Light Screen", new Move("Light Screen", 48, Type.FIRE, 0, 100, new LightScreenEffect()));
        moves.put("Lovely Kiss", new Move("Lovely Kiss", 16, Type.NORMAL, 0, 75, new SleepEffect()));
        moves.put("Low Kick", new Move("Low Kick", 32, Type.FIGHTING, 50, 90, new FlinchEffectThirty()));
        moves.put("Meditate", new Move("Meditate", 61, Type.PSYCHIC, 0, 100, new AttackRaiseEffect()));
        moves.put("Mega Drain", new Move("Mega Drain", 16, Type.GRASS, 40, 100, new DrainEffect()));
        moves.put("Mega Kick", new Move("Mega Kick", 8, Type.NORMAL, 120, 75, new NoEffect()));
        moves.put("Mega Punch", new Move("Mega Punch", 32, Type.NORMAL, 80, 85, new NoEffect()));
        moves.put("Night Shade", new Move("Night Shade", 24, Type.GHOST, 1, 100, new NoEffect()));  // Handled directly in the damage formula
        moves.put("Hyper Beam", new Move("Hyper Beam", 8, Type.NORMAL, 150, 90, new HBRechargeEffect()));
        moves.put("Pay Day", new Move("Pay Day", 32, Type.NORMAL, 40, 100, new CoinsScatteredEffect()));
        moves.put("Pin Missile", new Move("Pin Missile", 32, Type.BUG, 14, 85, new TwoToFiveHitsEffect()));
        moves.put("Pound", new Move("Pound", 56, Type.NORMAL, 40, 100, new NoEffect()));
        moves.put("Psychic", new Move("Psychic", 16, Type.PSYCHIC, 90, 100, new SpecialDropEffectThirty()));
        moves.put("Razor Leaf", new Move("Razor Leaf", 40, Type.GRASS, 55, 95, new HighCritRateEffect()));
        moves.put("Recover", new Move("Recover", 32, Type.NORMAL, 0, 100, new RecoverEffect()));
        moves.put("Reflect", new Move("Reflect", 32, Type.NORMAL, 0, 100, new ReflectEffect()));
        moves.put("Rest", new Move("Rest", 16, Type.PSYCHIC, 0, 100, new RecoverEffect()));
        moves.put("Rock Slide", new Move("Rock Slide", 16, Type.ROCK, 75, 90, new NoEffect()));
        moves.put("Rolling Kick", new Move("Rolling Kick", 24, Type.FIGHTING, 60, 85, new FlinchEffectThirty()));
        moves.put("Scratch", new Move("Scratch", 56, Type.NORMAL, 40, 100, new NoEffect()));
        moves.put("Seismic Toss", new Move("Seismic Toss", 32, Type.FIGHTING, 1, 100, new NoEffect())); // Handled directly in the damage formula
        moves.put("Selfdestruct", new Move("Selfdestruct", 8, Type.NORMAL, 260, 100, new ExplodeEffect())); // Virtually the same, coded like this for simplicity
        moves.put("Sharpen", new Move("Sharpen", 48, Type.NORMAL, 0, 100, new AttackRaiseEffect()));
        moves.put("Sing", new Move("Sing", 24, Type.NORMAL, 0, 55, new SleepEffect()));
        moves.put("Slam", new Move("Slam", 32, Type.NORMAL, 80, 75, new NoEffect()));
        moves.put("Slash", new Move("Slash", 32, Type.NORMAL, 70, 100, new HighCritRateEffect()));
        moves.put("Sleep Powder", new Move("Sleep Powder", 24, Type.GRASS, 0, 75, new SleepEffect()));
        moves.put("Softboiled", new Move("Softboiled", 16, Type.NORMAL, 0, 100, new RecoverEffect()));
        moves.put("Spore", new Move("Spore", 24, Type.GRASS, 0, 100, new SleepEffect()));
        moves.put("Stomp", new Move("Stomp", 32, Type.NORMAL, 65, 100, new FlinchEffectThirty()));
        moves.put("Struggle", new Move("Struggle", 256, Type.NORMAL, 50, 0, new RecoilEffect()));
        moves.put("Stun Spore", new Move("Stun Spore", 48, Type.GRASS, 0, 75, new ParalyzeEffect()));
        moves.put("Swords Dance", new Move("Swords Dance", 48, Type.NORMAL, 0, 100, new AttackSharpRaiseEffect()));
        moves.put("Submission", new Move("Submission", 40, Type.FIGHTING, 80, 80, new RecoilEffect()));
        moves.put("Substitute", new Move("Substitute", 16, Type.NORMAL, 0, 100, new SubstituteEffect()));
        moves.put("Super Fang", new Move("Super Fang", 16, Type.NORMAL, 1, 100, new NoEffect())); // Handled directly in the damage formula
        moves.put("Supersonic", new Move("Supersonic", 32, Type.NORMAL, 0, 55, new ConfuseEffect()));
        moves.put("Surf", new Move("Surf", 24, Type.WATER, 95, 100, new NoEffect()));
        moves.put("Tackle", new Move("Tackle", 56, Type.NORMAL, 35, 100, new NoEffect()));
        moves.put("Take Down", new Move("Take Down", 32, Type.NORMAL, 90, 85, new RecoilEffect()));
        moves.put("Tail Whip", new Move("Tail Whip", 48, Type.NORMAL, 0, 100, new NoEffect()));
        moves.put("Thunder", new Move("Thunder", 16, Type.ELECTRIC, 20, 70, new ParalyzeEffectTen()));
        moves.put("Thunderbolt", new Move("Thunderbolt", 24, Type.ELECTRIC, 95, 100, new ParalyzeEffectTen()));
        moves.put("Thunderpunch", new Move("Thunderpunch", 24, Type.ELECTRIC, 75, 100, new ParalyzeEffectTen()));
        moves.put("Thunder Wave", new Move("Thunder Wave", 32, Type.ELECTRIC, 0, 100, new ParalyzeEffect()));
        moves.put("Toxic", new Move("Toxic", 16, Type.POISON, 0, 85, new BadPoisonEffect()));
        moves.put("Tri Attack", new Move("Tri Attack", 16, Type.NORMAL, 80, 100, new NoEffect()));
        moves.put("Vicegrip", new Move("Vicegrip", 48, Type.NORMAL, 55, 100, new NoEffect()));
        moves.put("Vine Whip", new Move("Vine Whip", 16, Type.GRASS, 35, 100, new NoEffect()));
        moves.put("Wing Attack", new Move("Wing Attack", 56, Type.FLYING, 35, 100, new NoEffect()));
    }

    public static Move getMove(String name) {
        return moves.get(name);
    }
}
