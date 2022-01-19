import java.sql.Array;
import java.util.*;
import java.io.*;
import java.math.*;

/**
 * This class solves the coding game "mountain of mole hill":
 * https://www.codingame.com/ide/puzzle/a-mountain-of-a-mole-hill
 *
 * @author Justin Maruhn
 **/
class Solution {

    //Just for debugging
    private static final boolean DEBUG = false;

    /**
     * Length of a game-area side
     */
    public static final int AREA_LENGTH = 16;

    /**
     * Executes the "mountain of mole hill" solution.
     *
     * @param args Not in use here
     */
//    public static void main(String args[]) {
//        Scanner in = new Scanner(System.in);
//
//        int o_counter = 0;
//        char[] act_line = new char[AREA_LENGTH];
//        char[] line_above;
//
//        //iterate line by line over the input
//        for (int i = 0; i < AREA_LENGTH; i++) {
//            String input_line = in.nextLine();
//
//            if(DEBUG) System.err.println("The actual act_line is: " + "[" + input_line + "].");
//
//            //The old act_line is the new line_above
//            line_above = act_line;
//            //act_line is the actual input line
//            act_line = input_line.toCharArray();
//
//            //Marks, if the actual pos in the loop is inside the garden
//            boolean inside = false;
//
//            //iterate char by char over the actual line of the input
//            for (int j = 0; j < act_line.length; j++) {
//
//                /*Two cases here:
//                *   - 1) There is a '|' ->  This is clearly a middle-fence element and inverts the inside value
//                *                           every time with no other conditions, because there can't be multiple '|'
//                *                           next to another without a (garden-)border functionality for the actual line.
//                *
//                *   - 2) There is a '+' ->  A '+' is not a middle-fence element and not in every case a border in the
//                *                           actual line. To control, if it really defines the border between inside
//                *                           and outside, you have to control the character at the same pos a line above.
//                *                           If there is a second '+' above it, you know, that the '+' is the outermost
//                *                           border-element and really defines the border between inside and outside.
//                *                           A '|' above the '+' makes it to a relevant border-element for the inside/
//                *                           outside decision.
//                */
//                if ( (act_line[j] == '|') || ((act_line[j] == '+') && ((line_above[j] == '|') || (line_above[j] == '+'))) ) {
//                    inside = !inside;
//                }
//                //If the actual pos is inside a garden, the mole-hill (o) is relevant
//                else if ((act_line[j] == 'o') && inside) {
//                    o_counter += 1;
//                    if(DEBUG) System.err.println("    Counter added by one new mole-hill at pos" + "[" + j + "]");
//                }
//
//            }
//        }
//
//        // Write an answer using System.out.println()
//        // To debug: System.err.println("Debug messages...");
//
//        System.out.println(o_counter);
//    }
}