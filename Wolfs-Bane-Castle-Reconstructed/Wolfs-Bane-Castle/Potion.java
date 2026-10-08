public class Potion extends Tool {
    private int healing;

    public Potion(String name, int healing) {
        super(name);
        this.healing = healing;
    }

    public int getHealing() {
        return healing;
    }

    @Override
    public String toString() {
        return name + " (Potion, healing " + healing + ")";
    }
}
