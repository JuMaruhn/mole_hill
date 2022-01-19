import java.util.Arrays;
import java.util.Scanner;

/**
 * This class solves the coding game "mountain of mole hill" with the flood-fill algorithm:
 * https://www.codingame.com/ide/puzzle/a-mountain-of-a-mole-hill
 *
 * @author Justin Maruhn
 **/
public class Solution_FloodFill {

    //Just for debugging
    private static final boolean DEBUG = false;

    /**
     * Length of a game-area side
     */
    public static final int AREA_LENGTH = 16;

    /**
     * Represents a full game area with an additional line around it
     */
    private static char[][] area = new char[AREA_LENGTH + 2][AREA_LENGTH + 2];

    //Just an empty line from outside the garden
    private static final char[] EMPTY_NO_GARDEN_LINE = {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', };

    /**
     * Starts the erasing process for the "outside the garden" fields from the passed startpoint.
     * This method erases an area of connected fields.
     * @param x x-coord of startpoint
     * @param y y-coord of startpoint
     */
    private static void erase_outside_garden(int x, int y) {

        //If the coords are outside the area stop the trace here
        if ( (x < 0) || (y < 0) || (x > (AREA_LENGTH + 1)) || (y > (AREA_LENGTH + 1)) ) {
            return;
        }

        //A full game-area every time this method is called
        if(DEBUG) {

            System.err.println("\n");

            for (char[] line : area) {
                System.err.println(Arrays.toString(line));
            }
        }

        //If the field is an outside field, replace it by a space, else it is an inside field
        //and the trace has to stop here
        if (area[x][y] == '.' || area[x][y] == 'o') {
            area[x][y] = ' ';
        } else {
            return;
        }

        //Go in every direction
        erase_outside_garden(x + 1, y);
        erase_outside_garden(x + 1, y + 1);
        erase_outside_garden(x, y + 1);
        erase_outside_garden(x - 1, y);
        erase_outside_garden(x - 1, y - 1);
        erase_outside_garden(x, y - 1);

    }

    /**
     * Starts the process of the outside erasing.
     * If there is still a clear outside field with a '.' after the first round,
     * it starts a second.
     */
    private static void start_and_control_erasing() {

        for (int i = 0; i < AREA_LENGTH + 2; i++) {
            for (int j = 0; j < AREA_LENGTH + 2; j++) {
                if (area[i][j] == '.') {
                    erase_outside_garden(i, j);
                }
            }
        }
    }

    /**
     * This method call the erasing process and count the remaining 'o' (mole hills)
     * @return The number of 'o' (mole hills)
     */
    private static int calc_hills_in_garden() {

        int o_counter = 0;

        start_and_control_erasing();

        //There are just inside 'o' remaining
        for (int i = 1; i < AREA_LENGTH + 1; i++) {
            for (int j = 1; j < AREA_LENGTH + 1; j++) {
                if (area[i][j] == 'o') {
                    o_counter += 1;
                }
            }
        }

        return o_counter;
    }

    /**
     * Executes the "mountain of mole hill" solution.
     * This method sort out obvious 'o' (mole hills) and copies the lines into the global area-array.
     * In the end it starts the count process of the "inside the garden" 'o' (mole hills) and print the result.
     *
     * @param args Not in use here
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        char[] line;

        /*
        The first and last line are just additions for clear startpoints
         */
        area[0] = EMPTY_NO_GARDEN_LINE;
        area[AREA_LENGTH + 1] = EMPTY_NO_GARDEN_LINE;

        //iterate line by line over the input
        for (int i = 1; i < AREA_LENGTH + 1; i++) {

            String input_line = in.nextLine();
            line = input_line.toCharArray();

            /*
            The first and last chars are also additions for help
             */
            area[i][0] = '.';
            area[i][AREA_LENGTH + 1] = '.';

            //Controls if a field is inside or outside the garden
            boolean inside = false;
            //Number of relevant border-elements in this line
            byte border_count = 0;

            //Count the number of relevant border-elements
            for (int j = 0; j < AREA_LENGTH; j++) {
                if ( (line[j] == '|') || ((j < (AREA_LENGTH - 1)) && (line[j] == '+' && line[j + 1] == '+')) ) {
                    border_count++;
                }
            }

            //Iterate over the chars of one line
            for (int j = 1; j < AREA_LENGTH + 1; j++) {

                /*
                If the number of all border-elements in this line is an even number
                and the virtual pointer pass one, the inside value becomes inverted
                 */
                if ( ((line[j - 1] == '|') || ((1 < j) && (line[j - 2] == '+' && line[j - 1] == '+')))
                        && ((border_count % 2) == 0) ) {

                        inside = !inside;
                }

                //If the field is an 'o' and an outside field, replace it by a '.'
                if ( (line[j - 1] == 'o') && (!inside) && border_count >= 2) {
                    line[j - 1] = '.';
                }

                //copy the char to the area-array
                area[i][j] = line[j - 1];

            }

        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(calc_hills_in_garden());
    }
}
