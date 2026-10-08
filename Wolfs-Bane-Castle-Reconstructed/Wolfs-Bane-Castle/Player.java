import java.util.ArrayList;

public class Player extends GameCharacter {
    private final ArrayList<Tool> inventory = new ArrayList<>();

    public Player(String name) {
        super(name, 100);
    }

    public void addTool(Tool tool) {
        inventory.add(tool);
    }

    public Tool findTool(String toolName) {
        for (Tool tool : inventory) {
            if (tool.getName().equalsIgnoreCase(toolName)) {
                return tool;
            }
        }
        return null;
    }

    public Weapon findWeapon(String weaponName) {
        Tool tool = findTool(weaponName);
        return tool instanceof Weapon ? (Weapon) tool : null;
    }

    public Potion findPotion(String potionName) {
        Tool tool = findTool(potionName);
        return tool instanceof Potion ? (Potion) tool : null;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (Tool tool : inventory) {
            System.out.println("- " + tool);
        }
    }
}
