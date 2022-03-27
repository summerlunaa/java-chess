package chess.domain.generator;

import chess.domain.Board;
import chess.domain.piece.Piece;
import java.util.List;

@FunctionalInterface
public interface BoardGenerator {

    Board generate();
}
