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
}
/*
 * boolean kolla om enemy lever,
 * string getstatus
 */
