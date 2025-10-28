package western;

import storyhandler.GameManager;
import java.util.Scanner;

/**
 * Main launcher for the Western game.
 * Allows users to either play the interactive game or exit.
 */
public class Western {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║          WILD WEST ADVENTURE                   ║");
        System.out.println("║                                                ║");
        System.out.println("║  Choose your experience:                       ║");
        System.out.println("║                                                ║");
        System.out.println("║  1. Play Interactive Game                      ║");
        System.out.println("║     → Choose your character and make decisions ║");
        System.out.println("║                                                ║");
        System.out.println("║  2. Exit                                       ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.print("\nEnter your choice (1-2): ");

        int choice = getValidInput(scanner, 1, 2);

        switch (choice) {
            case 1:
                System.out.println("\n🎮 Starting Interactive Game...\n");
                GameManager game = new GameManager();
                game.startGame();
                break;

            case 2:
                System.out.println("\n👋 Thanks for visiting the Wild West!");
                System.out.println("Ride safe, partner!");
                break;
        }

        scanner.close();
    }

    /**
     * Helper method to get valid integer input
     */
    private static int getValidInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
