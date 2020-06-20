package logic.things;

public class PokemonBuilder {
    public enum Stat {HP, Attack, Defence, Special, Speed}

    private String species;
    private Type type1;
    private Type type2;
    private int base_hp;
    private int base_attack;
    private int base_defence;
    private int base_special;
    private int base_speed;

    private int level = 100;

    private int hp_dv;
    private int attack_dv;
    private int defence_dv;
    private int speed_dv;
    private int special_dv;

    private int hp_stat_exp;
    private int attack_stat_exp;
    private int defence_stat_exp;
    private int speed_stat_exp;
    private int special_stat_exp;

    private Move[] moves = new Move[4];

    public PokemonBuilder(String species, Type type1, Type type2, int base_hp, int base_attack, int base_defence, int base_special, int base_speed) {
        this.species = species;
        this.type1 = type1;
        this.type2 = type2;
        this.base_hp = base_hp;
        this.base_attack = base_attack;
        this.base_defence = base_defence;
        this.base_special = base_special;
        this.base_speed = base_speed;

        this.hp_dv = 15;
        this.attack_dv = 15;
        this.defence_dv = 15;
        this.special_dv = 15;
        this.speed_dv = 15;

        this.hp_stat_exp = 65535;
        this.attack_stat_exp = 65535;
        this.defence_stat_exp = 65535;
        this.special_stat_exp = 65535;
        this.speed_stat_exp = 65535;
    }

    public void setNewMove(Move move) {
        for(int i = 0; i < 4; ++i) {
            if(this.moves[i] == null) {
                this.moves[i] = move;
                return;
            }
        }
    }

    public int getBase_speed() {
        return base_speed;
    }

    public void setLevel(int value) {
        this.level = value;
    }

    public void setStatDv(Stat stat, int value) {
        switch(stat) {
            case HP:
                this.hp_dv = value;
                break;
            case Attack:
                this.attack_dv = value;
                break;
            case Defence:
                this.defence_dv = value;
                break;
            case Special:
                this.special_dv = value;
                break;
            case Speed:
                this.speed_dv = value;
                break;
        }
    }

    public void setStatStatExo(Stat stat, int value) {
        switch(stat) {
            case HP:
                this.hp_stat_exp = value;
                break;
            case Attack:
                this.attack_stat_exp = value;
                break;
            case Defence:
                this.defence_stat_exp = value;
                break;
            case Special:
                this.special_stat_exp = value;
                break;
            case Speed:
                this.speed_stat_exp = value;
                break;
        }
    }

    private int calcStat(int base, int dv, int stat_exp, int level) {
        int part1 = (base + dv) * 2 + (int)(Math.sqrt(stat_exp)/4);
        int part2 = part1 * level / 100;
        return part2 + 5;
    }

    public Pokemon build() {
        //HP
        int part1 = (base_hp + hp_dv) * 2 + (int)(Math.sqrt(hp_stat_exp)/4);
        int part2 = part1 * level / 100;
        int hp = part2 + level + 10;

        //Attack
        int attack = calcStat(base_attack, attack_dv, attack_stat_exp, level);

        //Defence
        int defence = calcStat(base_defence, defence_dv, defence_stat_exp, level);

        //Special
        int special = calcStat(base_special, special_dv, special_stat_exp, level);

        //Speed
        int speed = calcStat(base_speed, speed_dv, speed_stat_exp, level);

        return new Pokemon(this.species, this.type1, this.type2, this.level, hp, attack, defence, special, speed, this.moves);

    }
}
