package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnMovingStrategy1 implements MovingStrategy {

    private static final int RANK_INDEX_STARTING_POINT = 6;
    private static final Direction MOVABLE_DIRECTION = Direction.TOP;
    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT);

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.of(sourcePosition, targetPosition);
        validateDirection(direction);
        validateMove(board, sourcePosition, targetPosition, direction);
        validateCapture(board, sourcePosition, targetPosition, direction);
    }

    private void validateDirection(Direction direction) {
        if (!(direction == MOVABLE_DIRECTION || CAPTURABLE_DIRECTIONS.contains(direction))) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateMove(Board board, Position source, Position target, Direction direction) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());

        if (direction == MOVABLE_DIRECTION) {
            validateStartingPoint(board, source, direction, rankLength);
            validateNotStartingPoint(source, rankLength);
            validateExistPiece(board.getPiece(target));
        }
    }

    private void validateStartingPoint(Board board, Position source, Direction direction, int rankLength) {
        if (source.getRankIndex() == RANK_INDEX_STARTING_POINT && rankLength == 2) {
            validateExistPieceInPath(board, source, direction);
        }
        if (source.getRankIndex() == RANK_INDEX_STARTING_POINT && rankLength > 2) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateExistPieceInPath(Board board, Position source, Direction direction) {
        Piece currentPiece = board.getPiece(source.add(direction));
        validateExistPiece(currentPiece);
    }

    private void validateExistPiece(Piece target) {
        if (!target.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private void validateNotStartingPoint(Position source, int rankLength) {
        if (source.getRankIndex() != RANK_INDEX_STARTING_POINT && rankLength != 1) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateCapture(Board board, Position source, Position target, Direction direction) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());
        int fileLength = Math.abs(source.getFileIndex() - target.getFileIndex());

        if (CAPTURABLE_DIRECTIONS.contains(direction)) {
            validateCaptureMove(rankLength, fileLength);

            Piece targetPiece = board.getPiece(target);
            validateEmptyPiece(targetPiece);
            validateSameColor(targetPiece);
        }
    }

    private void validateCaptureMove(int rankLength, int fileLength) {
        if (rankLength + fileLength != 2) {
            throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
        }
    }

    private void validateEmptyPiece(Piece targetPiece) {
        if (targetPiece.isEmpty()) {
            throw new IllegalArgumentException("target 위치에 기물이 존재하지 않아 공격할 수 없습니다.");
        }
    }

    private void validateSameColor(Piece targetPiece) {
        if (!targetPiece.isBlack()) {
            throw new IllegalArgumentException("공격은 다른 진영만 가능합니다.");
        }
    }
}
