import java.io.*;
import java.util.*;

public class Game {
    private final ArrayList<NPC> npcs = new ArrayList<>();
    private final ArrayList<Tool> tools = new ArrayList<>();
    private final ArrayList<String> locations = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);

    private Player player;

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        try {
            loadNPCs("npc.txt");
            loadTools("tools.txt");
            loadLocations("settings.txt");
        } catch (IOException e) {
            System.out.println("Could not load game files: " + e.getMessage());
            return;
        }

        System.out.print("Enter your name: ");
        String playerName = input.nextLine();
        player = new Player(playerName);

        // Give player a starting weapon and potion when available.
        addStartingTool("Spear");
        addStartingTool("Mugwort");

        System.out.println("\nWelcome to Wolf's Bane Castle, " + player.getName() + ".");
        System.out.println("Survive the castle and find the Wolf's Bane potion.");

        play();
    }

    private void play() {
        for (int i = 0; i < locations.size() && player.isAlive(); i++) {
            String location = locations.get(i);

            System.out.println("\n=== " + location + " ===");
            System.out.println("Health: " + player.getHealth());

            if (i < npcs.size()) {
                NPC npc = npcs.get(i);

                // instanceof was part of the remembered assignment approach.
                if (npc instanceof Witch) {
                    handleWitch((Witch) npc);
                } else if (npc instanceof Knight) {
                    npc.encounter(player);
                }
            } else {
                System.out.println("The room is quiet.");
            }

            if (!player.isAlive()) {
                break;
            }

            if (i < tools.size()) {
                Tool found = tools.get(i);
                System.out.println("You found: " + found);
                player.addTool(found);
            }

            System.out.println("\n1. Continue");
            System.out.println("2. View inventory");
            System.out.println("3. Use potion");
            System.out.print("Choice: ");
            String choice = input.nextLine();

            if (choice.equals("2")) {
                player.showInventory();
                System.out.println("Press Enter to continue.");
                input.nextLine();
            } else if (choice.equals("3")) {
                usePotion();
            }
        }

        if (!player.isAlive()) {
            System.out.println("\nYou were defeated in Wolf's Bane Castle.");
        } else if (player.findPotion("Wolf's Bane") != null) {
            System.out.println("\nYou escaped with the Wolf's Bane potion. You win!");
        } else {
            System.out.println("\nYou escaped the castle, but did not find Wolf's Bane.");
        }
    }

    private void handleWitch(Witch witch) {
        System.out.println("You encounter " + witch.getName() + ".");

        Weapon weapon = chooseWeapon();

        if (weapon != null) {
            System.out.println("You attack with " + weapon.getName()
                + " for " + weapon.getDamage() + " damage.");
            witch.takeDamage(weapon.getDamage());
        }

        if (witch.isAlive()) {
            witch.encounter(player);
        } else {
            System.out.println("You defeated " + witch.getName() + ".");
        }
    }

    private Weapon chooseWeapon() {
        System.out.print("Enter a weapon name, or press Enter to skip: ");
        String name = input.nextLine();

        if (name.isBlank()) {
            return null;
        }

        Weapon weapon = player.findWeapon(name);

        if (weapon == null) {
            System.out.println("You do not have that weapon.");
        }

        return weapon;
    }

    private void usePotion() {
        System.out.print("Potion name: ");
        String name = input.nextLine();
        Potion potion = player.findPotion(name);

        if (potion == null) {
            System.out.println("You do not have that potion.");
            return;
        }

        int missing = 100 - player.getHealth();
        int healAmount = Math.min(missing, potion.getHealing());

        player.heal(healAmount);
        System.out.println("You recover " + healAmount + " health.");
    }

    private void addStartingTool(String name) {
        for (Tool tool : tools) {
            if (tool.getName().equalsIgnoreCase(name)) {
                player.addTool(tool);
                return;
            }
        }
    }

    public void loadNPCs(String fileName) throws IOException {
        npcs.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split("\\|");

                if (p[0].equalsIgnoreCase("WITCH") && p.length >= 4) {
                    npcs.add(new Witch(
                        p[1].trim(),
                        Integer.parseInt(p[2].trim()),
                        Integer.parseInt(p[3].trim())
                    ));
                } else if (p[0].equalsIgnoreCase("KNIGHT") && p.length >= 4) {
                    npcs.add(new Knight(
                        p[1].trim(),
                        Integer.parseInt(p[2].trim()),
                        p[3].trim()
                    ));
                }
            }
        }
    }

    public void loadTools(String fileName) throws IOException {
        tools.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split("\\|");

                if (p[0].equalsIgnoreCase("WEAPON") && p.length >= 3) {
                    tools.add(new Weapon(
                        p[1].trim(),
                        Integer.parseInt(p[2].trim())
                    ));
                } else if (p[0].equalsIgnoreCase("POTION") && p.length >= 3) {
                    tools.add(new Potion(
                        p[1].trim(),
                        Integer.parseInt(p[2].trim())
                    ));
                }
            }
        }
    }

    public void loadLocations(String fileName) throws IOException {
        locations.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    locations.add(line.trim());
                }
            }
        }
    }
}
