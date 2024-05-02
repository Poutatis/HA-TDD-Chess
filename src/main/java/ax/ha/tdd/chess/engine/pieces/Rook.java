package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Color;

public class Rook extends ChessPieceBase implements ChessPiece{

    public Rook(Color player, Square location) {
        super(PieceType.ROOK, player, location);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Square destination) {
        int currentX = location.getX();
        int currentY = location.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        // Rook moves in straight lines along ranks (rows) or files (columns)
        if (destX == currentX || destY == currentY) {
            // Check if there are any pieces blocking the rook's path
            int dx = (destX - currentX == 0) ? 0 : (destX - currentX) / Math.abs(destX - currentX);
            int dy = (destY - currentY == 0) ? 0 : (destY - currentY) / Math.abs(destY - currentY);

            int x = currentX + dx;
            int y = currentY + dy;

            while (x != destX || y != destY) {
                if (chessboard.getPieceAt(new Square(x, y)) != null) {
                    return false; // There is a piece blocking the rook's path
                }
                x += dx;
                y += dy;
            }

            // Check if the destination square is occupied by an opponent's piece
            ChessPiece pieceAtDestination = chessboard.getPieceAt(destination);
            return pieceAtDestination == null || pieceAtDestination.getColor() != getColor();
        }

        return false; // Rook cannot move diagonally
    }
}
