package model;

public class Player {
    private String name;
    private int health;
    private int attackPower;
    private boolean defending;

    public Player() {
    }

    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defending = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
        public boolean isDefending() {
            return defending;
        }

        public void setDefending(boolean defending) {
            this.defending = defending;
        }
    }


