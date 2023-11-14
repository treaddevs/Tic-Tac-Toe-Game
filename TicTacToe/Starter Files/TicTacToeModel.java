import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  private Player turn;
  private boolean gameOver;
  private Player winner;

  public TicTacToeModel() {
    board = new Player[3][3];
    turn = Player.X;
    gameOver = false;
    winner = null;
  }

  @Override
  public void move(int r, int c) {
    // Check if the move is valid
    if (r < 0 || r >= 3 || c < 0 || c >= 3 || board[r][c] != null || isGameOver()) {
      throw new IllegalArgumentException("Invalid move");
    }

    board[r][c] = turn;
    checkForWin(r, c);
    checkForTie();
    if (!isGameOver()) {
      turn = (turn == Player.X) ? Player.O : Player.X;
    }
  }

  private void checkForWin(int r, int c) {
    // Check rows, columns, and diagonals
    if (board[r][0] == turn && board[r][1] == turn && board[r][2] == turn ||
            board[0][c] == turn && board[1][c] == turn && board[2][c] == turn ||
            r == c && board[0][0] == turn && board[1][1] == turn && board[2][2] == turn ||
            r + c == 2 && board[0][2] == turn && board[1][1] == turn && board[2][0] == turn) {
      gameOver = true;
      winner = turn;
    }
  }

  private void checkForTie() {
    if (!gameOver && Arrays.stream(board).flatMap(Arrays::stream).allMatch(cell -> cell != null)) {
      gameOver = true;
    }
  }

  @Override
  public Player getTurn() {
    return turn;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player[][] getBoard() {
    return Arrays.copyOf(board, board.length);
  }

  @Override
  public Player getMarkAt(int r, int c) {
    return board[r][c];
  }

  @Override
  public String toString() {
    return Arrays.stream(board).map(
                    row -> " " + Arrays.stream(row).map(
                            p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
  }
}

/**
public class TicTacToeModel implements TicTacToe {
  private final Player[][] board = new Player[3][3];
  private Player turn = new Player.X;
  TicTacToeModel() {

  }
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using 
    // the helpful built-in String.join method.
    /**********
    List<String> rows = new ArrayList<>();
    for(Player[] row : getBoard()) {
      List<String> rowStrings = new ArrayList<>();
      for(Player p : row) {
        if(p == null) {
          rowStrings.add(" ");
        } else {
          rowStrings.add(p.toString());
        }
      }
      rows.add(" " + String.join(" | ", rowStrings));
    }
    return String.join("\n-----------\n", rows);
  }

  @Override
  public void move(int r, int c) {
    // Check if the move is valid
    if (r < 0 || r >= 3 || c < 0 || c >= 3 || board[r][c] != null || isGameOver()) {
      throw new IllegalArgumentException("Invalid move");
    }
    if(board[r][c] != null) {
      throw new IllegalArgumentException("Space is taken");
    }
  }

  @Override
  public Player getTurn() {
    return turn;
  }

  @Override
  public boolean isGameOver() {
    return getWinner() != null || isGameOver();
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public Player[][] getBoard() {
    return Arrays.copyOf(board, board.length);
  }

  @Override
  public Player getMarkAt(int r, int c) {
    return board[r][c];
  }
}
*/