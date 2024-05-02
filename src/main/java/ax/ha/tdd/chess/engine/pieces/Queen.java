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

        // Check if the destination is on a diagonal path
        int dx = Math.abs(destX - currentX);
        int dy = Math.abs(destY - currentY);
        if ((dx != 0 && dy != 0) && (dx != dy)) {
            return false; // Queen can move either horizontally, vertically, or diagonally
        }

        // Check if there's any piece blocking the path
        if (dx == 0 || dy == 0) { // Horizontal or vertical movement (like Rook)
            if (dx == 0) { // Vertical movement
                int yDirection = (destY > currentY) ? 1 : -1;
                int y = currentY + yDirection;
                while (y != destY) {
                    if (chessboard.getPieceAt(new Square(currentX, y)) != null) {
                        return false; // Queen cannot jump over pieces
                    }
                    y += yDirection;
                }
            } else { // Horizontal movement
                int xDirection = (destX > currentX) ? 1 : -1;
                int x = currentX + xDirection;
                while (x != destX) {
                    if (chessboard.getPieceAt(new Square(x, currentY)) != null) {
                        return false; // Queen cannot jump over pieces
                    }
                    x += xDirection;
                }
            }
        } else { // Diagonal movement (like Bishop)
            int xDirection = (destX > currentX) ? 1 : -1;
            int yDirection = (destY > currentY) ? 1 : -1;
            int x = currentX + xDirection;
            int y = currentY + yDirection;
            while (x != destX && y != destY) {
                if (chessboard.getPieceAt(new Square(x, y)) != null) {
                    return false; // Queen cannot jump over pieces
                }
                x += xDirection;
                y += yDirection;
            }
        }

        // Check if the destination square is empty or contains an opponent's piece
        ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
        return pieceAtDestination == null || pieceAtDestination.getColor() != getColor();
    }
}
