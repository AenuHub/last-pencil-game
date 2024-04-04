package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many pencils would you like to use:");
        String number = scanner.nextLine();
        while (!numberCheck(number)) {
            number = scanner.nextLine();
        }
        System.out.println("Who will be the first (John, Jack):");
        String player = scanner.nextLine();
        while (!playerCheck(player)) {
            player = scanner.nextLine();
        }

        int numberOfPencils = Integer.parseInt(number);
        printPencils(numberOfPencils);
        boolean isWin = false;

        while (!isWin) {
            if (player.equals("John")) {
                numberOfPencils -= turn("John", numberOfPencils);
                player = "Jack";
            } else if (player.equals("Jack")) {
                numberOfPencils -= turn("Jack" , numberOfPencils);
                player = "John";
            }
            isWin = winCheck(numberOfPencils);
            printPencils(numberOfPencils);
        }
        System.out.println(player + " won!");
    }

    public static void printPencils(int numberOfPencils) {
        for (int i = 0; i < numberOfPencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

    public static boolean numberCheck(String number) {
        Scanner in = new Scanner(number);
        if (!in.hasNextInt()) {
            System.out.println("The number of pencils should be numeric");
            return false;
        } else if (Integer.parseInt(number) <= 0) {
            System.out.println("The number of pencils should be positive");
            return false;
        }
        return true;
    }

    public static boolean playerCheck(String player) {
        if (!player.equals("John") && !player.equals("Jack")) {
            System.out.println("Choose between John and Jack");
            return false;
        }
        return true;
    }

    public static boolean pencilCheck(String numberOfPencils) {
        Scanner in = new Scanner(numberOfPencils);
        if (!in.hasNextInt()) {
            System.out.println("Possible values: '1', '2' or '3'");
            return false;
        }
        if (Integer.parseInt(numberOfPencils) != 1 && Integer.parseInt(numberOfPencils) != 2 && Integer.parseInt(numberOfPencils) != 3) {
            System.out.println("Possible values: '1', '2' or '3'");
            return false;
        }
        return true;
    }

    public static boolean pencilTakenCheck(String numberOfPencilsTaken, int numberOfPencils) {
        if (pencilCheck(numberOfPencilsTaken)) {
            if (Integer.parseInt(numberOfPencilsTaken) > numberOfPencils) {
                System.out.println("Too many pencils were taken");
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean winCheck(int num) {
        return num == 0;
    }

    public static int turn(String player, int numberOfPencils) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player + "'s turn!");

        if (player.equals("Jack")) {
            int botChoice = 0;
            if (numberOfPencils % 4 == 1) {
                botChoice = new Random().nextInt(3) + 1;
                System.out.println(botChoice);
                return botChoice;
            } else {
                System.out.println((numberOfPencils + 3) % 4);
                return (numberOfPencils + 3) % 4;
            }
        }

        String numberOfPencilsTaken = scanner.next();
        while (!pencilTakenCheck(numberOfPencilsTaken, numberOfPencils)) {
            numberOfPencilsTaken = scanner.next();
        }
        return Integer.parseInt(numberOfPencilsTaken);
    }
}
