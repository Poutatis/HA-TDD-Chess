package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.*;

import java.util.Iterator;
import java.util.List;

public class ChessboardImpl implements Chessboard {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    private final ChessPiece[][] board = new ChessPieceBase[8][8];

    public static ChessboardImpl startingBoard() {
        final ChessboardImpl chessboard = new ChessboardImpl();

        chessboard.withMirroredPieceStub(PieceType.PAWN, List.of(0, 1, 2, 3, 4, 5, 6, 7), 1)
                .withMirroredPieceStub(PieceType.ROOK, List.of(0, 7), 0)
                .withMirroredPieceStub(PieceType.KNIGHT, List.of(1, 6), 0)
                .withMirroredPieceStub(PieceType.BISHOP, List.of(2, 5), 0)
                .withMirroredPieceStub(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPieceStub(PieceType.KING, List.of(4), 0);
        return chessboard;
    }

    public ChessPiece getPieceAt(final Square square) {
        return board[square.getY()][square.getX()];
    }

    public void addPiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = chessPiece;
    }

    public boolean isSquareUnderThreat(Square square, Color opponentColor) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                ChessPiece piece = getPieceAt(new Square(x, y));
                if (piece != null && piece.getColor() == opponentColor && piece.canMove(this, square)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void removePieceAt(Square square) {
        board[square.getY()][square.getX()] = null;
    }

    /**
     * Helper method to initialize chessboard with {@link ChessPieceStub}.
     * Basically mirrors all added pieces for both players.
     * When all pieces have been implemented, this should be replaced with the proper implementations.
     *
     * @param pieceType    pieceType
     * @param xCoordinates xCoordinates
     * @param yCoordinate  yCoordinateOffset
     * @return itself, like a builder pattern
     */
    private ChessboardImpl withMirroredPieceStub(final PieceType pieceType,
                                                 final List<Integer> xCoordinates, final int yCoordinate) {
        xCoordinates.forEach(xCoordinate -> {
            addPiece(new ChessPieceStub(pieceType, Color.BLACK, new Square(xCoordinate, yCoordinate)));
            addPiece(new ChessPieceStub(pieceType, Color.WHITE, new Square(xCoordinate, 7 - yCoordinate)));
        });
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
}
