public class Weapon extends Tool {
    private int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return name + " (Weapon, damage " + damage + ")";
    }
}
