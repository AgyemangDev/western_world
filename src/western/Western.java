package western;

import java.util.Scanner;

/**
 * Main launcher for the Western game
 * Allows users to choose between demo mode or interactive game
 */
public class Western {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║          WILD WEST ADVENTURE                   ║");
        System.out.println("║                                                ║");
        System.out.println("║  Choose your experience:                       ║");
        System.out.println("║                                                ║");
        System.out.println("║  1. Play Interactive Game (NEW!)               ║");
        System.out.println("║     → Choose your character and make decisions ║");
        System.out.println("║                                                ║");
        System.out.println("║  2. Watch Story Demo                           ║");
        System.out.println("║     → See the original scripted story          ║");
        System.out.println("║                                                ║");
        System.out.println("║  3. Exit                                       ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("\nEnter your choice (1-3): ");
        
        int choice = getValidInput(scanner, 1, 3);
        
        switch(choice) {
            case 1:
                // Launch interactive game
                System.out.println("\n🎮 Starting Interactive Game...\n");
                GameManager game = new GameManager();
                game.startGame();
                break;
                
            case 2:
                // Run original demo
                System.out.println("\n🎬 Starting Story Demo...\n");
                runOriginalDemo();
                break;
                
            case 3:
                System.out.println("\n👋 Thanks for visiting the Wild West!");
                break;
        }
        
        scanner.close();
    }
    
    /**
     * Original scripted demo from your initial code
     */
    private static void runOriginalDemo() {
        System.out.println("=".repeat(60));
        System.out.println("THE TALE OF THE WILD WEST - A Scripted Adventure");
        System.out.println("=".repeat(60) + "\n");
        
        // --- STEP 1: Introduce the humans ---
        System.out.println("--- Step 1: Meet the Townsman ---");
        Humain townsman = new Humain("Tom", "Beer");
        townsman.sePresenter();
        townsman.parle("Hello there! Nice weather today.");
        
        System.out.println("\n--- Step 2: Enter the Barman ---");
        // --- STEP 2: Barman interaction ---
        Barman joe = new Barman("Joe", "Beer", "Saloon du Soleil");
        joe.sePresenter();
        joe.parle("Welcome to my bar, partners!");
        joe.sert(townsman);
        
        System.out.println("\n--- Step 3: Enter Cowboy and Lady in Distress ---");
        // --- STEP 3: Cowboy and Lady ---
        CowBoy clint = new CowBoy("Clint", "Whiskey", 80, "brave");
        clint.sePresenter();
        
        DameStresse jane = new DameStresse("Jane", "Tea", "free", "red");
        jane.sePresenter();
        
        System.out.println("\n--- Step 4: Enter Brigand and Kidnap Scenario ---");
        // --- STEP 4: Brigand kidnaps Lady ---
        Brigand billy = new Brigand("Billy the Kid", "Rum", 0, 500, "dangerous", false);
        billy.sePresenter();
        billy.kidnapperDame(jane);
        
        // Check Lady's state after kidnapping
        jane.sePresenter();
        
        System.out.println("\n--- Step 5: Cowboy rescues the Lady ---");
        // --- STEP 5: Cowboy rescues ---
        clint.libererDame(jane);
        jane.seFaireLiberer(clint);
        jane.changerRobe(clint);
        jane.sePresenter();
        
        System.out.println("\n--- Step 6: Sheriff enters to arrest Brigand ---");
        // --- STEP 6: Sheriff captures Brigand ---
        Sherif wyatt = new Sherif("Wyatt Earp", "Whiskey", 90, "brave");
        wyatt.sePresenter();
        wyatt.rechercher(billy);
        wyatt.coffrer(billy);
        
        // Brigand tries to escape
        billy.echapper();
        
        System.out.println("\n--- Step 7: Enter the Femme Fatale ---");
        DameBrigand bella = new DameBrigand("Bella Rouge", "Wine", "free", "black", 
                                            1, 800, "mysterious", false);
        bella.sePresenter();
        
        DameStresse lily = new DameStresse("Lily", "Tea", "free", "yellow");
        bella.kidnapperDame(lily);
        
        wyatt.rechercher(new Brigand("Billy", "Rum", 0, 500, "dangerous", false));
        bella.seFaireEmprisonner(wyatt);
        bella.echapper();
        
        System.out.println("\n--- Step 8: Heroes Continue Their Watch ---");
        clint.sePresenter();
        wyatt.sePresenter();
        
        System.out.println("\n--- Step 9: End with a chat at the Saloon ---");
        joe.parle("Everything settled, folks? Drinks on me!");
        townsman.parle("Thanks Joe, you're the best!");
        clint.parle("I'll make sure the town stays safe!");
        jane.parle("And I'll stay out of trouble from now on.");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("THE END - Thanks for watching!");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Helper method to get valid integer input
     */
    private static int getValidInput(Scanner scanner, int min, int max) {
        while(true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if(value >= min && value <= max) {
                    return value;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch(NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}