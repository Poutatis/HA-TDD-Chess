package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class Pawn extends ChessPieceBase implements ChessPiece{

    public Pawn(Color player, Square location) {
        super(PieceType.PAWN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        int currentX = location.getX();
        int currentY = location.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        // Determine the direction in which the pawn should move based on its color
        int direction = (getColor() == Color.WHITE) ? -1 : 1;

        // Check if the move is capturing the opponent's king
        if (Math.abs(destX - currentX) == 1 && destY == currentY + direction) {
            ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
            if (pieceAtDestination != null && pieceAtDestination.getType() == PieceType.KING && pieceAtDestination.getColor() != getColor()) {
                return false; // Pawn cannot capture the opponent's king
            }
        }

        ChessPiece blockingPiece = chessboard.getPieceAt(destination);

        // First move can go 1-2 squares
        if ((currentY == 6 && getColor() == Color.WHITE || currentY == 1 && getColor() == Color.BLACK) && destX == currentX && (destY == currentY + direction || destY == currentY + 2 * direction)) {
            // Block check
            if (destY == currentY + 2 * direction && chessboard.getPieceAt(new Square(currentX, currentY + direction)) == null) {
                return blockingPiece == null;
            }
            return blockingPiece == null;
        }

        // Move forward or capture diagonal
        return destX == currentX && destY == currentY + direction && blockingPiece == null ||
                Math.abs(destX - currentX) == 1 && destY == currentY + direction && blockingPiece != null && blockingPiece.getColor() != getColor();
    }


}
