package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTests {
    @Test
    public void testRookMoveAlongRank() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("h1"));
        chessboard.addPiece(rook);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(rook.canMove(chessboard, new Square("h5")));

        chessboard.removePieceAt(new Square("h1"));
        rook = new Rook(Color.WHITE, new Square("h5"));
        chessboard.addPiece(rook);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testRookMoveAlongFile() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("h3"));
        chessboard.addPiece(rook);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(rook.canMove(chessboard, new Square("d3")));

        chessboard.removePieceAt(new Square("h3"));
        rook = new Rook(Color.WHITE, new Square("d3"));
        chessboard.addPiece(rook);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testRookCannotJumpOverObstacle() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);
        ChessPiece obstacle = new Pawn(Color.WHITE, new Square("a3"));
        chessboard.addPiece(obstacle);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(rook.canMove(chessboard, new Square("a8")));

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testRookCaptureOpponentPiece() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);
        ChessPiece opponentPiece = new Pawn(Color.BLACK, new Square("a8"));
        chessboard.addPiece(opponentPiece);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(rook.canMove(chessboard, new Square("a8")));

        chessboard.removePieceAt(new Square("a1"));
        chessboard.removePieceAt(new Square("a8"));
        rook = new Rook(Color.WHITE, new Square("a8"));
        chessboard.addPiece(rook);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }
    @Test
    public void testRookCannotMoveDiagonally() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(rook.canMove(chessboard, new Square("b2")));

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }
    @Test
    public void testRookCannotJumpOverFriendlyPawn() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Rook rook = new Rook(Color.WHITE, new Square("a1"));
        chessboard.addPiece(rook);
        ChessPiece friendlyPawn = new Pawn(Color.WHITE, new Square("a2"));
        chessboard.addPiece(friendlyPawn);

        // Print chessboard state before move
        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(rook.canMove(chessboard, new Square("a3")));

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

}
