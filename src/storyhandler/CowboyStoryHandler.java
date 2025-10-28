package storyhandler;

import characters.Humain;
import characters.DameStresse;
import characters.DameBrigand;
import characters.CowBoy;
import characters.Brigand;
import characters.Barman;
import java.util.*;
import utils.InputUtils;


/**
 * CowboyStoryHandler - Manages the complete Cowboy storyline
 * Follows a structured narrative arc with choices and consequences
 */
public class CowboyStoryHandler {
    private CowBoy player;
    private Scanner scanner;
    private Random random;
    private Map<String, Humain> npcs;
    private int reputation;
    private boolean hasGun;
    private boolean hasHorse;
    private StoryState currentState;
    
    // Track story progression
    private enum StoryState {
        ACT1_ARRIVAL,
        ACT1_BAR_CHOICE,
        ACT2_LADY_ENCOUNTER,
        ACT2_REDEMPTION,
        ACT3_DESERT_RIDE,
        ACT3_DAME_BRIGAND,
        ACT4_FINAL_CONFRONTATION,
        ACT5_RESOLUTION,
        GAME_OVER
    }
    
    public CowboyStoryHandler(CowBoy player, Scanner scanner, Map<String, Humain> npcs) {
        this.player = player;
        this.scanner = scanner;
        this.random = new Random();
        this.npcs = npcs;
        this.reputation = player.getPopularite();
        this.hasGun = true;
        this.hasHorse = true;
        this.currentState = StoryState.ACT1_ARRIVAL;
    }
    
    /**
     * Main story orchestrator
     */
    public void playStory() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("THE TALE OF " + player.quelEstTonNom().toUpperCase());
        System.out.println("=".repeat(60) + "\n");
        
        // Story progression
        act1Arrival();
        
        if(currentState != StoryState.GAME_OVER) {
            act1BarChoice();
        }
        
        if(currentState != StoryState.GAME_OVER) {
            act2Crossroads();
        }
        
        if(currentState != StoryState.GAME_OVER) {
            act3DesertRide();
        }
        
        if(currentState != StoryState.GAME_OVER) {
            act4FinalConfrontation();
        }
        
        if(currentState != StoryState.GAME_OVER) {
            act5Resolution();
        }
        
