import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // Initialize all "global" variables here.
        int numberOfEnemies = 0;

        // Retrieve the initial quantity of enemies from the user.
        System.out.println("Array Annihilator helps you keep track of various enemies and their remaining hit points.");
        System.out.println("Initializing first 2D array of enemies and their attributes...\nHow many enemies should we start with?");
        Scanner numberOfEnemiesScanner = new Scanner(System.in);
        numberOfEnemies = numberOfEnemiesScanner.nextInt();

        // Create the 2D array that will store the enemies and their attributes.
        int[][] enemyAttributes = new int[numberOfEnemies][3]; // The three attributes are: ID, hp, and defense multiplier

        // Using nested loops, retrieve the enemy hp and defense multiplier one-by-one from the user.

        // Begin the main combat loop in which users can deal slash or poke damage to an enemy of their choice.

        // Introduce an "escape key" that allows users to see all enemy's stats in a tabular format.
    }
}