package western;

import java.util.*;

/**
 * Enhanced GameManager - Supports both Story Mode and Random Events Mode
 */
public class GameManager {
    private Scanner scanner;
    private Random random;
    private GameState gameState;
    private Map<String, Humain> npcs;
    
    public GameManager() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.gameState = new GameState();
        this.npcs = new HashMap<>();
    }
    
    public void startGame() {
        displayWelcome();
        Humain player = selectCharacter();
        gameState.setPlayerCharacter(player);
        initializeNPCs();
        
        // Check if Cowboy - offer story mode
        if(player instanceof CowBoy) {
            offerStoryMode((CowBoy)player);
        } else {
            playRandomEvents();
        }
    }
    
    private void displayWelcome() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO THE WILD WEST ADVENTURE!     ║");
        System.out.println("║   Your choices shape your destiny...      ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
    }
    
    private void offerStoryMode(CowBoy cowboy) {
        System.out.println("\n🎬 COWBOY STORY MODE AVAILABLE!");
        System.out.println("Would you like to:");
        System.out.println("   (1) Play the full story adventure");
        System.out.println("   (2) Play random events mode\n");
        System.out.print("Your choice (1/2): ");
        
        int choice = getIntInput(1, 2);
        
        if(choice == 1) {
            CowboyStoryHandler story = new CowboyStoryHandler(cowboy, scanner, npcs);
            story.playStory();
            
            // Update game state with story results
            gameState.setReputation(story.getFinalReputation());
            
            if(story.isAlive()) {
                System.out.println("\n✨ You may continue with random events if you wish...");
                System.out.print("Continue? (y/n): ");
                if(scanner.nextLine().trim().toLowerCase().equals("y")) {
                    playRandomEvents();
                }
            }
        } else {
            playRandomEvents();
        }
    }
    
    private Humain selectCharacter() {
        System.out.println("Choose your character:");
        System.out.println("1. Cowboy 🤠 - Heroic gunslinger");
        System.out.println("2. Sheriff ⭐ - Law enforcer");
        System.out.println("3. Brigand 🦹 - Dangerous outlaw");
        System.out.println("4. Barman 🍺 - Saloon keeper");
        System.out.println("5. Dame 👗 - Town lady");
        System.out.println("6. Dame Brigand 💃 - Femme fatale");
        System.out.print("\nEnter choice (1-6): ");
        
        int choice = getIntInput(1, 6);
        return createCharacter(choice);
    }
    
    private Humain createCharacter(int type) {
        System.out.print("\nCharacter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Favorite drink: ");
        String drink = scanner.nextLine().trim();
        
        switch(type) {
            case 1:
                System.out.print("Reputation (0-100): ");
                int rep = getIntInput(0, 100);
                System.out.print("Look (brave/mysterious/dangerous): ");
                String look = scanner.nextLine().trim();
                CowBoy cowboy = new CowBoy(name, drink, rep, look);
                System.out.println("\n" + name + " rides into town! 🤠");
                cowboy.sePresenter();
                return cowboy;
                
            case 2:
                System.out.print("Reputation (0-100): ");
                int sRep = getIntInput(0, 100);
                System.out.print("Look (brave/stern/wise): ");
                String sLook = scanner.nextLine().trim();
                Sherif sheriff = new Sherif(name, drink, sRep, sLook);
                System.out.println("\n" + name + " takes the badge! ⭐");
                sheriff.sePresenter();
                return sheriff;
                
            case 3:
                System.out.print("Bounty ($): ");
                int bounty = getIntInput(0, 10000);
                System.out.print("Look (dangerous/cunning/ruthless): ");
                String bLook = scanner.nextLine().trim();
                Brigand brigand = new Brigand(name, drink, 0, bounty, bLook, false);
                System.out.println("\n" + name + " emerges from shadows! 🦹");
                brigand.sePresenter();
                return brigand;
                
            case 4:
                System.out.print("Saloon name: ");
                String saloon = scanner.nextLine().trim();
                Barman barman = new Barman(name, drink, saloon);
                System.out.println("\n" + name + " opens for business! 🍺");
                barman.sePresenter();
                return barman;
                
            case 5:
                System.out.print("Dress color: ");
                String color = scanner.nextLine().trim();
                DameStresse dame = new DameStresse(name, drink, "free", color);
                System.out.println("\n" + name + " enters the scene! 👗");
                dame.sePresenter();
                return dame;
                
            case 6:
                System.out.print("Dress color: ");
                String dColor = scanner.nextLine().trim();
                System.out.print("Bounty ($): ");
                int dBounty = getIntInput(0, 10000);
                System.out.print("Look (mysterious/seductive/dangerous): ");
                String dLook = scanner.nextLine().trim();
                DameBrigand dameBrigand = new DameBrigand(name, drink, "free", dColor, 0, dBounty, dLook, false);
                System.out.println("\n" + name + " arrives! 💃");
                dameBrigand.sePresenter();
                return dameBrigand;
                
            default:
                return new Humain(name, drink);
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
    
    // ==================== RANDOM EVENTS MODE ====================
    private void playRandomEvents() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("YOUR STORY BEGINS...");
        System.out.println("=".repeat(50) + "\n");
        
        boolean running = true;
        int turn = 0;
        
        while(running && turn < 12) {
            turn++;
            System.out.println("\n--- Turn " + turn + " ---");
            
            Event event = generateEvent();
            System.out.println("\n" + event.getDescription());
            
            List<Choice> choices = event.getChoices();
            for(int i = 0; i < choices.size(); i++) {
                System.out.println((i + 1) + ". " + choices.get(i).getDescription());
            }
            
            System.out.print("\nYour action (1-" + choices.size() + "): ");
            int idx = getIntInput(1, choices.size()) - 1;
            
            Outcome outcome = processChoice(choices.get(idx), event);
            displayOutcome(outcome);
            gameState.updateFromOutcome(outcome);
            
            if(outcome.isGameEnding()) {
                running = false;
                displayEnding(outcome);
            }
            
            if(running && turn % 4 == 0) {
                System.out.print("\nContinue? (y/n): ");
                if(scanner.nextLine().trim().toLowerCase().equals("n")) {
                    running = false;
                    System.out.println("\n" + gameState.getPlayerCharacter().quelEstTonNom() + " rides into the sunset...");
                }
            }
        }
        
        if(turn >= 12) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("YOUR LEGEND GROWS...");
            System.out.println("Final Reputation: " + gameState.getReputation());
            System.out.println("=".repeat(50));
        }
        
        scanner.close();
    }
    
    private Event generateEvent() {
        Humain player = gameState.getPlayerCharacter();
        List<Event> events = new ArrayList<>();
        
        events.add(createSaloonEvent());
        events.add(createTravelEvent());
        
        if(player instanceof CowBoy) {
            events.addAll(getCowboyEvents());
        } else if(player instanceof Sherif) {
            events.addAll(getSherifEvents());
        } else if(player instanceof Brigand) {
            events.addAll(getBrigandEvents());
        } else if(player instanceof Barman) {
            events.addAll(getBarmanEvents());
        } else if(player instanceof DameBrigand) {
            events.addAll(getDameBrigandEvents());
        } else if(player instanceof DameStresse) {
            events.addAll(getDameEvents());
        }
        
        return events.get(random.nextInt(events.size()));
    }
    
    // ==================== CHARACTER EVENT GENERATORS ====================
    private List<Event> getCowboyEvents() {
        List<Event> events = new ArrayList<>();
        
        Event rescue = new Event("You hear screams! A lady is threatened by bandits!");
        rescue.addChoice(new Choice("Rush in guns blazing", ChoiceType.HEROIC, 60));
        rescue.addChoice(new Choice("Sneak around and ambush", ChoiceType.TACTICAL, 75));
        rescue.addChoice(new Choice("Call for backup", ChoiceType.SAFE, 90));
        events.add(rescue);
        
        Event duel = new Event("A notorious outlaw challenges you to a duel!");
        duel.addChoice(new Choice("Accept the duel", ChoiceType.AGGRESSIVE, 55));
        duel.addChoice(new Choice("Talk him down", ChoiceType.SOCIAL, 65));
        duel.addChoice(new Choice("Shoot first", ChoiceType.RISKY, 50));
        events.add(duel);
        
        Event showoff = new Event("Townsfolk want to see your shooting skills!");
        showoff.addChoice(new Choice("Shoot bottles in air", ChoiceType.NEUTRAL, 70));
        showoff.addChoice(new Choice("Show trick shots", ChoiceType.RISKY, 60));
        showoff.addChoice(new Choice("Decline politely", ChoiceType.SAFE, 85));
        events.add(showoff);
        
        return events;
    }
    
    private List<Event> getSherifEvents() {
        List<Event> events = new ArrayList<>();
        
        Event arrest = new Event("A wanted criminal is spotted in the saloon!");
        arrest.addChoice(new Choice("Arrest him publicly", ChoiceType.AGGRESSIVE, 60));
        arrest.addChoice(new Choice("Follow and ambush", ChoiceType.TACTICAL, 75));
        arrest.addChoice(new Choice("Gather a posse", ChoiceType.SAFE, 85));
        events.add(arrest);
        
        Event investigation = new Event("A robbery occurred. You must investigate.");
        investigation.addChoice(new Choice("Question aggressively", ChoiceType.AGGRESSIVE, 65));
        investigation.addChoice(new Choice("Examine evidence", ChoiceType.TACTICAL, 80));
        investigation.addChoice(new Choice("Offer reward", ChoiceType.SOCIAL, 70));
        events.add(investigation);
        
        Event jailbreak = new Event("Prisoners plan an escape!");
        jailbreak.addChoice(new Choice("Confront them", ChoiceType.AGGRESSIVE, 55));
        jailbreak.addChoice(new Choice("Set up trap", ChoiceType.TACTICAL, 75));
        jailbreak.addChoice(new Choice("Transfer them", ChoiceType.SAFE, 85));
        events.add(jailbreak);
        
        return events;
    }
    
    private List<Event> getBrigandEvents() {
        List<Event> events = new ArrayList<>();
        
        Event heist = new Event("You've scouted a bank with weak security...");
        heist.addChoice(new Choice("Rob it tonight", ChoiceType.RISKY, 45));
        heist.addChoice(new Choice("Plan carefully", ChoiceType.TACTICAL, 70));
        heist.addChoice(new Choice("Abandon it", ChoiceType.SAFE, 95));
        events.add(heist);
        
        Event kidnap = new Event("A wealthy lady walks alone...");
        kidnap.addChoice(new Choice("Kidnap her now", ChoiceType.AGGRESSIVE, 50));
        kidnap.addChoice(new Choice("Wait for perfect moment", ChoiceType.TACTICAL, 70));
        kidnap.addChoice(new Choice("Reconsider", ChoiceType.SAFE, 100));
        events.add(kidnap);
        
        Event escape = new Event("The law is closing in!");
        escape.addChoice(new Choice("Run for hills", ChoiceType.RISKY, 60));
        escape.addChoice(new Choice("Hide in saloon", ChoiceType.TACTICAL, 70));
        escape.addChoice(new Choice("Blend in crowd", ChoiceType.SOCIAL, 65));
        events.add(escape);
        
        return events;
    }
    
    private List<Event> getBarmanEvents() {
        List<Event> events = new ArrayList<>();
        
        Event fight = new Event("A brawl erupts in your saloon!");
        fight.addChoice(new Choice("Break it up yourself", ChoiceType.AGGRESSIVE, 65));
        fight.addChoice(new Choice("Call sheriff", ChoiceType.SAFE, 85));
        fight.addChoice(new Choice("Let them outside", ChoiceType.NEUTRAL, 75));
        events.add(fight);
        
        Event merchant = new Event("Merchant offers premium whiskey...");
        merchant.addChoice(new Choice("Buy everything", ChoiceType.RISKY, 60));
        merchant.addChoice(new Choice("Buy small amount", ChoiceType.SAFE, 85));
        merchant.addChoice(new Choice("Negotiate", ChoiceType.SOCIAL, 70));
        events.add(merchant);
        
        Event gossip = new Event("Customers share valuable information...");
        gossip.addChoice(new Choice("Listen and serve", ChoiceType.SOCIAL, 80));
        gossip.addChoice(new Choice("Join conversation", ChoiceType.NEUTRAL, 70));
        gossip.addChoice(new Choice("Mind business", ChoiceType.SAFE, 90));
        events.add(gossip);
        
        return events;
    }
    
    private List<Event> getDameEvents() {
        List<Event> events = new ArrayList<>();
        
        Event suitor = new Event("A charming stranger shows interest...");
        suitor.addChoice(new Choice("Accept courtship", ChoiceType.SOCIAL, 70));
        suitor.addChoice(new Choice("Politely decline", ChoiceType.SAFE, 90));
        suitor.addChoice(new Choice("Test intentions", ChoiceType.TACTICAL, 75));
        events.add(suitor);
        
        Event danger = new Event("You sense you're being followed...");
        danger.addChoice(new Choice("Confront follower", ChoiceType.AGGRESSIVE, 50));
        danger.addChoice(new Choice("Run to crowd", ChoiceType.SAFE, 85));
        danger.addChoice(new Choice("Lead to sheriff", ChoiceType.TACTICAL, 75));
        events.add(danger);
        
        Event social = new Event("Town hosts grand gathering...");
        social.addChoice(new Choice("Attend in finest", ChoiceType.SOCIAL, 80));
        social.addChoice(new Choice("Attend quietly", ChoiceType.NEUTRAL, 90));
        social.addChoice(new Choice("Stay home", ChoiceType.SAFE, 100));
        events.add(social);
        
        return events;
    }
    
    private List<Event> getDameBrigandEvents() {
        List<Event> events = new ArrayList<>();
        
        Event seduce = new Event("Wealthy merchant in town...");
        seduce.addChoice(new Choice("Seduce and rob", ChoiceType.RISKY, 65));
        seduce.addChoice(new Choice("Charm info first", ChoiceType.TACTICAL, 75));
        seduce.addChoice(new Choice("Too risky", ChoiceType.SAFE, 90));
        events.add(seduce);
        
        Event rival = new Event("Another femme fatale in your territory!");
        rival.addChoice(new Choice("Confront her", ChoiceType.AGGRESSIVE, 60));
        rival.addChoice(new Choice("Sabotage plans", ChoiceType.TACTICAL, 70));
        rival.addChoice(new Choice("Propose alliance", ChoiceType.SOCIAL, 65));
        events.add(rival);
        
        Event lawman = new Event("A lawman is suspicious of you...");
        lawman.addChoice(new Choice("Charm him off", ChoiceType.SOCIAL, 70));
        lawman.addChoice(new Choice("Leave town", ChoiceType.SAFE, 85));
        lawman.addChoice(new Choice("Frame someone", ChoiceType.RISKY, 60));
        events.add(lawman);
        
        return events;
    }
    
    private Event createSaloonEvent() {
        Event event = new Event("You enter the bustling saloon...");
        event.addChoice(new Choice("Order drink, listen", ChoiceType.SOCIAL, 75));
        event.addChoice(new Choice("Challenge to cards", ChoiceType.RISKY, 55));
        event.addChoice(new Choice("Keep to yourself", ChoiceType.SAFE, 90));
        return event;
    }
    
    private Event createTravelEvent() {
        Event event = new Event("Traveling dusty roads...");
        event.addChoice(new Choice("Main road", ChoiceType.SAFE, 85));
        event.addChoice(new Choice("Risky shortcut", ChoiceType.RISKY, 60));
        event.addChoice(new Choice("Join caravan", ChoiceType.NEUTRAL, 90));
        return event;
    }
    
    // ==================== OUTCOME PROCESSING ====================
    private Outcome processChoice(Choice choice, Event event) {
        Humain player = gameState.getPlayerCharacter();
        int chance = calculateChance(choice, player);
        boolean success = random.nextInt(100) + 1 <= chance;
        
        Outcome outcome = new Outcome(success);
        
        if(player instanceof CowBoy) {
            processCowboyOutcome(outcome, choice, success, (CowBoy)player);
        } else if(player instanceof Sherif) {
            processSherifOutcome(outcome, choice, success, (Sherif)player);
        } else if(player instanceof Brigand) {
            processBrigandOutcome(outcome, choice, success, (Brigand)player);
        } else if(player instanceof Barman) {
            processBarmanOutcome(outcome, choice, success, (Barman)player);
        } else if(player instanceof DameBrigand) {
            processDameBrigandOutcome(outcome, choice, success, (DameBrigand)player);
        } else if(player instanceof DameStresse) {
            processDameOutcome(outcome, choice, success, (DameStresse)player);
        }
        
        if(random.nextInt(100) < 15) {
            outcome.addSpecialEvent(generateSpecialEvent());
        }
        
        if(random.nextInt(100) < 2 && choice.getType() == ChoiceType.RISKY) {
            outcome.setGameEnding(true);
            outcome.setMessage(outcome.getMessage() + "\n\n💀 YOUR STORY ENDS...");
        }
        
        return outcome;
    }
    
    private void processCowboyOutcome(Outcome o, Choice c, boolean s, CowBoy cowboy) {
        if(s) {
            if(c.getType() == ChoiceType.HEROIC) {
                DameStresse dame = (DameStresse)npcs.get("lady1");
                System.out.println();
                cowboy.libererDame(dame);
                dame.seFaireLiberer(cowboy);
                o.setMessage("✓ SUCCESS! Heroic rescue!");
                o.setReputationChange(random.nextInt(5) + 3);
            } else if(c.getType() == ChoiceType.AGGRESSIVE) {
                Brigand outlaw = (Brigand)npcs.get("outlaw1");
                System.out.println();
                cowboy.tirer(outlaw);
                o.setMessage("✓ SUCCESS! Quick draw wins!");
                o.setReputationChange(random.nextInt(4) + 2);
            } else {
                System.out.println();
                cowboy.boire();
                o.setMessage("✓ SUCCESS! " + genSuccess());
                o.setReputationChange(random.nextInt(3) + 1);
            }
        } else {
            o.setMessage("✗ FAILURE! " + genFailure());
            o.setReputationChange(-random.nextInt(3) - 1);
        }
    }
    
    private void processSherifOutcome(Outcome o, Choice c, boolean s, Sherif sheriff) {
        if(s) {
            if(c.getType() == ChoiceType.AGGRESSIVE || c.getType() == ChoiceType.TACTICAL) {
                Brigand outlaw = (Brigand)npcs.get("outlaw2");
                System.out.println();
                sheriff.rechercher(outlaw);
                sheriff.coffrer(outlaw);
                o.setMessage("✓ SUCCESS! Justice served!");
                o.setReputationChange(random.nextInt(5) + 3);
            } else {
                o.setMessage("✓ SUCCESS! " + genSuccess());
                o.setReputationChange(random.nextInt(4) + 2);
            }
        } else {
            o.setMessage("✗ FAILURE! " + genFailure());
            o.setReputationChange(-random.nextInt(3) - 1);
        }
    }
    
    private void processBrigandOutcome(Outcome o, Choice c, boolean s, Brigand brigand) {
        if(s) {
            if(c.getType() == ChoiceType.AGGRESSIVE && c.getDescription().contains("Kidnap")) {
                DameStresse dame = (DameStresse)npcs.get("lady2");
                System.out.println();
                brigand.kidnapperDame(dame);
                o.setMessage("✓ SUCCESS! Evil plan works!");
                o.setReputationChange(random.nextInt(4) + 2);
            } else {
                o.setMessage("✓ SUCCESS! " + genSuccess());
                o.setReputationChange(random.nextInt(3) + 1);
            }
        } else {
            if(random.nextInt(100) < 30) {
                System.out.println();
                brigand.seFaireEmprisonner(new CowBoy("Deputy", "Beer", 70, "stern"));
                o.setMessage("✗ FAILURE! Captured!");
                o.setReputationChange(-random.nextInt(5) - 2);
            } else {
                o.setMessage("✗ FAILURE! " + genFailure());
                o.setReputationChange(-random.nextInt(3) - 1);
            }
        }
    }
    
    private void processBarmanOutcome(Outcome o, Choice c, boolean s, Barman barman) {
        if(s) {
            if(c.getType() == ChoiceType.SOCIAL) {
                Humain customer = npcs.get("townsman");
                System.out.println();
                barman.sert(customer);
                barman.parle("Here's your drink, friend!");
                o.setMessage("✓ SUCCESS! Legendary hospitality!");
                o.setReputationChange(random.nextInt(4) + 2);
            } else {
                o.setMessage("✓ SUCCESS! " + genSuccess());
                o.setReputationChange(random.nextInt(3) + 1);
            }
        } else {
            o.setMessage("✗ FAILURE! " + genFailure());
            o.setReputationChange(-random.nextInt(2) - 1);
        }
    }
    
    private void processDameBrigandOutcome(Outcome o, Choice c, boolean s, DameBrigand dame) {
        if(s) {
            if(c.getType() == ChoiceType.AGGRESSIVE && c.getDescription().contains("rob")) {
                DameStresse victim = (DameStresse)npcs.get("lady1");
                System.out.println();
                dame.kidnapperDame(victim);
                o.setMessage("✓ SUCCESS! Wicked scheme works!");
                o.setReputationChange(random.nextInt(5) + 2);
            } else {
                o.setMessage("✓ SUCCESS! " + genSuccess());
                o.setReputationChange(random.nextInt(3) + 1);
            }
        } else {
            if(random.nextInt(100) < 25) {
                System.out.println();
                dame.seFaireEmprisonner(new CowBoy("Deputy", "Beer", 70, "stern"));
                o.setMessage("✗ FAILURE! Law catches you!");
                o.setReputationChange(-random.nextInt(4) - 2);
            } else {
                o.setMessage("✗ FAILURE! " + genFailure());
                o.setReputationChange(-random.nextInt(3) - 1);
            }
        }
    }
    
    private void processDameOutcome(Outcome o, Choice c, boolean s, DameStresse dame) {
        if(s) {
            o.setMessage("✓ SUCCESS! " + genSuccess());
            o.setReputationChange(random.nextInt(3) + 1);
        } else {
            if(random.nextInt(100) < 20) {
                Brigand badguy = (Brigand)npcs.get("outlaw1");
                System.out.println();
                dame.seFaireEnlever(badguy);
                o.setMessage("✗ FAILURE! Kidnapped!");
                o.setReputationChange(-random.nextInt(4) - 2);
            } else {
                o.setMessage("✗ FAILURE! " + genFailure());
                o.setReputationChange(-random.nextInt(2) - 1);
            }
        }
    }
    
    private int calculateChance(Choice choice, Humain player) {
        int base = choice.getSuccessChance();
        int bonus = 0;
        
        if(player instanceof CowBoy) {
            CowBoy cowboy = (CowBoy)player;
            if(choice.getType() == ChoiceType.AGGRESSIVE || choice.getType() == ChoiceType.HEROIC) {
                bonus = 10;
            }
            bonus += cowboy.getPopularite() / 20;
        } else if(player instanceof Sherif) {
            Sherif sheriff = (Sherif)player;
            if(choice.getType() == ChoiceType.TACTICAL || choice.getType() == ChoiceType.AGGRESSIVE) {
                bonus = 12;
            }
            bonus += sheriff.getPopularite() / 20;
        } else if(player instanceof Brigand) {
            if(choice.getType() == ChoiceType.RISKY || choice.getType() == ChoiceType.TACTICAL) {
                bonus = 15;
            }
        } else if(player instanceof Barman) {
            if(choice.getType() == ChoiceType.SOCIAL || choice.getType() == ChoiceType.SAFE) {
                bonus = 12;
            }
        } else if(player instanceof DameBrigand) {
            if(choice.getType() == ChoiceType.SOCIAL || choice.getType() == ChoiceType.RISKY) {
                bonus = 15;
            }
        } else if(player instanceof DameStresse) {
            if(choice.getType() == ChoiceType.SOCIAL || choice.getType() == ChoiceType.SAFE) {
                bonus = 10;
            }
        }
        
        return Math.min(95, base + bonus);
    }
    
    private String genSuccess() {
        String[] m = {"Respect earned!", "Town admires you!", "Skillfully done!", "All goes well!", "Reputation grows!"};
        return m[random.nextInt(m.length)];
    }
    
    private String genFailure() {
        String[] m = {"Things go wrong...", "Barely escape!", "Plan fails!", "Bad luck!", "Better next time!"};
        return m[random.nextInt(m.length)];
    }
    
    private String generateSpecialEvent() {
        String[] e = {"Mysterious map found!", "Stranger offers job", "Treasure rumors!", "Past returns", "Merchant arrives", "Canyon lights!", "Bounty hunter!", "Gold rush news!"};
        return "⚡ SPECIAL: " + e[random.nextInt(e.length)];
    }
    
    private void displayOutcome(Outcome outcome) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(outcome.getMessage());
        
        if(outcome.getReputationChange() != 0) {
            String change = outcome.getReputationChange() > 0 ? "+" : "";
            System.out.println("Reputation: " + change + outcome.getReputationChange());
        }
        
        for(String event : outcome.getSpecialEvents()) {
            System.out.println(event);
        }
        System.out.println("=".repeat(50));
    }
    
    private void displayEnding(Outcome outcome) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("GAME OVER");
        System.out.println("=".repeat(50));
        System.out.println("Your tale has ended.");
        System.out.println("Final Reputation: " + gameState.getReputation());
        System.out.println("\nThanks for playing!");
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
                System.out.print("Invalid: ");
            }
        }
    }
}

