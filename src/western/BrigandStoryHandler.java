package western;

import java.util.*;

/**
 * BrigandStoryHandler - Complete narrative-driven story for Brigand characters
 * Two branching paths: Bank Heist and Kidnapping
 */
public class BrigandStoryHandler {
    private Brigand player;
    private Scanner scanner;
    private Random random;
    private Map<String, Humain> npcs;
    private int reputation;
    private boolean alive;
    private boolean merciful;
    private boolean violent;
    private String chosenPath;
    private boolean hasGun;
    private boolean hasHorse;
    private StoryState currentState;
    
    private enum StoryState {
        INTRO,
        PATH_CHOICE,
        ACT1,
        ACT2,
        ACT3,
        EPILOGUE,
        GAME_OVER
    }
    
    public BrigandStoryHandler(Brigand player, Scanner scanner, Map<String, Humain> npcs) {
        this.player = player;
        this.scanner = scanner;
        this.random = new Random();
        this.npcs = npcs;
        this.reputation = 30;
        this.alive = true;
        this.merciful = false;
        this.violent = false;
        this.hasGun = true;
        this.hasHorse = true;
        this.currentState = StoryState.INTRO;
    }
    
    public void playStory() {
        displayIntro();
        chooseStoryPath();
        
        if(chosenPath.equals("heist")) {
            playHeistPath();
        } else {
            playKidnappingPath();
        }
        
        if(currentState != StoryState.GAME_OVER) {
            displayEpilogue();
        }
    }
    
    // ==================== INTRODUCTION ====================
    private void displayIntro() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("🦹 THE BRIGAND'S PATH 🦹");
        System.out.println("═".repeat(60));
        System.out.println("\nYou are " + player.quelEstTonNom() + ", Diego le Fumant");
        System.out.println("A notorious outlaw known for your silver revolver and quick tongue.");
        
        player.sePresenter();
        
