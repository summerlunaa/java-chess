package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import java.util.List;

public class BlackPawnMovingStrategy extends PawnMovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 1;
    private static final Direction MOVABLE_DIRECTION = Direction.BOTTOM;
    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    public BlackPawnMovingStrategy() {
        super(RANK_INDEX_STARTING_POINT, MOVABLE_DIRECTION, CAPTURABLE_DIRECTIONS);
    }

    @Override
    protected void validateSameColor(Piece targetPiece) {
        if (targetPiece.isBlack()) {
            throw new IllegalArgumentException("공격은 다른 진영만 가능합니다.");
        }
    }
}
