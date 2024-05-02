package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class Knight extends ChessPieceBase implements ChessPiece {

    public Knight(Color player, Square location) {
        super(PieceType.KNIGHT, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        int currentX = location.getX();
        int currentY = location.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        // Knight moves in an L-shape: 2 squares in one direction and 1 square in the other direction
        int dx = Math.abs(destX - currentX);
        int dy = Math.abs(destY - currentY);

        // Verify if the move is in an L-shape pattern
        if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
            // Check if there's a piece at the destination
            ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
            if (pieceAtDestination != null && pieceAtDestination.getType() == PieceType.KING && pieceAtDestination.getColor() != getColor()) {
                return false; // Knight cannot capture the opponent's king
            }
            return pieceAtDestination == null || pieceAtDestination.getColor() != getColor(); // Knight can move to the destination if there's no piece or if it's an opponent's piece
        }
        return false; // Knight cannot move to the destination
    }

}
