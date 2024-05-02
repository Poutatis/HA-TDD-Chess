package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.*;
import ax.ha.tdd.chess.engine.pieces.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTests {

    @Test
    public void testHorizontalMovement() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(queen.canMove(chessboard, new Square("a4"))); // Move left
        assertTrue(queen.canMove(chessboard, new Square("h4"))); // Move right
    }

    @Test
    public void testVerticalMovement() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(queen.canMove(chessboard, new Square("d1"))); // Move up
        assertTrue(queen.canMove(chessboard, new Square("d8"))); // Move down
    }

    @Test
    public void testDiagonalMovement() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(queen.canMove(chessboard, new Square("g1"))); // Move diagonally up-right
        assertTrue(queen.canMove(chessboard, new Square("a7"))); // Move diagonally down-left
    }

    @Test
    public void testBlockedMovement() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);
        Pawn blockingPawn = new Pawn(Color.WHITE, new Square("d5")); // Blocking pawn
        chessboard.addPiece(blockingPawn);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(queen.canMove(chessboard, new Square("d8"))); // Blocked by own piece
    }

    @Test
    public void testCaptureOpponent() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);
        Pawn opponentPawn = new Pawn(Color.BLACK, new Square("d8")); // Opponent's piece
        chessboard.addPiece(opponentPawn);
        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(queen.canMove(chessboard, new Square("d8"))); // Capture opponent's piece

        chessboard.removePieceAt(new Square("d4"));
        chessboard.removePieceAt(new Square("d8"));
        queen = new Queen(Color.WHITE, new Square("d8"));
        chessboard.addPiece(queen);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testCaptureFriendly() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Queen queen = new Queen(Color.WHITE, new Square("d4"));
        chessboard.addPiece(queen);
        Pawn friendlyPawn = new Pawn(Color.WHITE, new Square("d8")); // Friendly piece
        chessboard.addPiece(friendlyPawn);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(queen.canMove(chessboard, new Square("d8"))); // Cannot capture own piece
    }


}
