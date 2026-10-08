public class Witch extends NPC {
    private int damage;

    public Witch(String name, int health, int damage) {
        super(name, health);
        this.damage = damage;
    }

    @Override
    public void encounter(Player player) {
        System.out.println("A witch named " + name + " attacks!");
        player.takeDamage(damage);
        System.out.println(name + " deals " + damage + " damage.");
    }
}
