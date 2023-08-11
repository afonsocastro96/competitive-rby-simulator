package logic.things;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokemon {
    public static final int HP = 1;
    public static final int ATTACK = 2;
    public static final int DEFENCE = 3;
    public static final int SPEED = 4;
    public static final int SPECIAL = 5;

    String species;
    Type type1;
    Type type2;
    int level;
    Status status;
    List<Status> volatileStatuses;
    int currentHp;
    int substituteHp;
    int hp;
    int attack;
    int defence;
    int special;
    int speed;
    int attackModifier;
    int defenceModifier;
    int specialModifier;
    int speedModifier;
    int paralysisSpeedDropCounter;
    int burnAttackDropCounter;
    private Move[] moves;
    private int[] moves_pp;

    public Pokemon(String species, Type type1, Type type2, int level, int hp, int attack, int defence, int special, int speed, Move[] moves) {
        this.species = species;
        this.type1 = type1;
        this.type2 = type2;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.special = special;
        this.speed = speed;

        this.moves = moves;
        this.moves_pp = new int[]{moves[0].getMax_pp(), moves[1].getMax_pp(), moves[2].getMax_pp(), moves[3].getMax_pp()};

        attackModifier = 0;
        defenceModifier = 0;
        specialModifier = 0;
        speedModifier = 0;
        currentHp = hp;
        substituteHp = 0;
        volatileStatuses = new ArrayList<>();

        // These two are used to simulate the attack/speed burn/paralysis effect stacking glitch
        paralysisSpeedDropCounter = 0;
        burnAttackDropCounter = 0;

    }

    public String getSpecies() {
        return species;
    }

    public Type getType1() {
        return type1;
    }

    public Type getType2() {
        return type2;
    }

    public int getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpecial() {
        return special;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenceModifier() {
        return defenceModifier;
    }

    public int getSpecialModifier() {
        return specialModifier;
    }

    public int getSpeedModifier() {
        return speedModifier;
    }

    public void addStatModifier(int stat, int modifier) {
        switch(stat) {
            case ATTACK:
                this.attackModifier += modifier;
                break;
            case DEFENCE:
                this.defenceModifier += modifier;
                break;
            case SPEED:
                this.speedModifier += modifier;
                break;
            case SPECIAL:
                this.specialModifier += modifier;
                break;
        }
    }

    public void setCurrentHp(int currentHp) {

        this.currentHp = currentHp;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addVolatileStatus(Status status) {
        this.volatileStatuses.add(status);
        sortVolatileStatuses();
    }

    public void removeVolatileStatus(String name) {
        for(int i = 0; i < volatileStatuses.size(); ++i) {
            if (volatileStatuses.get(i).getName().equals(name))
                volatileStatuses.remove(volatileStatuses.get(i));
        }
    }

    public void healDamage(int amount) {
        if(this.hp - this.currentHp < amount)
            this.currentHp = this.hp;
        else
            this.currentHp += amount;
    }

    // Returns the damage inflicted, or, if defender was behind substitute, negative the HP remaining on the substitute
    public int inflictDamage(int damage) {
        if(this.substituteHp > 0) {
            if(this.substituteHp < damage) {
                this.substituteHp = 0;
                return 0;
            } else {
                this.substituteHp -= damage;
                return -damage;
            }
        } else {
            if (this.currentHp < damage) {
                int ret = this.currentHp;
                this.currentHp = 0;
                return ret;
            } else {
                this.currentHp -= damage;
                return damage;
            }
        }
    }

    public void setParalysisSpeedDropCounter(int paralysisSpeedDropCounter) {
        this.paralysisSpeedDropCounter = paralysisSpeedDropCounter;
    }

    public void setBurnAttackDropCounter(int burnAttackDropCounter) {
        this.burnAttackDropCounter = burnAttackDropCounter;
    }

    public void setSubstituteHp(int substituteHp) {
        this.substituteHp = substituteHp;
    }

    public int getSubstituteHp() {return this.substituteHp;}

    public List<Status> getVolatileStatuses() {
        return volatileStatuses;
    }

    public boolean hasVolatileStatus(String status_name) {
        for(Status status : volatileStatuses)
            if(status.getName().equals(status_name))
                return true;
        return false;
    }

    public void addParalysisSpeedDropCounter(int paralysisSpeedDropCounter) {
        this.paralysisSpeedDropCounter += paralysisSpeedDropCounter;
    }

    public void addBurnAttackDropCounter(int burnAttackDropCounter) {
        this.burnAttackDropCounter += burnAttackDropCounter;
    }

    private int applyModifier(int stat, int modifier) {
        int ret;
        int a = 2 + Math.abs(modifier);
        if(modifier > 0)
            ret = stat * a / 2;
        else
            ret = stat * 2 / a;
        return ret;
    }

    public int getStatWithModifiers(int stat) {
        int ret = 0;
        switch(stat) {
            case ATTACK:
                ret = applyModifier(attack, attackModifier);
                ret /= Math.pow(2, burnAttackDropCounter);
                break;
            case DEFENCE:
                ret = applyModifier(defence, defenceModifier);
                break;
            case SPECIAL:
                ret = applyModifier(special, specialModifier);
                break;
            case SPEED:
                ret = applyModifier(speed, speedModifier);
                ret /= Math.pow(4, paralysisSpeedDropCounter);
                break;
        }
        return ret;
    }

    public void sortVolatileStatuses() {
        volatileStatuses.sort(Comparator.comparingInt(Status::getPriority).reversed());
    }

    public int getMovePP(String move) {
        for(int i = 0; i < moves_pp.length; ++i) {
            if(moves[i].getName().equals(move))
                return moves_pp[i];
        }
        return -1;
    }

    public void reduceMovePP(String move) {
        for(int i = 0; i < moves_pp.length; ++i) {
            if(moves[i].getName().equals(move))
                --moves_pp[i];
        }
    }
}