// Supporting classes remain the same
class GameState {
    private Humain playerCharacter;
    private int reputation;
    private List<String> events;
    
    public GameState() {
        this.reputation = 50;
        this.events = new ArrayList<>();
    }
    
    public void setPlayerCharacter(Humain c) { this.playerCharacter = c; }
    public Humain getPlayerCharacter() { return playerCharacter; }
    public int getReputation() { return reputation; }
    public void setReputation(int r) { this.reputation = r; }
    
    public void updateFromOutcome(Outcome o) {
        reputation += o.getReputationChange();
        reputation = Math.max(0, Math.min(100, reputation));
        events.addAll(o.getSpecialEvents());
    }
}

class Event {
    private String description;
    private List<Choice> choices;
    
    public Event(String d) {
        this.description = d;
        this.choices = new ArrayList<>();
    }
    
    public void addChoice(Choice c) { choices.add(c); }
    public String getDescription() { return description; }
    public List<Choice> getChoices() { return choices; }
}

class Choice {
    private String description;
    private ChoiceType type;
    private int successChance;
    
    public Choice(String d, ChoiceType t, int s) {
        this.description = d;
        this.type = t;
        this.successChance = s;
    }
    
    public String getDescription() { return description; }
    public ChoiceType getType() { return type; }
    public int getSuccessChance() { return successChance; }
}

enum ChoiceType {
    SAFE, NEUTRAL, SOCIAL, TACTICAL, RISKY, AGGRESSIVE, HEROIC
}

class Outcome {
    private boolean success;
    private String message;
    private int reputationChange;
    private List<String> specialEvents;
    private boolean gameEnding;
    
    public Outcome(boolean s) {
        this.success = s;
        this.specialEvents = new ArrayList<>();
        this.gameEnding = false;
    }
    
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public void setMessage(String m) { this.message = m; }
    public int getReputationChange() { return reputationChange; }
    public void setReputationChange(int c) { this.reputationChange = c; }
    public void addSpecialEvent(String e) { specialEvents.add(e); }
    public List<String> getSpecialEvents() { return specialEvents; }
    public boolean isGameEnding() { return gameEnding; }
    public void setGameEnding(boolean e) { this.gameEnding = e; }
}