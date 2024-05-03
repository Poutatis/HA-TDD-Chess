package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;

public class GameImpl implements Game{

    final ChessboardImpl board = ChessboardImpl.startingBoard();

    boolean isNewGame = true;
    // Variable to store move count
    int moveCount = 0;
    // Variable to store the move result
    private String lastMoveResult;

    @Override
    public Color getPlayerToMove() {
        //TODO this should reflect the current state.
        return isNewGame ? Color.WHITE : (moveCount % 2 == 0 ? Color.WHITE : Color.BLACK);
    }

    @Override
    public Chessboard getBoard() {
        return board;
    }



    @Override
    public String getLastMoveResult() {
        // Return the stored move result
        return lastMoveResult != null ? lastMoveResult : (isNewGame ? "Game hasn't begun" : "Last move was successful (default reply, change this)");
    }

    @Override
    public void move(String move) {
        // Regular expression pattern for a valid move format like "a1-b2"
        String movePattern = "^[a-hA-H][1-8]-[a-hA-H][1-8]$";

        // Check if the move matches the pattern
        if (!move.matches(movePattern)) {
            lastMoveResult = "Invalid move format: " + move;
            return;
        }

        // Split the move into source and destination squares
        String[] moveParts = move.split("-");
        String sourceStr = moveParts[0];
        String destStr = moveParts[1];

        // Parse the source and destination squares
        Square source = new Square(sourceStr);
        Square destination = new Square(destStr);

        // Get the piece at the source square
        ChessPiece currentPiece = board.getPieceAt(source);
        if (currentPiece == null) {
            lastMoveResult = "No piece found at source square: " + sourceStr;
            return;
        }

        // Check if the piece can move to the destination square
        if (!currentPiece.canMove(board, destination)) {
            lastMoveResult = "Illegal move: " + move;
            return;
        }

        // Move the piece to the destination square
        board.removePieceAt(source);
        currentPiece.movePiece(destination);
        board.addPiece(currentPiece);

        // Increment the move count
        moveCount++;

        // Set the move result
        lastMoveResult = "Piece moved: " + move;

        // Update the game state
        isNewGame = false;
    }
}