        displayFinalStats();
    }
    
    // ==================== ACT 1 ====================
    private void act1Arrival() {
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("          ACT 1 — A DRINK AND A WHISPER");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("🏜️  SCENE 1: ARRIVAL\n");
        System.out.println("You ride into DustyTown under the blistering sun.");
        System.out.println("The streets are half-empty, dust swirling around your boots.");
        System.out.println("You push open the saloon doors...\n");
        
        pause();
        
        Barman joe = (Barman)npcs.get("barman");
        System.out.println("The piano stops. Everyone stares.\n");
        
        joe.parle("Well, I'll be damned... Ain't seen a cowboy 'round these");
        System.out.println("       parts in months. What brings you here, stranger?");
        
        player.parle("Just ridin' through. Thought I'd wet my throat.");
        
        System.out.println("\n(Joe smiles nervously, pours whiskey)\n");
        joe.sert(player);
        
        joe.parle("On the house... Name's Joe. Used to be a lively town");
        System.out.println("       'fore Jesse the Scarred started causing trouble.");
        
        currentState = StoryState.ACT1_BAR_CHOICE;
    }
    
    private void act1BarChoice() {
        System.out.println("\n🥃 SCENE 2: THE CHOICE AT THE BAR\n");
        System.out.println("Joe slides you a glass and starts whispering gossip.");
        System.out.println("You overhear that a lady's sister has been kidnapped by Jesse.\n");
        
        Barman joe = (Barman)npcs.get("barman");
        player.boire();
        
System.out.println("🎯 CHOICE 1:");
System.out.println("   (1) Sit quietly, drink, and listen to gossip");
System.out.println("   (2) Speak up when the lady approaches you for help\n");

int choice = InputUtils.getValidatedNumericChoice(scanner, 2);

if (choice == 1) {
    pathA_SitQuietly();
} else {
    pathB_TalkToLady();
}
        
        currentState = StoryState.ACT2_LADY_ENCOUNTER;
    }
    
    private void pathA_SitQuietly() {
        System.out.println("\n💔 YOU SIT QUIETLY\n");
        System.out.println("You sip your drink while people talk about you.");
        System.out.println("They say, 'He's just another drifter. Won't lift a finger.'");
        System.out.println("You feel their eyes judging you.");
        System.out.println("The music starts again, but the town feels colder.\n");
        
        pause();
        
        System.out.println("Later, as guilt builds up, you overhear more gossip");
        System.out.println("about Jesse's hideout...\n");
        
        player.parle("Alright, maybe it's time someone did somethin'");
        System.out.println("       'bout this Jesse fella.");
        
        reputation -= 1;
        System.out.println("\n[Reputation -1 for delay, but redemption possible]");
        
        currentState = StoryState.ACT2_REDEMPTION;
    }
    
    private void pathB_TalkToLady() {
        System.out.println("\n💃 YOU TALK TO THE LADY\n");
        
        DameStresse clara = (DameStresse)npcs.get("lady1");
        
        clara.parle("My sister's been taken by that monster, Jesse!");
        System.out.println("       You're the only one brave enough to help.");
        
System.out.println("\n🎯 CHOICE 2:");
System.out.println("   (1) Accept her plea and promise to help ");
System.out.println("   (2) Refuse politely - 'Ain't my business, ma'am'\n");

int choice = InputUtils.getValidatedNumericChoice(scanner, 2);

if (choice == 1) {
    player.parle("I'll bring your sister back safe, ma'am. You have my word.");
    clara.parle("Thank you! God bless you, cowboy!");
    reputation += 2;
    System.out.println("\n[Reputation +2]");
} else {
    player.parle("Ain't my business, ma'am. Sorry.");
    System.out.println("\n(Clara's face falls. The townsfolk whisper in disappointment.)\n");
    reputation -= 2;
    System.out.println("[Reputation -2]");
    System.out.println("You return to sitting quietly...\n");
    pathA_SitQuietly();
}
    }
    
    // ==================== ACT 2 ====================
    private void act2Crossroads() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 2 — CROSSROADS OF HONOR");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("You gather your gear and prepare to ride out.");
        System.out.println("The sun is high, and the desert stretches endlessly before you.\n");
        
        player.tirerEnLair();
        System.out.println("(You check your gun one last time)\n");
        
        Barman joe = (Barman)npcs.get("barman");
        joe.parle("You be careful out there, partner. Jesse's a snake.");
        
        pause();
        
        currentState = StoryState.ACT3_DESERT_RIDE;
    }
    
    // ==================== ACT 3 ====================
    private void act3DesertRide() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 3 — THE RIDE THROUGH THE DESERT");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("You follow faint wagon tracks through the sand,");
        System.out.println("the wind whispering across the dunes.\n");
        
        pause();
        
        System.out.println("Midway, a shadowy figure appears...\n");
        
        DameBrigand rosa = (DameBrigand)npcs.get("femme");
        rosa.sePresenter();
        
        System.out.println();
        rosa.parle("Well, well... if it ain't the town's shiny hero.");
        System.out.println("       Lookin' for trouble, cowboy?");
        System.out.println("(She circles you like a cat.)\n");
        
System.out.println("🎯 CHOICE 3:");
System.out.println("   (1) Flirt back - 'Maybe I already found it.'");
System.out.println("   (2) Stay cautious - 'Where's Jesse?'\n");

int choice = InputUtils.getValidatedNumericChoice(scanner, 2);

if (choice == 1) {
    path_FlirtWithRosa(rosa);
} else {
    path_StayCautious(rosa);
}
        
        currentState = StoryState.ACT4_FINAL_CONFRONTATION;
    }
    
    private void path_FlirtWithRosa(DameBrigand rosa) {
        System.out.println("\n💋 YOU FLIRT BACK\n");
        
        player.parle("Maybe I already found it.");
        
        System.out.println("\nShe leans in close... then pulls a knife!\n");
        System.out.println("🔪 You're ambushed! Quick - defend yourself!\n");
        
        pause();
        
        // 50% chance fight
        int roll = random.nextInt(100) + 1;
        int chance = 50 + (reputation / 10); // Reputation affects outcome
        
        System.out.println("[Rolling... Your chance: " + chance + "%]");
        
        if(roll <= chance) {
            System.out.println("\n✓ WIN!\n");
            player.tirer(new Brigand("Rosa", "Wine", 0, 600, "seductive", false));
            System.out.println("You disarm her and pin her to the ground.\n");
            
            rosa.parle("Alright, alright! You win, cowboy!");
            System.out.println("       Jesse's at Dead Man's Rock canyon. Now let me go!");
            
            reputation += 1;
        } else {
            System.out.println("\n✗ LOSE!\n");
            System.out.println("She stabs your shoulder and steals your gun and horse!");
            System.out.println("You collapse in the sand, bleeding...\n");
            
            hasGun = false;
            hasHorse = false;
            reputation -= 2;
            
            System.out.println("💀 GAME OVER: 'Don't trust dirty bitches.'\n");
            currentState = StoryState.GAME_OVER;
        }
    }
    
    private void path_StayCautious(DameBrigand rosa) {
        System.out.println("\n🛡️ YOU STAY CAUTIOUS\n");
        
        player.parle("Where's Jesse?");
        
        System.out.println("\n(You keep your hand on your holster.)\n");
        System.out.println("She eyes you, then smirks with respect.\n");
        
        rosa.parle("Smart cowboy. Check the canyon near Dead Man's Rock.");
        System.out.println("       But watch your back... Jesse don't play fair.");
        
        System.out.println("\n(She disappears into the dust.)\n");
        reputation += 1;
        System.out.println("[Reputation +1 for staying sharp]");
    }
    
    // ==================== ACT 4 ====================
    private void act4FinalConfrontation() {
        if(currentState == StoryState.GAME_OVER) return;
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 4 — THE FINAL CONFRONTATION");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        if(!hasHorse) {
            System.out.println("You limp through the desert on foot, wounded and weak.");
            System.out.println("By the time you reach Dead Man's Rock, it's too late...\n");
            System.out.println("💀 You collapse from exhaustion. The desert claims another soul.\n");
            currentState = StoryState.GAME_OVER;
            return;
        }
        
        System.out.println("You find Jesse's camp at sunset.");
        System.out.println("He's laughing by a campfire with Clara's sister tied up nearby.\n");
        
        Brigand jesse = (Brigand)npcs.get("outlaw1");
        DameStresse sister = (DameStresse)npcs.get("lady2");
        
        jesse.parle("Well look who rode in... another hero lookin' to die.");
        
