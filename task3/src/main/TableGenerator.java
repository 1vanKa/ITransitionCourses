package main;

import com.jakewharton.fliptables.FlipTable;

public class TableGenerator {
    /**
     * Creates and returns help table for specified moves
     *
     * @param moves moves of the game
     * @return help table for specified moves
     */
    protected static String createHelpTable(String[] moves) {
        int halfMoves = moves.length / 2;
        String[][] data = new String[moves.length][2];
        for (int i = 0; i < moves.length; i++) {
            StringBuilder losesTo = new StringBuilder();
            for (int j = 1; j <= halfMoves; j++)
                losesTo.append(moves[(j + i) % moves.length]).append(", ");
            losesTo.delete(losesTo.length() - 2, losesTo.length());
            data[i] = new String[]{moves[i], losesTo.toString()};
        }
        return FlipTable.of(new String[]{"Move", "Loses to"}, data);
    }
}
