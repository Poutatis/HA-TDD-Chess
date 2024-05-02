package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Queen;
import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTests {

    @Test
    public void testValidMove() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        chessboard.addPiece(king);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Act & Assert
        assertTrue(king.canMove(chessboard, new Square("e2"))); // Move one square forward
        assertTrue(king.canMove(chessboard, new Square("f1"))); // Move one square to the right
        assertTrue(king.canMove(chessboard, new Square("d1"))); // Move one square to the left
        assertTrue(king.canMove(chessboard, new Square("f2"))); // Move one square diagonally
        assertTrue(king.canMove(chessboard, new Square("d2"))); // Move one square diagonally
    }

    @Test
    public void testInvalidMove() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        chessboard.addPiece(king);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Act & Assert
        assertFalse(king.canMove(chessboard, new Square("e3"))); // Move two squares forward
        assertFalse(king.canMove(chessboard, new Square("g1"))); // Move two squares to the right
        assertFalse(king.canMove(chessboard, new Square("c1"))); // Move two squares to the left
        assertFalse(king.canMove(chessboard, new Square("g3"))); // Move two squares diagonally
        assertFalse(king.canMove(chessboard, new Square("c3"))); // Move two squares diagonally
    }

    @Test
    public void testCaptureOpponent() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        chessboard.addPiece(king);
        chessboard.addPiece(new Pawn(Color.BLACK, new Square("f2"))); // Opponent's piece

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Act & Assert
        assertTrue(king.canMove(chessboard, new Square("f2"))); // Capture opponent's piece

        chessboard.removePieceAt(new Square("e1"));
        chessboard.removePieceAt(new Square("f2"));
        king = new King(Color.WHITE, new Square("f2"));
        chessboard.addPiece(king);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testCannotCaptureOwnPiece() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.WHITE, new Square("e1"));
        chessboard.addPiece(king);
        chessboard.addPiece(new King(Color.WHITE, new Square("e2"))); // Own piece

        // Act & Assert
        assertFalse(king.canMove(chessboard, new Square("e2"))); // Cannot capture own piece
    }

    @Test
    public void testKingCannotMoveToSquareUnderAttack() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();

        // Placera en kung och en fiendepjäs som kan hota kungen
        King king = new King(Color.WHITE, new Square("d4"));
        chessboard.addPiece(king);
        Rook rook = new Rook(Color.BLACK, new Square("e5")); // Rook hotar rutan (5, 4)
        chessboard.addPiece(rook);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Act
        boolean canMove = king.canMove(chessboard, new Square("e4")); // Försök flytta kungen till en hotad ruta

        // Assert
        assertFalse(canMove); // Förväntat resultat är att kungen inte kan flyttas till en hotad ruta

    }
}

