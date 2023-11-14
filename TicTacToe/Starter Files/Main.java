import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("TWO PLAYER TIC-TAC-TOE GAME!");
        System.out.println("");
        System.out.println("Enter a move by inputting integer coordinates (row column) separated by a space between");
        System.out.println("Continue for each round until the game finishes");
        System.out.println("");
        System.out.println("Examples: 1 1 = Places move in top leftmost corner");
        System.out.println("          3 3 = Places move in bottom rightmost corner");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("The first player is up!");
        TicTacToeModel model = new TicTacToeModel();
        TicTacToeController controller = new TicTacToeConsoleController(
                new InputStreamReader(System.in),
                new OutputStreamWriter(System.out)
        );

        controller.playGame(model);
    }
}

/**
 * Run a Tic Tac Toe game interactively on the console.
 */
/**
public class Main {
 */
    /**
     * Run a Tic Tac Toe game interactively on the console.
     */
/**
    public static void main(String[] args) {
        TicTacToeModel model = new TicTacToeModel();
        new TicTacToeConsoleController(new InputStreamReader(System.in),
                System.out).playGame(new TicTacToeModel());
    }
}
*/

