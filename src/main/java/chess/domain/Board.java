package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;

public class Board {

    private List<List<Piece>> board;

    public Board(List<List<Piece>> board) {
        this.board = board;
    }

    public void place(int rankIndex, int fileIndex, Piece piece) {
        board.get(rankIndex).set(fileIndex, piece);
    }

    public boolean isEmptyPosition(Position position) {
        Piece piece = getPiece(position);
        return piece.isEmpty();
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