        System.out.println("\nOnce a ranch worker, now your name is carved into every");
        System.out.println("sheriff's Wanted board from Rio Verde to Dust Valley.");
        System.out.println("\nYour legend begins here...");
        System.out.println("═".repeat(60));
        pause();
        currentState = StoryState.PATH_CHOICE;
    }
    
    private void chooseStoryPath() {
        System.out.println("\n🎯 CHOOSE YOUR PATH:");
        System.out.println("═".repeat(60));
        System.out.println("(1) 💰 THE BANK HEIST");
        System.out.println("    Rob the Dust Valley Bank in broad daylight");
        System.out.println("    Risk: HIGH | Reward: FORTUNE");
        System.out.println();
        System.out.println("(2) 💃 THE KIDNAPPING");
        System.out.println("    Kidnap Clara, the mayor's daughter");
        System.out.println("    Risk: EXTREME | Reward: RANSOM");
        System.out.println("═".repeat(60));
        
        int choice = getValidatedNumericChoice(2);
        chosenPath = (choice == 1) ? "heist" : "kidnapping";
        
        System.out.println("\n🔥 You've chosen: " + (chosenPath.equals("heist") ? "THE BANK HEIST" : "THE KIDNAPPING"));
        pause();
        currentState = StoryState.ACT1;
    }
    
    // ==================== HEIST PATH ====================
    private void playHeistPath() {
        heistAct1_Planning();
        if(currentState == StoryState.GAME_OVER) return;
        
        heistAct2_TheRobbery();
        if(currentState == StoryState.GAME_OVER) return;
        
        heistAct3_Showdown();
    }
    
    private void heistAct1_Planning() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 1 — THE PLANNING");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("🏜️ SCENE 1: THE SALOON\n");
        System.out.println("You ride into Dust Valley at dusk, hat pulled low.");
        System.out.println("The saloon doors creak as you enter...\n");
        pause();
        
        Barman joe = (Barman)npcs.get("barman");
        joe.sePresenter();
        System.out.println();
        
        joe.parle("What can I get ya, stranger?");
        
        System.out.println("\nYou glance around. The bank across the street catches your eye.");
        System.out.println("Two guards. Old vault. This could work...\n");
        
        joe.sert(player);
        System.out.println("\n(You sip slowly, plotting)\n");
        
        System.out.println("🎯 CHOICE 1:");
        System.out.println("   (1) Ask the barman about the bank guards");
        System.out.println("   (2) Keep quiet and observe silently\n");
        
        int choice = getValidatedNumericChoice(2);
        
        if(choice == 1) {
            player.parle("Say, Joe... them guards at the bank. They tough?");
            joe.parle("Tough enough. But they switch shifts at noon.");
            System.out.println("\n[Intelligence gained: Best time to strike is noon]");
            reputation += 1;
        } else {
            System.out.println("\nYou sit in silence, watching the guards' patterns.");
            System.out.println("One yawns. The other checks his pocket watch.");
            System.out.println("\n[You notice they're not very alert]");
        }
        
        pause();
        System.out.println("\n🌙 SCENE 2: MIDNIGHT PREPARATION\n");
        System.out.println("Back at your camp, you load your revolver.");
        System.out.println("Tomorrow, everything changes...\n");
        
        System.out.println("Your horse, Bandito, snorts nervously.");
        System.out.println("Even he knows what's coming.\n");
        pause();
        
        currentState = StoryState.ACT2;
    }
    
    private void heistAct2_TheRobbery() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 2 — THE ROBBERY AT DUST VALLEY BANK");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("It's noon. The sun burns red over the saloon roofs.");
        System.out.println("You walk into Dust Valley Bank, pretending to deposit gold...\n");
        pause();
        
        System.out.println("Then suddenly—BANG! You pull your gun!\n");
        System.out.println("💬 \"Everyone on the ground! NOW!\"");
        System.out.println("Screams fill the air. The teller freezes.\n");
        
        System.out.println("🎯 CHOICE 2:");
        System.out.println("   (1) Threaten the banker and steal all the gold");
        System.out.println("   (2) Blow open the vault with dynamite\n");
        
        int choice = getValidatedNumericChoice(2);
        
        if(choice == 1) {
            System.out.println("\n💬 \"Fill the bags, nice and slow!\"");
            System.out.println("You wave your silver revolver at the terrified banker.");
            System.out.println("He shakily hands over bags of gold coins.\n");
            reputation -= 5;
            violent = true;
            System.out.println("[Reputation -5: Town fears you]");
        } else {
            System.out.println("\n💥 BOOM! The dynamite explodes!");
            System.out.println("The vault door flies open. Gold glitters in the smoke.");
            System.out.println("Debris rains down. Some civilians are injured.\n");
            reputation -= 10;
            violent = true;
            System.out.println("[Reputation -10: Seen as ruthless]");
        }
        
        pause();
        System.out.println("\n🔔 CLANG! CLANG! CLANG! The alarm bell rings!");
        System.out.println("Sheriff Cole bursts through the door, revolver drawn!\n");
        
        Sherif sheriff = (Sherif)npcs.get("sheriff");
        sheriff.sePresenter();
        System.out.println();
        
        sheriff.parle("Drop your weapon, outlaw!");
        sheriff.rechercher(player);
        
        pause();
        System.out.println("\n🎯 CHOICE 3:");
        System.out.println("   (1) Duel with the Sheriff");
        System.out.println("   (2) Escape on your horse, Bandito\n");
        
        choice = getValidatedNumericChoice(2);
        
        if(choice == 1) {
            System.out.println("\n🔫 \"Let's dance, Sheriff!\"");
            System.out.println("You both draw... BANG! BANG!\n");
            pause();
            
            int roll = random.nextInt(100) + 1;
            int chance = 60 + (reputation / 5);
            System.out.println("[Rolling... Your chance: " + chance + "%]");
            
            if(roll <= chance) {
                System.out.println("\n✓ Your shot hits true!");
                System.out.println("The sheriff clutches his shoulder, wounded but alive.");
                sheriff.parle("Someone... get Billy the Brave!");
                reputation -= 8;
                violent = true;
                System.out.println("\n[Reputation -8: You shot a lawman]");
            } else {
                System.out.println("\n✗ The sheriff's bullet finds its mark!");
                System.out.println("You stagger back, blood pouring from your chest.\n");
                player.parle("Damn... too slow...");
                System.out.println("\n💀 You collapse. The law wins this day.");
                currentState = StoryState.GAME_OVER;
                return;
            }
        } else {
            System.out.println("\n🐴 You leap onto Bandito and gallop away!");
            System.out.println("Bullets whiz past your head. You ride like the wind.");
            sheriff.parle("Get me Billy the Brave!");
            reputation -= 3;
            System.out.println("\n[Reputation -3: You're now a wanted man]");
        }
        
        pause();
        System.out.println("\n🏜️ You make it to Coyote Canyon, your hideout.");
        System.out.println("You hide the gold and reload your revolver.");
        System.out.println("But you know... he's coming.\n");
        System.out.println("Billy the Brave. The legendary cowboy.\n");
        pause();
        
        currentState = StoryState.ACT3;
    }
    
    private void heistAct3_Showdown() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 3 — COYOTE CANYON SHOWDOWN");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("The sunset paints the canyon blood-red.");
        System.out.println("Then you hear it... hoofbeats.\n");
        pause();
        
        System.out.println("Billy the Brave rides in, revolver spinning.");
        CowBoy billy = new CowBoy("Billy the Brave", "Whiskey", 95, "heroic");
        billy.sePresenter();
        System.out.println();
        
        billy.parle("You stole from my town. Now it's justice... or death.");
        billy.tirerEnLair();
        System.out.println("(A warning shot cracks the air)\n");
        
        pause();
        System.out.println("🎯 FINAL CHOICE:");
        System.out.println("   (1) Ambush Billy and shoot first");
        System.out.println("   (2) Challenge him to a fair duel");
        System.out.println("   (3) Offer him half the gold to join you\n");
        
        int choice = getValidatedNumericChoice(3);
        
        if(choice == 1) {
            System.out.println("\n💥 You draw and fire without warning!");
            billy.tirer(player);
            System.out.println("But Billy is faster. He shoots your gun from your hand!\n");
            pause();
            
            System.out.println("The sheriff's posse arrives moments later.");
            System.out.println("You fight to the last breath, but you're outnumbered.\n");
            
            Sherif sheriff = (Sherif)npcs.get("sheriff");
            player.seFaireEmprisonner(billy);
            sheriff.coffrer(player);
            
            pause();
            System.out.println("\n💀 You die in a blaze of gunfire...");
            System.out.println("But your legend lives forever.");
            System.out.println("\n🎭 Diego le Fumant - The Brigand Who Never Surrendered");
            alive = false;
            reputation += 10;
            currentState = StoryState.GAME_OVER;
            
        } else if(choice == 2) {
            System.out.println("\n🔫 \"Let's do this fair and square, cowboy.\"");
            System.out.println("You both walk 10 paces... turn...\n");
            pause();
            
            System.out.println("⚡ BANG!\n");
            pause();
            
            int roll = random.nextInt(100) + 1;
            int chance = (violent && !merciful) ? 40 : 60;
            System.out.println("[Rolling... Your chance: " + chance + "%]");
            
            if(roll <= chance) {
                player.tirer(billy);
                billy.libererDame((DameStresse)npcs.get("lady1"));
                
                System.out.println("\nYour bullet grazes Billy's arm. His misses.");
                System.out.println("You lower your gun.\n");
                player.parle("Go. Tell them Diego spared you.");
                
                billy.parle("You're not just an outlaw... you're something else.");
                System.out.println("\n✨ You ride off into legend - the merciful brigand");
                alive = true;
                reputation += 15;
                merciful = true;
            } else {
                billy.tirer(player);
                System.out.println("\nBilly's bullet finds its mark.");
                System.out.println("You fall to the dusty ground, vision fading...\n");
                
                billy.parle("Justice... is served.");
                player.parle("At least... I died with honor...");
                System.out.println("\n💀 You die knowing you lived as an outlaw... and died as one.");
                System.out.println("🎭 Peace returns to Dust Valley");
                alive = false;
                reputation -= 5;
                currentState = StoryState.GAME_OVER;
            }
            
        } else {
            System.out.println("\n💰 \"Billy... what if we split this gold?\"");
            player.parle("Forget the law. We could own this territory together.");
            pause();
            
            billy.parle("...");
            System.out.println("Billy's eyes narrow. He considers...\n");
            pause();
            
            int roll = random.nextInt(100) + 1;
            int chance = violent ? 30 : 60;
            System.out.println("[Rolling... Your chance: " + chance + "%]");
            
            if(roll <= chance) {
                System.out.println("\n🤝 \"Alright, Diego. But one betrayal... and you're dead.\"");
                System.out.println("You shake hands. An unholy alliance is born.\n");
                billy.boire();
                pause();
                
                System.out.println("✨ Together, you become legendary outlaws...");
                System.out.println("Until one day, one of you breaks the pact.");
                System.out.println("\n🎭 The Partnership That Shook The West");
                alive = true;
                reputation += 20;
            } else {
                billy.tirer(player);
                System.out.println("\n🔫 \"I don't deal with murderers.\"");
                System.out.println("Billy shoots. You fall.\n");
                
                player.parle("Shoulda... known better...");
                System.out.println("\n💀 Your greed was your downfall.");
                System.out.println("Billy takes the gold back to Dust Valley.");
                System.out.println("\n🎭 The Brigand Who Trusted No One");
                alive = false;
                reputation -= 10;
                currentState = StoryState.GAME_OVER;
            }
        }
    }
    
    // ==================== KIDNAPPING PATH ====================
    private void playKidnappingPath() {
        kidnappingAct1_ThePlan();
        if(currentState == StoryState.GAME_OVER) return;
        
        kidnappingAct2_TheChase();
        if(currentState == StoryState.GAME_OVER) return;
        
        kidnappingAct3_FinalReckoning();
    }
    
    private void kidnappingAct1_ThePlan() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 1 — THE WOMAN IN RED");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("🍺 SCENE 1: THE SILVER MOON SALOON\n");
        System.out.println("At the Silver Moon Saloon, laughter fills the air.");
        System.out.println("Piano music drifts through the smoke.\n");
        pause();
        
        Barman joe = (Barman)npcs.get("barman");
        joe.sePresenter();
        joe.sert(player);
        System.out.println();
        
        System.out.println("You sip your drink, eyes scanning the room...");
        System.out.println("Then you spot her.\n");
        pause();
        
        DameStresse clara = new DameStresse("Clara", "Wine", "free", "crimson");
        clara.sePresenter();
        System.out.println();
        
        System.out.println("Smart, fearless, and far too curious for her own good.");
        System.out.println("She wears a crimson dress that matches her spirit.");
        System.out.println("More importantly—she's the mayor's daughter.\n");
        
        player.parle("This ransom will set me up for life...");
        pause();
        
        System.out.println("\n🎯 CHOICE 1:");
        System.out.println("   (1) Kidnap her quietly as she leaves the bar");
        System.out.println("   (2) Cause a distraction and take her by force\n");
        
        int choice = getValidatedNumericChoice(2);
        
        if(choice == 1) {
            System.out.println("\n🌙 You wait in the shadows as she steps outside...");
            player.parle("Don't scream...");
            System.out.println("(You cover her mouth)");
            System.out.println("She struggles, but you're stronger.\n");
            player.kidnapperDame(clara);
            reputation -= 8;
            System.out.println("[Reputation -8: Quiet kidnapping]");
        } else {
            System.out.println("\n💥 BANG! You fire your gun into the ceiling!");
            player.parle("Everyone DOWN!");
            System.out.println("\nIn the chaos, you grab Clara and drag her out.");
            System.out.println("She screams and fights back.\n");
            player.kidnapperDame(clara);
            reputation -= 15;
            violent = true;
            System.out.println("[Reputation -15: Violent kidnapping]");
        }
        
        pause();
        joe.parle("Someone get the sheriff! And Billy the Brave!");
        System.out.println("\n🔥 The hunt begins.\n");
        pause();
        
        currentState = StoryState.ACT2;
    }
    
    private void kidnappingAct2_TheChase() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 2 — THE CHASE THROUGH THE DESERT");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        System.out.println("You ride through the night with Clara tied to Bandito's saddle.");
        System.out.println("She keeps cursing you, calling you every name under the sun.\n");
        
        DameStresse clara = new DameStresse("Clara", "Wine", "kidnapped", "crimson");
        clara.parle("You're a coward! Let me go!");
        player.parle("Quiet, lady. You're worth your weight in gold.");
        
        System.out.println("\nYou ignore her. The stars wheel overhead.\n");
        pause();
        
        System.out.println("Suddenly—BANG! BANG!");
        System.out.println("Gunshots echo across the desert!");
        System.out.println("Billy and the sheriff are on your trail!\n");
        
        Sherif sheriff = (Sherif)npcs.get("sheriff");
        sheriff.rechercher(player);
        
        pause();
        System.out.println("\n🎯 CHOICE 2:");
        System.out.println("   (1) Ride faster through the canyon");
        System.out.println("   (2) Hide in an abandoned mine\n");
        
        int choice = getValidatedNumericChoice(2);
        
        if(choice == 1) {
            System.out.println("\n🐴 You spur Bandito into a full gallop!");
            System.out.println("The wind screams past. Bullets whistle by.");
            System.out.println("You make it to your hideout just in time.\n");
            reputation -= 3;
            System.out.println("[Reputation -3: Still running]");
        } else {
            System.out.println("\n⛏️ You duck into an old mine shaft, pulling Clara with you.");
            System.out.println("Footsteps approach...\n");
            
            CowBoy billy = new CowBoy("Billy", "Whiskey", 95, "heroic");
            billy.parle("Come out, Diego! It's over!");
            pause();
            
            System.out.println("\nClara looks at you in the darkness.");
            clara.parle("Why are you doing this? You weren't always this way...");
            System.out.println("\nSomething in her voice makes you pause.");
            pause();
            
            System.out.println("For a moment, you see your old life. The ranch. Honest work.");
            System.out.println("What happened to you?\n");
            merciful = true;
            reputation += 5;
            System.out.println("[Reputation +5: A moment of humanity]");
        }
        
        pause();
        System.out.println("\n🏜️ You make it to Coyote Canyon.");
        System.out.println("But you know Billy is coming.");
        System.out.println("This ends tonight.\n");
        pause();
        
        currentState = StoryState.ACT3;
    }
    
    private void kidnappingAct3_FinalReckoning() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("          ACT 3 — THE FINAL RECKONING");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        DameStresse clara = new DameStresse("Clara", "Wine", "kidnapped", "crimson");
        
        System.out.println("The desert wind howls through the canyon.");
        System.out.println("Clara sits by the fire, watching you.\n");
        pause();
        
        System.out.println("Then... hoofbeats.");
        System.out.println("Billy the Brave arrives, silhouetted against the moon.\n");
        
        CowBoy billy = new CowBoy("Billy the Brave", "Whiskey", 95, "heroic");
        billy.sePresenter();
        billy.tirerEnLair();
        System.out.println();
        
        clara.parle("Stop this madness! Both of you!");
        System.out.println("(Clara stands between you both)\n");
        
        pause();
        System.out.println("🎯 FINAL CHOICE:");
        System.out.println("   (1) Let Clara go and fight Billy honorably");
        System.out.println("   (2) Use Clara as a shield and shoot");
        System.out.println("   (3) Surrender and beg for forgiveness\n");
        
        int choice = getValidatedNumericChoice(3);
        
        if(choice == 1) {
            System.out.println("\n💫 \"Go, Clara. This is between me and him.\"");
            System.out.println("She looks at you, surprised. Then runs to safety.\n");
            clara.seFaireLiberer(billy);
            billy.libererDame(clara);
            pause();
            
            System.out.println("🔫 You and Billy face each other.");
            System.out.println("The wind dies. Silence.\n");
            pause();
            
            System.out.println("⚡ DRAW!\n");
            pause();
            
            int roll = random.nextInt(100) + 1;
            int chance = merciful ? 65 : 45;
            System.out.println("[Rolling... Your chance: " + chance + "%]");
            
            if(roll <= chance) {
                player.tirer(billy);
                System.out.println("\nYour bullet grazes Billy. His shot misses.");
                System.out.println("You could finish him... but you don't.\n");
                
                player.parle("I'm done being the villain.");
                billy.parle("Then start over. Far from here.");
                billy.boire();
                
                System.out.println("\n✨ You ride away - the brigand who found redemption");
                System.out.println("🎭 The Outlaw Who Became Human Again");
                alive = true;
                reputation += 20;
            } else {
                billy.tirer(player);
                System.out.println("\nBilly is faster. His bullet finds your chest.");
                System.out.println("You fall to your knees...\n");
                
                player.parle("I... tried...");
                clara.parle("You could have been more...");
                System.out.println("(Clara weeps)");
                System.out.println("\n💀 The Brigand Who Almost Changed");
                alive = false;
                reputation += 5;
                currentState = StoryState.GAME_OVER;
            }
            
        } else if(choice == 2) {
            System.out.println("\n🔫 You grab Clara and put your gun to her head!");
            player.parle("Back off, Billy! Or she dies!");
            pause();
            
            billy.parle("You're a monster.");
            billy.tirer(player);
            System.out.println("BANG! His shot is perfect.\n");
            pause();
            
            System.out.println("💀 You fall dead, releasing Clara.");
            clara.seFaireLiberer(billy);
            billy.libererDame(clara);
            System.out.println("She weeps—not for you, but for what you became.");
            System.out.println("\n🎭 Justice is Done - The Coward's End");
            alive = false;
            reputation -= 20;
            currentState = StoryState.GAME_OVER;
            
        } else {
            System.out.println("\n😢 You drop your gun and fall to your knees.");
            player.parle("I'm sorry... for everything.");
            System.out.println("Clara looks at you, stunned.\n");
            pause();
            
            billy.parle("You're going to prison, Diego.");
            player.seFaireEmprisonner(billy);
            clara.seFaireLiberer(billy);
            billy.libererDame(clara);
            
            Sherif sheriff = (Sherif)npcs.get("sheriff");
            sheriff.coffrer(player);
            
            pause();
            System.out.println("\n⛓️ Years pass in a cell...");
            System.out.println("But Clara visits. She sees the man you once were.");
            System.out.println("Eventually, you're released. You return to ranch work.\n");
            
            player.echapper();
            pause();
            
            clara.changerRobe(billy);
            System.out.println("\n✨ Redemption is slow... but it comes.");
            System.out.println("🎭 The Outlaw Who Became Human Again");
            alive = true;
            reputation += 25;
            merciful = true;
        }
    }
    
    // ==================== ENDING ====================
    private void displayEpilogue() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("🎬 EPILOGUE");
        System.out.println("═".repeat(60));
        
        if(alive) {
            System.out.println("\n✨ You survived your own legend.");
            System.out.println("Status: ALIVE");
        } else {
            System.out.println("\n💀 Your story ends in dust and gunsmoke.");
            System.out.println("Status: DECEASED");
        }
        
        System.out.println("\nFinal Reputation: " + reputation);
        
        if(reputation > 40) {
            System.out.println("Legacy: REFORMED OUTLAW - Some say you found peace");
        } else if(reputation > 10) {
            System.out.println("Legacy: CONFLICTED SOUL - Neither hero nor villain");
        } else if(reputation > -10) {
            System.out.println("Legacy: RUTHLESS BRIGAND - Feared and despised");
        } else {
            System.out.println("Legacy: MONSTER - Your name is cursed");
        }
        
        System.out.println("\n" + player.quelEstTonNom() + "'s tale is told in saloons");
        System.out.println("across the West for generations to come...");
        System.out.println("═".repeat(60));
        
        if(alive && merciful) {
            System.out.println("\n🎖️  ACHIEVEMENTS UNLOCKED:");
            System.out.println("   ⭐ Redeemed Soul");
            System.out.println("   ⭐ Second Chance");
            if(reputation >= 40) System.out.println("   ⭐ Reformed Legend");
            System.out.println();
        } else if(alive) {
            System.out.println("\n🎖️  ACHIEVEMENTS UNLOCKED:");
            System.out.println("   ⭐ Survivor");
            System.out.println("   ⭐ Outlaw's Pride");
            System.out.println();
        } else {
            System.out.println("\n💀 The desert remembers your name...");
            if(reputation < 0) {
                System.out.println("   ⚰️  Died a Villain");
            } else {
                System.out.println("   ⚰️  Died with Honor");
            }
            System.out.println();
        }
    }
    
    // ==================== UTILITY METHODS ====================
    public boolean isAlive() {
        return alive;
    }
    
    public int getFinalReputation() {
        return reputation;
    }
    
    private void pause() {
        try {
            Thread.sleep(1500);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private int getValidatedNumericChoice(int maxOption) {
        while (true) {
            System.out.print("Your choice (1-" + maxOption + "): ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                } else {
                    System.out.println("\n🦹 Partner, that ain't a valid choice. Pick between 1 and " + maxOption + ".\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n🦹 Partner, that ain't a valid choice. Pick between 1 and " + maxOption + ".\n");
            }
        }
    }
}