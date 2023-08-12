package logic.database;

import logic.database.effects.*;
import logic.things.Move;
import logic.things.Type;

import java.util.HashMap;

public class Moves {
    public static HashMap<String, Move> moves = new HashMap<>();

    public static void initialize() {
        /* TODO:
        // - Partial Trapping Moves: Bind, Fire Spin, Clamp, Wrap
        // - Rampage moves: Petal Dance and Thrash
        // - Miscellaneous moves: Bide, Counter, Disable, Metronome, Mimic, Mirror Move, Rage, Transform */

        moves.put("Absorb", new Move("Absorb", 32, Type.GRASS, 20, 100, new DrainEffect()));
        moves.put("Avid", new Move("Acid", 48, Type.POISON, 40, 100, new DefenceDropEffectThirtyThree()));
        moves.put("Acid Armor", new Move("Acid Armor", 61, Type.POISON, 0, -1, new DefenceSharpRaiseEffect()));
        moves.put("Agility", new Move("Agility", 48, Type.PSYCHIC, 0, -1, new SpeedSharpRaiseEffect()));
        moves.put("Amnesia", new Move("Amnesia", 32, Type.PSYCHIC, 0, -1, new SpecialSharpRaiseEffect()));
        moves.put("Aurora Beam", new Move("Aurora Beam", 32, Type.ICE, 65, 100, new AttackDropEffectThirtyThree()));
        moves.put("Barrage", new Move("Barrage", 32, Type.PSYCHIC, 15, 85, new TwoToFiveHitsEffect()));
        moves.put("Barrier", new Move("Barrier", 48, Type.PSYCHIC, 0, -1, new DefenceSharpRaiseEffect()));
        moves.put("Bite", new Move("Bite", 40, Type.NORMAL, 60,100, new FlinchEffectTen()));
        moves.put("Blizzard", new Move("Blizzard", 8, Type.ICE, 120, 90, new FreezeEffectTen()));
        moves.put("Body Slam", new Move("Body Slam", 24, Type.NORMAL, 85, 100, new ParalyzeEffectThirty()));
        moves.put("Bone Club", new Move("Bone Club", 32, Type.GROUND, 65, 85, new FlinchEffectTen()));
        moves.put("Bonemerang", new Move("Bonemerang", 16, Type.GROUND, 50, 90, new TwoHitsEffect()));
        moves.put("Bubble", new Move("Bubble", 56, Type.WATER, 20, 100, new SpeedDropEffectThirtyThree()));
        moves.put("Bubblebeam", new Move("Bubblebeam", 32, Type.WATER, 65, 100, new SpeedDropEffectThirtyThree()));
        moves.put("Comet Punch", new Move("Comet Punch", 24, Type.NORMAL, 18, 85, new TwoToFiveHitsEffect()));
        moves.put("Confuse Ray", new Move("Confuse Ray", 16, Type.GHOST, 0, 100, new ConfuseEffect()));
        moves.put("Confusion", new Move("Confusion", 40, Type.PSYCHIC, 50, 100, new ConfuseEffectTen()));
        moves.put("Confusion Damage", new Move("Confusion Damage", 1, Type.TYPELESS, 40, -1, new NoEffect()));
        moves.put("Constrict", new Move("Constrict", 56, Type.NORMAL, 10, 100, new SpeedDropEffectThirtyThree()));
        moves.put("Conversion", new Move("Conversion", 48, Type.NORMAL, 0, -1, new ConversionEffect()));
        moves.put("Crabhammer", new Move("Crabhammer", 16, Type.WATER, 90, 85, new HighCritRateEffect()));
        moves.put("Cut", new Move("Cut", 48, Type.NORMAL, 50, 95, new NoEffect()));
        moves.put("Defense Curl", new Move("Defense Curl", 61, Type.NORMAL, 0, -1, new DefenceRaiseEffect()));
        moves.put("Dig", new Move("Dig", 16, Type.GROUND, 100, 100, new ChargeEffect("dug a hole!", true)));
        moves.put("Dizzy Punch", new Move("Dizzy Punch", 16, Type.NORMAL, 70, 100, new NoEffect()));
        moves.put("Doubleslap", new Move("Doubleslap", 16, Type.NORMAL, 15, 85, new TwoToFiveHitsEffect()));
        moves.put("Double Kick", new Move("Double Kick", 48, Type.FIGHTING, 30, 100, new TwoHitsEffect()));
        moves.put("Double Team", new Move("Double Team", 24, Type.NORMAL, 0, -1, new EvasionRaiseEffect()));
        moves.put("Double-Edge", new Move("Double-Edge", 24, Type.NORMAL, 100, 100, new RecoilEffect()));
        moves.put("Dragon Rage", new Move("Dragon Rage", 16, Type.DRAGON, 1, 100, new NoEffect())); // Handled directly in the damage formula
        moves.put("Dream Eater", new Move("Dream Eater", 24, Type.PSYCHIC, 100, 100, new DrainEffect())); // Sleep condition handled directly in the game engine
        moves.put("Drill Peck", new Move("Drill Peck", 32, Type.FLYING, 80, 100, new NoEffect()));
        moves.put("Earthquake", new Move("Earthquake", 16, Type.GROUND, 100, 100, new NoEffect()));
        moves.put("Egg Bomb", new Move("Egg Bomb", 16, Type.NORMAL, 100, 75, new NoEffect()));
        moves.put("Ember", new Move("Ember", 40, Type.FIRE, 40, 100, new BurnEffectTen()));
        moves.put("Explosion", new Move("Explosion", 8, Type.NORMAL, 340, 100, new ExplodeEffect())); // Virtually the same, coded like this for simplicity
        moves.put("Fire Blast", new Move("Fire Blast", 8, Type.FIRE, 120, 85, new BurnEffectThirty()));
        moves.put("Fire Punch", new Move("Fire Punch", 24, Type.FIRE, 75, 100, new BurnEffectTen()));
        moves.put("Fissure", new Move("Fissure", 8, Type.GROUND, 2, 30, new OneHitKOEffect()));
        moves.put("Flamethrower", new Move("Flamethrower", 16, Type.FIRE, 95, 100, new BurnEffectTen()));
        moves.put("Flash", new Move("Flash", 32, Type.NORMAL, 0, 70, new AccuracyDropEffect()));
        moves.put("Fly", new Move("Fly", 24, Type.FLYING, 70, 95, new ChargeEffect("flew up high!", true)));
        moves.put("Focus Energy", new Move("Focus Energy", 48, Type.NORMAL, 0, -1, new FocusEnergyEffect()));
        moves.put("Fury Attack", new Move("Fury Attack", 32, Type.NORMAL, 15, 85, new TwoToFiveHitsEffect()));
        moves.put("Fury Swipes", new Move("Fury Swipes", 24, Type.NORMAL, 18, 80, new TwoToFiveHitsEffect()));
        moves.put("Glare", new Move("Glare", 48, Type.NORMAL, 0, 75, new ParalyzeEffect()));
        moves.put("Growl", new Move("Growl", 61, Type.NORMAL, 0, 100, new AttackDropEffect()));
        moves.put("Growth", new Move("Growth", 61, Type.NORMAL, 0, -1, new SpecialRaiseEffect()));
        moves.put("Guillotine", new Move("Guillotine", 8, Type.NORMAL, 2, 100, new OneHitKOEffect()));
        moves.put("Gust", new Move("Gust", 56, Type.NORMAL, 40, 100, new NoEffect()));
        moves.put("Harden", new Move("Harden", 48, Type.NORMAL, 0, -1, new DefenceRaiseEffect()));
        moves.put("Haze", new Move("Haze", 48, Type.ICE, 0, -1, new HazeEffect()));
        moves.put("Headbutt", new Move("Headbutt", 24, Type.NORMAL, 70, 100, new FlinchEffectThirty()));
        moves.put("Hi Jump Kick", new Move("Hi Jump Kick", 32, Type.FIGHTING, 85, 90, new NoEffect())); // Recoil effect handled directly in the game engine
        moves.put("Horn Attack", new Move("Horn Attack", 40, Type.NORMAL, 65, 100, new NoEffect()));
        moves.put("Horn Drill", new Move("Horn Drill", 8, Type.NORMAL, 2, 30, new OneHitKOEffect()));
        moves.put("Hydro Pump", new Move("Hydro Pump", 8, Type.WATER, 120, 80, new NoEffect()));
        moves.put("Hyper Beam", new Move("Hyper Beam", 8, Type.NORMAL, 150, 90, new HBRechargeEffect()));
        moves.put("Hyper Fang", new Move("Hyper Fang", 24, Type.NORMAL, 80, 90, new FlinchEffectTen()));
        moves.put("Hypnosis", new Move("Hypnosis", 32, Type.PSYCHIC, 0, 60, new SleepEffect()));
        moves.put("Ice Beam", new Move("Ice Beam", 16, Type.ICE, 95, 100, new FreezeEffectTen()));
        moves.put("Ice Punch", new Move("Ice Punch", 24, Type.ICE, 75, 100, new FreezeEffectTen()));
        moves.put("Jump Kick", new Move("Jump Kick", 32, Type.FIGHTING, 70, 95, new NoEffect())); // Recoil effect handled directly in the game engine
        moves.put("Karate Chop", new Move("Karate Chop", 40, Type.NORMAL, 50, 100, new HighCritRateEffect()));
        moves.put("Kinesis", new Move("Kinesis", 24, Type.PSYCHIC, 0, 80, new AccuracyDropEffect()));
        moves.put("Leech Life", new Move("Leech Life", 24, Type.BUG, 20, 100, new DrainEffect()));
        moves.put("Leech Seed", new Move("Leech Seed", 16, Type.GRASS, 0, 90, new LeechSeedEffect()));
        moves.put("Leer", new Move("Leer", 48, Type.NORMAL, 0, 100, new DefenceDropEffect()));
        moves.put("Lick", new Move("Lick", 48, Type.GHOST, 20, 100, new ParalyzeEffectThirty()));
        moves.put("Light Screen", new Move("Light Screen", 48, Type.FIRE, 0, -1, new LightScreenEffect()));
        moves.put("Lovely Kiss", new Move("Lovely Kiss", 16, Type.NORMAL, 0, 75, new SleepEffect()));
        moves.put("Low Kick", new Move("Low Kick", 32, Type.FIGHTING, 50, 90, new FlinchEffectThirty()));
        moves.put("Meditate", new Move("Meditate", 61, Type.PSYCHIC, 0, -1, new AttackRaiseEffect()));
        moves.put("Mega Drain", new Move("Mega Drain", 16, Type.GRASS, 40, 100, new DrainEffect()));
        moves.put("Mega Kick", new Move("Mega Kick", 8, Type.NORMAL, 120, 75, new NoEffect()));
        moves.put("Mega Punch", new Move("Mega Punch", 32, Type.NORMAL, 80, 85, new NoEffect()));
        moves.put("Minimize", new Move("Minimize", 32, Type.NORMAL, 0, -1, new EvasionRaiseEffect()));
        moves.put("Mist", new Move("Mist", 48, Type.ICE, 0, -1, new MistEffect()));
        moves.put("Night Shade", new Move("Night Shade", 24, Type.GHOST, 1, 100, new NoEffect()));  // Handled directly in the damage formula
        moves.put("Pay Day", new Move("Pay Day", 32, Type.NORMAL, 40, 100, new CoinsScatteredEffect()));
        moves.put("Peck", new Move("Peck", 56, Type.FLYING, 35, 100, new NoEffect()));
        moves.put("Pin Missile", new Move("Pin Missile", 32, Type.BUG, 14, 85, new TwoToFiveHitsEffect()));
        moves.put("Poison Gas", new Move("Poison Gas", 61, Type.POISON, 0, 55, new PoisonEffect()));
        moves.put("PoisonPowder", new Move("PoisonPowder", 56, Type.POISON, 0, 75, new PoisonEffect()));
        moves.put("Poison Sting", new Move("Poison Sting", 56, Type.POISON, 15, 100, new PoisonEffectTwenty()));
        moves.put("Pound", new Move("Pound", 56, Type.NORMAL, 40, 100, new NoEffect()));
        moves.put("Psybeam", new Move("Psybeam", 32, Type.PSYCHIC, 65, 100, new ConfuseEffectTen()));
        moves.put("Psychic", new Move("Psychic", 16, Type.PSYCHIC, 90, 100, new SpecialDropEffectThirty()));
        moves.put("Psywave", new Move("Psywave", 24, Type.PSYCHIC, 1, 100, new NoEffect())); //Handled directly in the damage formula
        moves.put("Quick Attack", new Move("Quick Attack", 56, Type.NORMAL, 40, 100, new NoEffect())); //Priority handled directly in the game engine
        moves.put("Razor Leaf", new Move("Razor Leaf", 40, Type.GRASS, 55, 95, new HighCritRateEffect()));
        moves.put("Razor Wind", new Move("Razor Wind", 16,  Type.NORMAL, 80, 75, new ChargeEffect("made a whirlwind!", false)));
        moves.put("Recover", new Move("Recover", 32, Type.NORMAL, 0, -1, new RecoverEffect()));
        moves.put("Reflect", new Move("Reflect", 32, Type.NORMAL, 0, -1, new ReflectEffect()));
        moves.put("Rest", new Move("Rest", 16, Type.PSYCHIC, 0, -1, new RecoverEffect()));
        moves.put("Roar", new Move("Roar", 32, Type.NORMAL, 0, 100, new FailedEffect()));
        moves.put("Rock Slide", new Move("Rock Slide", 16, Type.ROCK, 75, 90, new NoEffect()));
        moves.put("Rock Throw", new Move("Rock Throw", 24, Type.ROCK, 50, 65, new NoEffect()));
        moves.put("Rolling Kick", new Move("Rolling Kick", 24, Type.FIGHTING, 60, 85, new FlinchEffectThirty()));
        moves.put("Sand Attack", new Move("Sand Attack", 24, Type.NORMAL, 0, 100, new AccuracyDropEffect()));
        moves.put("Scratch", new Move("Scratch", 56, Type.NORMAL, 40, 100, new NoEffect()));
        moves.put("Screech", new Move("Screech", 61, Type.NORMAL, 0, 85, new DefenceSharpDropEffect()));
        moves.put("Seismic Toss", new Move("Seismic Toss", 32, Type.FIGHTING, 1, 100, new NoEffect())); // Handled directly in the damage formula
        moves.put("Selfdestruct", new Move("Selfdestruct", 8, Type.NORMAL, 260, 100, new ExplodeEffect())); // Virtually the same, coded like this for simplicity
        moves.put("Sharpen", new Move("Sharpen", 48, Type.NORMAL, 0, -1, new AttackRaiseEffect()));
        moves.put("Sing", new Move("Sing", 24, Type.NORMAL, 0, 55, new SleepEffect()));
        moves.put("Skull Bash", new Move("Skull Bash", 24, Type.NORMAL, 100, 100, new ChargeEffect("lowered its head!", false)));
        moves.put("Sky Attack", new Move("Sky Attack", 8, Type.FLYING, 140, 90, new ChargeEffect("is glowing!", false)));
        moves.put("Slam", new Move("Slam", 32, Type.NORMAL, 80, 75, new NoEffect()));
        moves.put("Slash", new Move("Slash", 32, Type.NORMAL, 70, 100, new HighCritRateEffect()));
        moves.put("Sleep Powder", new Move("Sleep Powder", 24, Type.GRASS, 0, 75, new SleepEffect()));
        moves.put("Sludge", new Move("Sludge", 32, Type.POISON, 65, 100, new PoisonEffectForty()));
        moves.put("Smog", new Move("Smog", 32, Type.POISON, 20, 70, new PoisonEffectForty()));
        moves.put("Smokescreen", new Move("Smokescreen", 32, Type.NORMAL, 0, 100, new AccuracyDropEffect()));
        moves.put("Softboiled", new Move("Softboiled", 16, Type.NORMAL, 0, -1, new RecoverEffect()));
        moves.put("Sonicboom", new Move("Sonicboom", 32, Type.NORMAL, 1, 100, new NoEffect())); // Handled directly in the damage formula
        moves.put("Spike Cannon", new Move("Spike Cannon", 24, Type.NORMAL, 20, 100, new TwoToFiveHitsEffect()));
        moves.put("Splash", new Move("Splash", 61, Type.NORMAL, 0, -1, new SplashEffect()));
        moves.put("Spore", new Move("Spore", 24, Type.GRASS, 0, 100, new SleepEffect()));
        moves.put("Strength", new Move("Strength", 24, Type.NORMAL, 80, 100, new NoEffect()));
        moves.put("Stomp", new Move("Stomp", 32, Type.NORMAL, 65, 100, new FlinchEffectThirty()));
        moves.put("String Shot", new Move("String Shot", 61, Type.BUG, 0, 95, new SpeedDropEffect()));
        moves.put("Struggle", new Move("Struggle", 256, Type.NORMAL, 50, 100, new RecoilEffect()));
        moves.put("Stun Spore", new Move("Stun Spore", 48, Type.GRASS, 0, 75, new ParalyzeEffect()));
        moves.put("Submission", new Move("Submission", 40, Type.FIGHTING, 80, 80, new RecoilEffect()));
        moves.put("Substitute", new Move("Substitute", 16, Type.NORMAL, 0, -1, new SubstituteEffect()));
        moves.put("Super Fang", new Move("Super Fang", 16, Type.NORMAL, 1, 100, new NoEffect())); // Handled directly in the damage formula
        moves.put("Supersonic", new Move("Supersonic", 32, Type.NORMAL, 0, 55, new ConfuseEffect()));
        moves.put("Surf", new Move("Surf", 24, Type.WATER, 95, 100, new NoEffect()));
        moves.put("Swift", new Move("Swift", 32, Type.NORMAL, 60, -1, new NoEffect()));
        moves.put("Swords Dance", new Move("Swords Dance", 48, Type.NORMAL, 0, 100, new AttackSharpRaiseEffect()));
        moves.put("Tackle", new Move("Tackle", 56, Type.NORMAL, 35, 100, new NoEffect()));
        moves.put("Tail Whip", new Move("Tail Whip", 48, Type.NORMAL, 0, -1, new DefenceDropEffect()));
        moves.put("Take Down", new Move("Take Down", 32, Type.NORMAL, 90, 85, new RecoilEffect()));
        moves.put("Teleport", new Move("Teleport", 32, Type.PSYCHIC, 0, -1, new FailedEffect()));
        moves.put("Thunder", new Move("Thunder", 16, Type.ELECTRIC, 20, 70, new ParalyzeEffectTen()));
        moves.put("Thunder Wave", new Move("Thunder Wave", 32, Type.ELECTRIC, 0, 100, new ParalyzeEffect()));
        moves.put("Thunderbolt", new Move("Thunderbolt", 24, Type.ELECTRIC, 95, 100, new ParalyzeEffectTen()));
        moves.put("Thunderpunch", new Move("Thunderpunch", 24, Type.ELECTRIC, 75, 100, new ParalyzeEffectTen()));
        moves.put("Thundershock", new Move("Thundershock", 48, Type.ELECTRIC, 40, 100, new ParalyzeEffectTen()));
        moves.put("Toxic", new Move("Toxic", 16, Type.POISON, 0, 85, new BadPoisonEffect()));
        moves.put("Tri Attack", new Move("Tri Attack", 16, Type.NORMAL, 80, 100, new NoEffect()));
        moves.put("Twineedle", new Move("Twinneedle", 32, Type.BUG, 25, 100, new TwinneedleEffect()));
        moves.put("Vicegrip", new Move("Vicegrip", 48, Type.NORMAL, 55, 100, new NoEffect()));
        moves.put("Vine Whip", new Move("Vine Whip", 16, Type.GRASS, 35, 100, new NoEffect()));
        moves.put("Water Gun", new Move("Water Gun", 40, Type.WATER, 40, 100, new NoEffect()));
        moves.put("Waterfall", new Move("Waterfall", 24, Type.WATER, 80, 100, new NoEffect()));
        moves.put("Whirlwind", new Move("Whirlwind", 32, Type.NORMAL, 0, 85, new FailedEffect()));
        moves.put("Wing Attack", new Move("Wing Attack", 56, Type.FLYING, 35, 100, new NoEffect()));
        moves.put("Withdraw", new Move("Withdraw", 61, Type.WATER, 0, -1, new DefenceRaiseEffect()));
    }

    public static Move getMove(String name) {
        return moves.get(name);
    }
}
