package utils;

import java.util.Scanner;

/**
 * Utility class for input validation and user choices.
 * Can be reused across multiple story handlers or menus.
 */
public class InputUtils {

    /**
     * Reads and validates a numeric player choice between 1 and maxOption.
     * Keeps asking until a valid choice is entered.
     *
     * @param scanner Scanner instance to read input
     * @param maxOption Maximum valid numeric option
     * @return Valid integer choice between 1 and maxOption
     */
    public static int getValidatedNumericChoice(Scanner scanner, int maxOption) {
        while (true) {
            System.out.print("Your choice (1-" + maxOption + "): ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= maxOption) {
                    return choice;
                } else {
                    System.out.println("\n🤠 Fella, you made the wrong choice. Choose between 1 and " + maxOption + ".\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n🤠 Fella, you made the wrong choice. Choose between 1 and " + maxOption + ".\n");
            }
        }
    }
}
