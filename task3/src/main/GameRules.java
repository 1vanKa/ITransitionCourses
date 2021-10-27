package main;

public class GameRules {
    protected static String checkVictory(int p1, int p2, int length) {
        int half = length / 2;
        if (p1 == p2) return "Draw!";
        if ((p1 < p2 && p2 - p1 > half) || (p1 > p2 && p2 + length - p1 > half))
            return "Congratulations! You win!";
        return "You lose!";
    }
}
