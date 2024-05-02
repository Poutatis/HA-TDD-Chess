package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.King;
import ax.ha.tdd.chess.engine.pieces.Knight;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {
    @Test
    public void testKnightMoveLShape() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e4"));
        chessboard.addPiece(knight);


        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(knight.canMove(chessboard, new Square("d2")));
        assertTrue(knight.canMove(chessboard, new Square("c3")));
        assertTrue(knight.canMove(chessboard, new Square("c5")));
        assertTrue(knight.canMove(chessboard, new Square("d6")));
        assertTrue(knight.canMove(chessboard, new Square("f6")));
        assertTrue(knight.canMove(chessboard, new Square("g5")));
        assertTrue(knight.canMove(chessboard, new Square("g3")));
        assertTrue(knight.canMove(chessboard, new Square("f2")));

    }
    @Test
    public void testKnightCannotMoveInStraightLines() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e4"));
        chessboard.addPiece(knight);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(knight.canMove(chessboard, new Square("e2"))); // Straight vertical move
        assertFalse(knight.canMove(chessboard, new Square("e7"))); // Straight vertical move
        assertFalse(knight.canMove(chessboard, new Square("a4"))); // Straight horizontal move
        assertFalse(knight.canMove(chessboard, new Square("h4"))); // Straight horizontal move
    }
    @Test
    public void testKnightCannotCaptureFriendlyPiece() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("e4"));
        chessboard.addPiece(knight);
        ChessPiece friendlyPiece = new Pawn(Color.WHITE, new Square("c3")); // Place a friendly piece in the path of the Knight
        chessboard.addPiece(friendlyPiece);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(knight.canMove(chessboard, new Square("c3"))); // Try to capture the friendly piece

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }
    @Test
    public void testKnightJumpsOverFriendlyAndCapturesEnemy() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        Knight knight = new Knight(Color.WHITE, new Square("f3"));
        chessboard.addPiece(knight);
        ChessPiece whitePawn1 = new Pawn(Color.WHITE, new Square("f4"));
        chessboard.addPiece(whitePawn1);
        ChessPiece whitePawn2 = new Pawn(Color.WHITE, new Square("e4"));
        chessboard.addPiece(whitePawn2);
        ChessPiece blackPawn = new Pawn(Color.BLACK, new Square("e5"));
        chessboard.addPiece(blackPawn);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertTrue(knight.canMove(chessboard, new Square("e5"))); // Knight should be able to capture the enemy piece

        chessboard.removePieceAt(new Square("f3"));
        chessboard.removePieceAt(new Square("e5"));
        knight = new Knight(Color.WHITE, new Square("e5"));
        chessboard.addPiece(knight);

        System.out.println("Chessboard after move:");
        System.out.println(new ChessboardWriter().print(chessboard));
    }

    @Test
    public void testCaptureKing() {
        // Arrange
        Chessboard chessboard = new ChessboardImpl();
        King king = new King(Color.BLACK, new Square("e8"));
        Knight knight = new Knight(Color.WHITE, new Square("d6")); // Knight attempting to capture the king
        chessboard.addPiece(king);
        chessboard.addPiece(knight);

        System.out.println("Chessboard before move:");
        System.out.println(new ChessboardWriter().print(chessboard));

        // Assert
        assertFalse(knight.canMove(chessboard, new Square("e8"))); // Knight cannot capture the king
    }
}
