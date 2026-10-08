public abstract class NPC extends GameCharacter {
    public NPC(String name, int health) {
        super(name, health);
    }

    public abstract void encounter(Player player);
}
