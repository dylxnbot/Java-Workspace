public class Knight extends NPC {
    private String message;

    public Knight(String name, int health, String message) {
        super(name, health);
        this.message = message;
    }

    @Override
    public void encounter(Player player) {
        System.out.println("Knight " + name + " says: " + message);
    }
}
