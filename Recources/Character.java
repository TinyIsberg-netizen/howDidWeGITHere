package Recources;

abstract class Character {

    // Strength, Dexterity, Constitution
    String name;
    int dexterity;
    int strength;
    int constitution;
    int attack = 10;
    int maxHealth = 20;
    int health = maxHealth;
    int xp = 0;
    int level = 1;

    public Character(int dexterity, int strength, int constitution) {
        this.dexterity = dexterity;
        this.strength = strength;
        this.constitution = constitution;
        this.name = name;

    }

    public void attack(Enemy enemy) {
        enemy.takeDamage(attack);
        System.out.println(name + " attacked for " + attack);

    }

    public void takeDamage(int diceSize) {
        health -= diceSize;
        System.out.println(name + " takes" + diceSize);

    }

    public void gainXp(int amount) {
        xp += amount;
        System.out.println(name + " gain" + amount + " experience!");
        if (xp >= level * 50) {
            level++;
            maxHealth += 10;
            attack += 5;
            health = maxHealth;
            System.out.println("Level up! You are now " + level + "!");

        }

    }

    public boolean isAlive() {
        return health > 0;

    }

    public String getStatus() {
        return name + " Health: " + "/" + maxHealth;

    }

    public String getName() {
        return name;
    }

    @Override

    public String toString() {
        return "\n ***Character Sheet***\n" +
                "Name: " + name +
                "\nLevel: " + level +
                "\nHP: " + health + "/" + maxHealth +
                "\nAttack: " + attack +
                "\nXP: " + xp + "\n";
    }

}
