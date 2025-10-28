package storyhandler;
import characters.Sherif;
import characters.Humain;
import characters.DameStresse;
import characters.DameBrigand;
import characters.CowBoy;
import characters.Brigand;
import characters.Barman;
import java.util.*;

/**
 * GameManager - Story Mode Only for Cowboy and Brigand
 */
public class GameManager {
    private Scanner scanner;
    private GameState gameState;
    private Map<String, Humain> npcs;
    
    public GameManager() {
        this.scanner = new Scanner(System.in);
        this.gameState = new GameState();
        this.npcs = new HashMap<>();
    }
    
    public void startGame() {
        displayWelcome();
        Humain player = selectCharacter();
        gameState.setPlayerCharacter(player);
        initializeNPCs();
        
        // Launch story mode based on character type
        if(player instanceof CowBoy) {
            CowboyStoryHandler story = new CowboyStoryHandler((CowBoy)player, scanner, npcs);
            story.playStory();
            gameState.setReputation(story.getFinalReputation());
        } else if(player instanceof Brigand) {
            BrigandStoryHandler story = new BrigandStoryHandler((Brigand)player, scanner, npcs);
            story.playStory();
            gameState.setReputation(story.getFinalReputation());
        }
        
        displayFinalStats();
        scanner.close();
    }
    
    private void displayWelcome() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO THE WILD WEST ADVENTURE!     ║");
        System.out.println("║   Your choices shape your destiny...      ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
    }
    
    private Humain selectCharacter() {
        System.out.println("═".repeat(50));
        System.out.println("CHOOSE YOUR PATH:");
        System.out.println("═".repeat(50));
        System.out.println("1. 🤠 COWBOY - The Heroic Gunslinger");
        System.out.println("   Walk the path of justice and honor");
        System.out.println("   Face outlaws and protect the innocent\n");
        System.out.println("2. 🦹 BRIGAND - The Notorious Outlaw");
        System.out.println("   Live outside the law");
        System.out.println("   Rob banks and evade justice");
        System.out.println("═".repeat(50));
        System.out.print("\nEnter choice (1-2): ");
        
        int choice = getIntInput(1, 2);
        return createCharacter(choice);
    }
    
    private Humain createCharacter(int type) {
        System.out.print("\nCharacter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Favorite drink: ");
        String drink = scanner.nextLine().trim();
        
        if(type == 1) {
            // Create Cowboy
            System.out.print("Reputation (0-100): ");
            int rep = getIntInput(0, 100);
            System.out.print("Look (brave/mysterious/dangerous): ");
            String look = scanner.nextLine().trim();
            CowBoy cowboy = new CowBoy(name, drink, rep, look);
            System.out.println("\n" + name + " rides into town! 🤠");
            cowboy.sePresenter();
            return cowboy;
            
        } else {
            // Create Brigand
            System.out.print("Bounty ($): ");
            int bounty = getIntInput(0, 10000);
            System.out.print("Look (dangerous/cunning/ruthless): ");
            String look = scanner.nextLine().trim();
            Brigand brigand = new Brigand(name, drink, 0, bounty, look, false);
            System.out.println("\n" + name + " emerges from shadows! 🦹");
            brigand.sePresenter();
            return brigand;
        }
    }
    
    private void initializeNPCs() {
        npcs.put("barman", new Barman("Joe", "Beer", "Dusty Saloon"));
        npcs.put("townsman", new Humain("Tom", "Beer"));
        npcs.put("lady1", new DameStresse("Clara", "Tea", "free", "blue"));
        npcs.put("lady2", new DameStresse("Emma", "Lemonade", "free", "yellow"));
        npcs.put("outlaw1", new Brigand("Jesse the Scarred", "Rum", 0, 1500, "dangerous", false));
        npcs.put("outlaw2", new Brigand("Black Bart", "Whiskey", 0, 1200, "ruthless", false));
        npcs.put("sheriff", new Sherif("Marshal Kane", "Coffee", 90, "stern"));
        npcs.put("femme", new DameBrigand("Rosa Red", "Wine", "free", "red", 0, 600, "seductive", false));
    }
    
    private void displayFinalStats() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("⭐ FINAL STATISTICS ⭐");
        System.out.println("═".repeat(50));
        System.out.println("Character: " + gameState.getPlayerCharacter().quelEstTonNom());
        System.out.println("Final Reputation: " + gameState.getReputation());
        System.out.println("═".repeat(50));
        System.out.println("\n🎬 Thanks for playing Wild West Adventure!");
        System.out.println("Your legend will be remembered...\n");
    }
    
    private int getIntInput(int min, int max) {
        while(true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if(value >= min && value <= max) {
                    return value;
                }
                System.out.print("Enter " + min + "-" + max + ": ");
            } catch(NumberFormatException e) {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }
}

// Supporting class
class GameState {
    private Humain playerCharacter;
    private int reputation;
    
    public GameState() {
        this.reputation = 50;
    }
    
    public void setPlayerCharacter(Humain c) { this.playerCharacter = c; }
    public Humain getPlayerCharacter() { return playerCharacter; }
    public int getReputation() { return reputation; }
    public void setReputation(int r) { this.reputation = r; }
}