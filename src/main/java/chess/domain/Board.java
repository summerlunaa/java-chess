package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private final List<List<Piece>> board;

    public Board(List<List<Piece>> board) {
        this.board = board;
    }

    public Board place(int rankIndex, int fileIndex, Piece piece) {
        List<List<Piece>> newBoard = new ArrayList<>(board);
        newBoard.get(rankIndex).set(fileIndex, piece);
        return new Board(newBoard);
    }

    public int countPawn(Color color, int fileIndex) {
        int pawnCount = 0;
        for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
            Piece piece = getPiece(rankIndex, fileIndex);
            pawnCount += calculatePawnCount(piece, color);
        }

        return pawnCount;
    }

    private int calculatePawnCount(Piece piece, Color color) {
        if (piece.isSamePieceType(PieceType.PAWN) && piece.isSameColor(color)) {
            return 1;
        }

        return 0;
    }

    public Piece getPiece(Position position) {
        return board.get(position.getRankIndex()).get(position.getFileIndex());
    }

    public Piece getPiece(int rankIndex, int fileIndex) {
        return board.get(rankIndex).get(fileIndex);
    }

    public List<List<Piece>> getBoard() {
        return Collections.unmodifiableList(board);
    }
}
