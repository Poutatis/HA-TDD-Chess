package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.Bishop;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTests {

    @Test
    public void testDiagonalMovementWithoutObstacles() {
        // Arrange
        ChessboardImpl chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(bishop.canMove(chessboard, new Square("a3")));
        assertTrue(bishop.canMove(chessboard, new Square("f4")));
        assertTrue(bishop.canMove(chessboard, new Square("h6")));
    }

    @Test
    public void testDiagonalMovementWithObstacles() {
        // Arrange
        ChessboardImpl chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);
        ChessPiece pawn1 = new Pawn(Color.WHITE, new Square("b2"));
        chessboard.addPiece(pawn1);
        ChessPiece pawn2 = new Pawn(Color.WHITE, new Square("e3"));
        chessboard.addPiece(pawn2);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(bishop.canMove(chessboard, new Square("a3"))); // Blocked by own piece
        assertFalse(bishop.canMove(chessboard, new Square("f4"))); // Blocked by own piece
    }

    @Test
    public void testCaptureOpponentPiece() {
        // Arrange
        ChessboardImpl chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);
        ChessPiece opponentPiece = new Pawn(Color.BLACK, new Square("f4"));
        chessboard.addPiece(opponentPiece);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(bishop.canMove(chessboard, new Square("f4"))); // Can capture opponent's piece

        chessboard.removePieceAt(new Square("c1"));
        chessboard.removePieceAt(new Square("f4"));
        bishop = new Bishop(Color.WHITE, new Square("f4"));
        chessboard.addPiece(bishop);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testNonDiagonalMovement() {
        // Arrange
        ChessboardImpl chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        chessboard.addPiece(bishop);

        // Assert
        assertFalse(bishop.canMove(chessboard, new Square("c2"))); // Non-diagonal movement
    }

    @Test
    public void testCaptureOwnPiece() {
        // Arrange
        ChessboardImpl chessboard = new ChessboardImpl();
        Bishop bishop = new Bishop(Color.WHITE, new Square("c1"));
        Pawn ownPiece = new Pawn(Color.WHITE, new Square("d2")); // Same color as the moving Bishop
        chessboard.addPiece(bishop);
        chessboard.addPiece(ownPiece);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(bishop.canMove(chessboard, new Square("d2"))); // Attempting to capture own piece
    }

}