System.out.println("🎯 CHOICE 4:");
System.out.println("   (1) Challenge Jesse to a duel");
System.out.println("   (2) Ambush him quietly at night");
System.out.println("   (3) Try to negotiate peace\n");

int choice = InputUtils.getValidatedNumericChoice(scanner, 3);

switch (choice) {
    case 1 -> path_Duel(jesse, sister);
    case 2 -> path_Ambush(jesse, sister);
    case 3 -> path_Negotiate(jesse, sister);
}

    }
    
    private void path_Duel(Brigand jesse, DameStresse sister) {
        System.out.println("\n🔫 DUEL AT SUNSET\n");
        System.out.println("A tense standoff. Wind blows through the canyon.");
        System.out.println("Your hand hovers over your holster...\n");
        
        player.tirerEnLair();
        System.out.println("(Practice shot to intimidate)\n");
        
        pause();
        
        System.out.println("The moment comes. You draw—\n");
        
        int roll = random.nextInt(100) + 1;
        int chance = 55 + (reputation / 5) + (hasGun ? 0 : -30);
        
        System.out.println("[Rolling... Your chance: " + chance + "%]");
        
        if(roll <= chance) {
            System.out.println("\n✓ WIN!\n");
            player.tirer(jesse);
            System.out.println("\nJesse drops, mortally wounded.\n");
            
            jesse.parle("You... you're faster than I thought...");
            System.out.println("\n(Jesse collapses)\n");
            
            player.libererDame(sister);
            sister.seFaireLiberer(player);
            
            reputation += 5;
            System.out.println("[Reputation +5 for honorable victory]");
            currentState = StoryState.ACT5_RESOLUTION;
        } else {
            System.out.println("\n✗ LOSE!\n");
            System.out.println("Jesse's bullet strikes you in the chest.");
            System.out.println("You fall to your knees, vision fading...\n");
            
            player.parle("At least... I tried...");
            
            System.out.println("\n💀 Fade to black.");
            System.out.println("   'The desert remembers your courage.'\n");
            currentState = StoryState.GAME_OVER;
        }
    }
    
    private void path_Ambush(Brigand jesse, DameStresse sister) {
        System.out.println("\n🌙 AMBUSH AT NIGHT\n");
        System.out.println("You wait until darkness falls.");
        System.out.println("You sneak through the shadows like a ghost...\n");
        
        pause();
        
        int roll = random.nextInt(100) + 1;
        int chance = 70 + (reputation / 5);
        
        System.out.println("[Rolling... Your chance: " + chance + "%]");
        
        if(roll <= chance) {
            System.out.println("\n✓ WIN!\n");
            System.out.println("You knock Jesse out cold from behind!");
            System.out.println("He never saw it coming.\n");
            
            player.libererDame(sister);
            sister.seFaireLiberer(player);
            
            System.out.println("You tie him up and deliver him alive to the sheriff.\n");
            reputation += 7;
            System.out.println("[Reputation +7 for tactical brilliance!]");
            currentState = StoryState.ACT5_RESOLUTION;
        } else {
            System.out.println("\n✗ LOSE!\n");
            System.out.println("One of his men spots you!");
            System.out.println("Gunfire erupts!\n");
            
            // Give one more chance
            System.out.println("🔫 Fight back or flee?");
            System.out.println("   (A) Stand and fight");
            System.out.println("   (B) Retreat\n");
            System.out.print("Your choice (A/B): ");
            
            String fightChoice = scanner.nextLine().trim().toUpperCase();
            
            if(fightChoice.equals("A")) {
                path_Duel(jesse, sister); // Goes to duel sequence
            } else {
                System.out.println("\nYou flee into the night, wounded and ashamed.\n");
                reputation -= 5;
                System.out.println("💀 The lady is never rescued.\n");
                currentState = StoryState.GAME_OVER;
            }
        }
    }
    
    private void path_Negotiate(Brigand jesse, DameStresse sister) {
        System.out.println("\n🤝 NEGOTIATION\n");
        System.out.println("You holster your gun and raise your hands.\n");
        
        player.parle("Ain't no need for more blood. Let her go.");
        
        jesse.parle("You got guts, cowboy... but guts ain't enough.");
        
        pause();
        
        int roll = random.nextInt(100) + 1;
        int chance = 40 + (reputation / 3);
        
        System.out.println("[Rolling... Your chance: " + chance + "%]");
        
        if(roll <= chance) {
            System.out.println("\n✓ WIN!\n");
            System.out.println("Jesse sees the wisdom in your words.");
            System.out.println("He's tired of running. He surrenders.\n");
            
            player.libererDame(sister);
            sister.seFaireLiberer(player);
            
            System.out.println("You escort him back to town for trial.\n");
            reputation += 8;
            System.out.println("[Reputation +8 for peaceful resolution!]");
            currentState = StoryState.ACT5_RESOLUTION;
        } else {
            System.out.println("\n✗ LOSE!\n");
            System.out.println("Jesse fakes surrender... then lunges with a knife!\n");
            
            System.out.println("You're stabbed in the gut. Blood pours out.\n");
            player.parle("Shoulda... known better...");
            
            System.out.println("\n💀 You die trusting a snake.\n");
            currentState = StoryState.GAME_OVER;
        }
    }
    
    // ==================== ACT 5 ====================
    private void act5Resolution() {
        if(currentState == StoryState.GAME_OVER) return;
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 5 — THE RETURN");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        DameStresse clara = (DameStresse)npcs.get("lady1");
        DameStresse sister = (DameStresse)npcs.get("lady2");
        Barman joe = (Barman)npcs.get("barman");
        
        System.out.println("You ride back into DustyTown, the rescued lady behind you.");
        System.out.println("Clara rushes out, tears streaming down her face.\n");
        
        clara.parle("You... you brought her back! Thank you! Thank you!");
        
        System.out.println("\n(The sisters embrace, crying with joy.)\n");
        
        sister.changerRobe(player);
        sister.parle("You're a true hero, " + player.quelEstTonNom() + ".");
        
        System.out.println("\nThe townsfolk lift you on their shoulders, cheering your name!");
        System.out.println("Music fills the air. Children dance. Hope returns.\n");
        
        joe.sert(player);
        joe.parle("This one's truly on the house, legend.");
        
        player.boire();
        player.tirerEnLair();
        
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("You ain't just a cowboy anymore.");
        System.out.println("You're DustyTown's LEGEND.");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        pause();
        
        System.out.println("(Screen fades — cowboy rides into the sunset.)\n");
        System.out.println("🌅 THE END 🌅\n");
    }
    
    private void displayFinalStats() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          FINAL STATISTICS");
        System.out.println("=".repeat(60));
        System.out.println("Cowboy: " + player.quelEstTonNom());
        System.out.println("Final Reputation: " + reputation);
        System.out.println("Status: " + (currentState == StoryState.GAME_OVER ? "DEAD 💀" : "LEGEND 🌟"));
        System.out.println("Gun: " + (hasGun ? "Yes ✓" : "Lost ✗"));
        System.out.println("Horse: " + (hasHorse ? "Yes ✓" : "Lost ✗"));
        System.out.println("=".repeat(60) + "\n");
        
        if(currentState != StoryState.GAME_OVER) {
            System.out.println("🎖️  ACHIEVEMENTS UNLOCKED:");
            if(reputation >= 10) System.out.println("   ⭐ Town Hero");
            if(reputation >= 15) System.out.println("   ⭐ Living Legend");
            if(hasGun && hasHorse) System.out.println("   ⭐ Survivor");
            System.out.println();
        } else {
            System.out.println("💀 The desert remembers your name...\n");
        }
    }
    /**
 * Keeps asking until a valid choice is entered.
 */
    
    
    private void pause() {
        try {
            Thread.sleep(1500);
        } catch(InterruptedException e) {}
    }
    
    // Getter for reputation (for integration with GameManager)
    public int getFinalReputation() {
        return reputation;
    }
    
    public boolean isAlive() {
        return currentState != StoryState.GAME_OVER;
    }
}