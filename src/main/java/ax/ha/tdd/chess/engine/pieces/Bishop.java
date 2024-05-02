package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class Bishop extends ChessPieceBase implements ChessPiece {

    public Bishop(Color player, Square location) {
        super(PieceType.BISHOP, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        int currentX = location.getX();
        int currentY = location.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        // Check if the destination is on a diagonal path
        int dx = Math.abs(destX - currentX);
        int dy = Math.abs(destY - currentY);
        if (dx != dy) {
            return false; // Bishop can only move diagonally
        }

        // Check if there's any piece blocking the diagonal path
        int xDirection = (destX > currentX) ? 1 : -1;
        int yDirection = (destY > currentY) ? 1 : -1;
        int x = currentX + xDirection;
        int y = currentY + yDirection;
        while (x != destX && y != destY) {
            if (chessboard.getPieceAt(new Square(x, y)) != null) {
                return false; // Bishop cannot jump over pieces
            }
            x += xDirection;
            y += yDirection;
        }

        // Check if the destination square is empty or contains an opponent's piece
        ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
        return pieceAtDestination == null || pieceAtDestination.getColor() != getColor();
    }
}
