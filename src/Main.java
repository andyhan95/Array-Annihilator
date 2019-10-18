import javafx.application.Preloader;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // Initialize all "global" variables here.
        int numberOfEnemies = 0, sumOfAllHP = 0;
        boolean enemyAlive = true;

        numberOfEnemies = getNumberOfEnemies();

        // Create the 2D array that will store the enemies and their attributes.
        int[][] enemyAttributes = new int[numberOfEnemies][3]; // The three attributes are: enemy ID, hp, and weakness multiplier

        instantiateEnemies(numberOfEnemies, enemyAttributes);

        attackLoop(numberOfEnemies, enemyAlive, enemyAttributes);
    }

    private static void attackLoop(int numberOfEnemies, boolean enemyAlive, int[][] enemyAttributes) {
        int sumOfAllHP;
        do {
            System.out.println("Would you like to poke (1), slash (2), or check all enemy stats (3)?");
            Scanner userInputScanner = new Scanner(System.in);
            int input = userInputScanner.nextInt(), enemyToAttack = 0;
            if(input == 1) {
                attack(numberOfEnemies, enemyAttributes, 1, "poke");
            }
            else if(input == 2) {
                attack(numberOfEnemies, enemyAttributes, 2, "slash");
            }
            else if(input == 3) {
                System.out.println("Printing a table of all stats...");
                for(int i = 0; i < numberOfEnemies; i++) {
                    System.out.println("Enemy " + enemyAttributes[i][0] + ", which has a weakness multiplier of " + enemyAttributes[i][2] + ", has " + enemyAttributes[i][1] + " health left.");
                }
            }
            else {
                System.out.println("You need to input 1, 2, or 3.");
            }
            sumOfAllHP = 0;
            for(int i = 0; i < numberOfEnemies; i++) {
                sumOfAllHP += enemyAttributes[i][1];
            }
            if(sumOfAllHP <= 0) {
                enemyAlive = false;
            }
        }
        while (enemyAlive); // There is at least one enemy alive
        System.out.println("Congratulations. You've defeated all enemies.\nEnding program.");
    }

    private static void instantiateEnemies(int numberOfEnemies, int[][] enemyAttributes) {
        // Using a for loop, retrieve the enemy hp and defense multiplier one-by-one from the user.
        for(int i = 0; i < numberOfEnemies; i++) {
            System.out.println("Creating enemy ID " + i + "...");
            enemyAttributes[i][0] = i; // Assigning the enemy an ID.
            System.out.println("How many hit points does enemy " + i + " have?");
            Scanner currentHPScanner = new Scanner(System.in);
            enemyAttributes[i][1] = currentHPScanner.nextInt();
            System.out.println("What is enemy " + i + "'s weakness multiplier? Higher values indicate low defense.");
            Scanner currentWMScanner = new Scanner(System.in);
            enemyAttributes[i][2] = currentWMScanner.nextInt();
        }
        System.out.println("Nice. all enemies have been instantiated.");
    }

    private static int getNumberOfEnemies() {
        int numberOfEnemies;// Retrieve the initial quantity of enemies from the user.
        System.out.println("Array Annihilator helps you keep track of various enemies and their remaining hit points.");
        System.out.println("Initializing first 2D array of enemies and their attributes...\nHow many enemies are there?");
        Scanner numberOfEnemiesScanner = new Scanner(System.in);
        numberOfEnemies = numberOfEnemiesScanner.nextInt();
        return numberOfEnemies;
    }

    private static void attack(int numberOfEnemies, int[][] enemyAttributes, int attackMultiplier, String attackType) {
        int enemyToAttack;
        System.out.println("Which enemy would you like to " + attackType + "?");
        Scanner enemyChoiceScanner = new Scanner(System.in);
        enemyToAttack = enemyChoiceScanner.nextInt();
        if(enemyToAttack >= numberOfEnemies) {
            System.out.println("That enemy doesn't exist. Out of bounds.");
        }
        else if(enemyToAttack < numberOfEnemies && enemyAttributes[enemyToAttack][1] <= 0) {
            System.out.println("That enemy is already dead!");
        }
        else if(enemyToAttack < numberOfEnemies && enemyAttributes[enemyToAttack][1] > 0) {
            enemyAttributes[enemyToAttack][1] -= attackMultiplier * enemyAttributes[enemyToAttack][2]; // This takes into account the weakness multiplier (WM).
            if(enemyAttributes[enemyToAttack][1] > 0) {
                System.out.println("Your " + attackType + " did " + (attackMultiplier * enemyAttributes[enemyToAttack][2]) + " damage.");
                System.out.println("Enemy " + enemyAttributes[enemyToAttack][0] + " now has " + enemyAttributes[enemyToAttack][1] + " hit points left.");
            }
            else {
                enemyAttributes[enemyToAttack][1] = 0; // Set HP to 0 if it becomes negative.
                System.out.println("Your " + attackType + " did " + (attackMultiplier * enemyAttributes[enemyToAttack][2]) + " damage.");
                System.out.println("Enemy " + enemyAttributes[enemyToAttack][0] + " has died!");
            }
        }
        else
        {
            System.out.println("Hmmm... Something went terribly wrong. There's a bug here.");
        }
    }
}