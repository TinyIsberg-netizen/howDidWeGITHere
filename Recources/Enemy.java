package Recources;

public class Enemy {
    public String name;
    public int diceSize;
    public int health;

    public Enemy(String name, int diceSize, int health) {
        this.name = name;
        this.diceSize = diceSize;
        this.health = health;
    }

    public int rollDice() {

        return (int) (Math.random() * diceSize) + 1;
    }

    public boolean isAlive() {
        return health > 0;

    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage!");
        if (!isAlive()) {
            System.out.println(name + " has been defeated!");
        }
    }

    public String getStatus() {
        return name + " Health: " + health;
    }
}
