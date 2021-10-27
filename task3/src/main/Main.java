package main;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] moves = args;
        Scanner scanner = new Scanner(System.in);
        while (true)
            if (isCorrectMoves(moves))
                break;
            else {
                System.out.println("Enter moves: ");
                moves = scanner.nextLine().split(" ");
            }
        game(moves);
    }

    private static void game(String[] moves) {
        int computerMove = computerMove(moves);
        SecureRandom random = new SecureRandom();
        byte[] key = random.generateSeed(16);
        byte[] hmac = HMAC.hmacSHA256(key, moves[computerMove].getBytes());
        String hmacString = HMAC.bytesToHex(hmac);
        System.out.println("HMAC: " + hmacString);
        printMenu(moves);
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {System.out.print("Enter your move: ");
            input = scanner.nextLine();
            if ("0".equals(input)) System.exit(0);
            if ("?".equals(input)) {
                System.out.println(TableGenerator.createHelpTable(moves));
                continue;
            }
            if (isMoveInInputInGame(input, moves.length))
                break;
            System.out.println("It is not a command from menu.");
        }
        System.out.println("Your move: " + moves[Integer.parseInt(input) - 1]);
        System.out.println("Computer move: " + moves[computerMove]);
        System.out.println(GameRules.checkVictory(Integer.parseInt(input) - 1,
                computerMove, moves.length));
        System.out.println("HMAC key: " + HMAC.bytesToHex(key));
    }

    private static int computerMove(String[] moves) {
        SecureRandom random = new SecureRandom();
        byte[] randomByte = random.generateSeed(1);
        return (randomByte[0] - Byte.MIN_VALUE) / ((Byte.MAX_VALUE - Byte.MIN_VALUE) / moves.length);

    }

    private static boolean isMoveInInputInGame(String input, int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++)
            if (Integer.toString(i + 1).equals(input))
                return true;
        return false;
    }

    private static void printMenu(String[] moves) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < moves.length; )
            str.append(i + 1).append(" - ").append(moves[i++]).append('\n');
        str.append("0 - exit\n? - help");
        System.out.println(str);
    }

    private static boolean isCorrectMoves(String[] moves) {
        if (moves.length == 0) return false;
        if (moves.length % 2 == 0) {
            System.out.println("Wrong input! You should enter odd number of moves. For example:\n"
                    + "1 2 3 4 5 //odd number - approved\n"
                    + "1 2 3 4 //even number - not approved");
            return false;
        } else if (moves.length < 3) {
            System.out.println("Wrong input! You should enter more than 2 moves. For example:\n"
                    + "1 2 3 //three moves - approved\n"
                    + "1 //one move - not approved");
            return false;
        } else if (new HashSet<>(Arrays.asList(moves)).size() != moves.length) {
            System.out.println("Wrong input! Duplicate moves are not allowed."
                    + " You should enter unique moves. For example:\n"
                    + "1 2 3 //unique moves - approved\n"
                    + "1 1 3//duplicate moves - not approved");
            return false;
        }
        return true;
    }
}
