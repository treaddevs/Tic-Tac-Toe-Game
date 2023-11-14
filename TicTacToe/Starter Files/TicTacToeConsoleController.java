import java.io.*;
import java.util.Scanner;

public class TicTacToeConsoleController implements TicTacToeController {
    private final Reader input;
    private final Writer output;

    public TicTacToeConsoleController(Reader input, OutputStreamWriter output) {
        this.input = input;
        this.output = output;
    }
    @Override
    public void playGame(TicTacToe model) {
        Scanner scanner = new Scanner(input);

        while (!model.isGameOver()) {
            outputGameState(model);
            outputPrompt(model);
            String inputLine = scanner.nextLine();

            if (inputLine.equalsIgnoreCase("q")) {
                outputQuitGame(model);
                return;
            }

            String[] inputs = inputLine.trim().split(" ");
            if (inputs.length == 2) {
                try {
                    int row = Integer.parseInt(inputs[0]) - 1;
                    int col = Integer.parseInt(inputs[1]) - 1;

                    try {
                        model.move(row, col);
                    } catch (IllegalArgumentException e) {
                        outputErrorMessage("\nInvalid move: " + e.getMessage());
                    }
                } catch (NumberFormatException e) {
                    outputErrorMessage("\nInvalid input. Please enter two integers for row and column.");
                }
            } else {
                outputErrorMessage("\nInvalid input format. Enter two integers separated by a space.");
            }
        }

        outputGameState(model);
        outputGameResult(model);
    }

    private void outputGameState(TicTacToe model) {
        try {
            output.write(model.toString());
            output.flush();
        } catch (IOException e) {
            // Handle IO error
        }
    }

    private void outputPrompt(TicTacToe model) {
        try {
            output.write("\nEnter a move for " + model.getTurn() + ":\n");
            output.flush();
        } catch (IOException e) {
            // Handle IO error
        }
    }

    private void outputQuitGame(TicTacToe model) {
        try {
            output.write("\nGame quit! Ending game state:\n" + model.toString() + "\n");
            output.flush();
        } catch (IOException e) {
            // Handle IO error
        }
    }

    private void outputGameResult(TicTacToe model) {
        try {
            if (model.getWinner() != null) {
                output.write("\nGame is over! " + model.getWinner() + " wins!\n");
            } else {
                output.write("\nGame is a tie!\n");
            }
            output.flush();
        } catch (IOException e) {
            // Handle IO error
        }
    }

    private void outputErrorMessage(String message) {
        try {
            output.write("Error: " + message + "\n");
            output.flush();
        } catch (IOException e) {
            // Handle IO error
        }
    }
}
