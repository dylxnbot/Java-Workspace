# Wolf's Bane Castle

Reconstructed version of a Java text-adventure coursework project.

## Main classes
- `GameCharacter`
- `Player`
- `NPC`
- `Witch`
- `Knight`
- `Tool`
- `Weapon`
- `Potion`
- `Game`

## Features
- Loads NPCs from `npc.txt`
- Loads weapons and potions from `tools.txt`
- Loads 8 castle locations from `settings.txt`
- Uses inheritance and `instanceof`
- Uses linear searches to find tools by name
- Witch and knight encounters
- Player inventory, combat, and potion use

## Run

```bash
javac *.java
java Game
```

Keep `npc.txt`, `tools.txt`, and `settings.txt` in the same directory as the compiled classes.

## Reconstruction note
This is a clean reconstruction based on remembered project structure and behavior. It is not guaranteed to be byte-for-byte identical to the original coursework submission.
