package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTests {

    @Test
    public void testMovePawnBackwardsShouldBeIllegal() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("a2"));
        chessboard.addPiece(pawn);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(pawn.canMove(chessboard, new Square("a1")));

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testPawnForwardMovementFirstMove() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("a2"));
        chessboard.addPiece(pawn);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(pawn.canMove(chessboard, new Square("a4")));

        chessboard.removePieceAt(new Square("a2"));
        pawn = new Pawn(Color.WHITE, new Square("a4"));
        chessboard.addPiece(pawn);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testPawnForwardMovementSubsequentMove() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("a4"));
        chessboard.addPiece(pawn);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(pawn.canMove(chessboard, new Square("a5")));

        chessboard.removePieceAt(new Square("a4"));
        pawn = new Pawn(Color.WHITE, new Square("a5"));
        chessboard.addPiece(pawn);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testPawnDiagonalCapture() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("a2"));
        chessboard.addPiece(pawn);
        ChessPiece opponentPiece = new Pawn(Color.BLACK, new Square("b3"));
        chessboard.addPiece(opponentPiece);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(pawn.canMove(chessboard, new Square("b3")));

        System.out.println("Chessboard after move:");
        chessboard.removePieceAt(new Square("b3"));
        chessboard.removePieceAt(new Square("a2"));
        pawn = new Pawn(Color.WHITE, new Square("b3"));
        chessboard.addPiece(pawn);
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testPawnBlockedMove() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Pawn pawn = new Pawn(Color.WHITE, new Square("e2"));
        chessboard.addPiece(pawn);
        ChessPiece blockingPiece = new Pawn(Color.BLACK, new Square("e3"));
        chessboard.addPiece(blockingPiece);

        // Assert
        assertFalse(pawn.canMove(chessboard, new Square("e3")));
        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testCaptureKing() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.BLACK, new Square("d8"));
        Pawn pawn = new Pawn(Color.WHITE, new Square("d7")); // Pawn attempting to capture the king
        chessboard.addPiece(king);
        chessboard.addPiece(pawn);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(pawn.canMove(chessboard, new Square("d8"))); // Pawn cannot capture the king
    }
}
