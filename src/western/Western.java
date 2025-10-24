package western;

public class Western {
    public static void main(String[] args) {

        // --- STEP 1: Introduce the humans ---
        Humain townsman = new Humain("Tom", "Beer");
        townsman.sePresenter(); // Townsman introduces himself
        townsman.parle("Hello there! Nice weather today.");

        System.out.println("\n--- Step 2: Enter the Barman ---");

        // --- STEP 2: Barman interaction ---
        Barman joe = new Barman("Joe", "Beer", "Saloon du Soleil");
        joe.sePresenter(); // Introduces the Barman
        joe.parle("Welcome to my bar, partners!");
        joe.sert(townsman); // Serves the townsman his favorite drink

        System.out.println("\n--- Step 3: Enter Cowboy and Lady in Distress ---");

        // --- STEP 3: Cowboy and Lady ---
        CowBoy clint = new CowBoy("Clint", "Whiskey", 80, "brave");
        clint.sePresenter(); // Introduce Cowboy

        DameStresse jane = new DameStresse("Jane", "Tea", "free", "red");
        jane.sePresenter(); // Lady introduces herself

        System.out.println("\n--- Step 4: Enter Brigand and Kidnap Scenario ---");

        // --- STEP 4: Brigand kidnaps Lady ---
        Brigand billy = new Brigand("Billy the Kid", "Rum", 0, 500, "dangerous", false);
        billy.sePresenter(); // Brigand introduces himself
        billy.kidnapperDame(jane); // Brigand kidnaps Jane

        // Check Lady's state after kidnapping
        jane.sePresenter();

        System.out.println("\n--- Step 5: Cowboy rescues the Lady ---");

        // --- STEP 5: Cowboy rescues ---
        clint.libererDame(jane); // Cowboy rescues Jane
        jane.seFaireLiberer(clint); // Lady acknowledges rescue
        jane.changerRobe(clint); // Lady changes dress color after rescue
        jane.sePresenter(); // Lady introduces herself again

        System.out.println("\n--- Step 6: Sheriff enters to arrest Brigand ---");

        // --- STEP 6: Sheriff captures Brigand ---
        Sherif wyatt = new Sherif("Wyatt Earp", "Whiskey", 90, "brave");
        wyatt.sePresenter(); // Sheriff introduces himself
        wyatt.rechercher(billy); // Sheriff searches for Brigand
        wyatt.coffrer(billy); // Sheriff arrests Brigand

        // Brigand tries to escape
        billy.echapper();

        System.out.println("\n--- Step 7: Cowboy and Sheriff continue ---");
        
        System.out.println("\n--- Step 9: Enter the Femme Fatale ---");
DameBrigand bella = new DameBrigand("Bella Rouge", "Wine", "free", "black", 1, 800, "mysterious", false);
bella.sePresenter();

DameStresse lily = new DameStresse("Lily", "Tea", "free", "yellow");
bella.kidnapperDame(lily);


wyatt.rechercher(new Brigand("Billy", "Rum", 0, 500, "dangerous", false));
bella.seFaireEmprisonner(wyatt);

bella.echapper();

        clint.sePresenter(); // Cowboy re-introduces himself
        wyatt.sePresenter(); // Sheriff reports jailed brigands

        System.out.println("\n--- Step 8: End with a chat at the Saloon ---");

        joe.parle("Everything settled, folks? Drinks on me!");
        townsman.parle("Thanks Joe, you're the best!");
        clint.parle("I’ll make sure the town stays safe!");
        jane.parle("And I’ll stay out of trouble from now on.");
    }
}

