package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class Queen extends ChessPieceBase implements ChessPiece {

    public Queen(Color player, Square location) {
        super(PieceType.QUEEN, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        int currentX = location.getX();
        int currentY = location.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        // Check if the destination is on a diagonal path, horizontal, or vertical path
        int dx = Math.abs(destX - currentX);
        int dy = Math.abs(destY - currentY);
        if ((dx != 0 && dy != 0) && (dx != dy) && (dx != 0 || dy != 0)) {
            return false; // Queen can only move horizontally, vertically, or diagonally
        }

        // Check if there's any piece blocking the path
        if (dx == 0 || dy == 0 || dx == dy) { // Horizontal, vertical, or diagonal movement
            int xDirection = Integer.compare(destX, currentX);
            int yDirection = Integer.compare(destY, currentY);
            int x = currentX + xDirection;
            int y = currentY + yDirection;
            while (x != destX || y != destY) {
                ChessPiece pieceAtSquare = chessboard.getPieceAt(new Square(x, y));
                if (pieceAtSquare != null) {
                    return false; // Queen cannot jump over pieces
                }
                x += xDirection;
                y += yDirection;
            }
        }

        // Check if the destination square is empty or contains an opponent's piece that is not a king
        ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
        return pieceAtDestination == null ||
                (pieceAtDestination.getColor() != getColor() && pieceAtDestination.getType() != PieceType.KING);
    }

}
