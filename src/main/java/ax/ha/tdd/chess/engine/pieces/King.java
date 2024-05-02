package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class King extends ChessPieceBase implements ChessPiece {

    public King(Color player, Square location) {
        super(PieceType.KING, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        int currentX = location.getX();
        int currentY = location.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        // Check if the destination is within one square in any direction
        int dx = Math.abs(destX - currentX);
        int dy = Math.abs(destY - currentY);
        if (dx > 1 || dy > 1) {
            return false; // King can only move one square in any direction
        }

        // Check if the destination square is empty or contains an opponent's piece
        ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
        if (pieceAtDestination != null && pieceAtDestination.getColor() == getColor()) {
            return false; // Cannot move to a square occupied by own piece
        }

        // Check if moving to the destination square would place the king in check
        Color opponentColor = (getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;
        if (chessboard.isSquareUnderThreat(destination, opponentColor)) {
            return false; // Cannot move to a square under threat
        }

        return true;
    }


}
