package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public abstract class PawnMovingStrategy implements MovingStrategy {

    private final int rankIndexStartingPoint;
    private final Direction movableDirection;
    private final List<Direction> capturableDirections;

    protected PawnMovingStrategy(int rankIndexStartingPoint, Direction movableDirection,
                              List<Direction> capturableDirections) {
        this.rankIndexStartingPoint = rankIndexStartingPoint;
        this.movableDirection = movableDirection;
        this.capturableDirections = capturableDirections;
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        int rankLength = Math.abs(sourcePosition.getRankIndex() - targetPosition.getRankIndex());
        int fileLength = Math.abs(sourcePosition.getFileIndex() - targetPosition.getFileIndex());
        Direction direction = Direction.of(sourcePosition, targetPosition);

        if (direction == movableDirection && isMovableLengthAtMove(sourcePosition, rankLength)) {
            validateMoveForward(board, sourcePosition, rankLength, direction);
            return;
        }
        if (capturableDirections.contains(direction) && isMovableLengthAtCapture(rankLength, fileLength)) {
            validateCapture(board, targetPosition);
            return;
        }

        throw new IllegalArgumentException("해당 기물이 갈 수 없는 경로입니다.");
    }

    private boolean isMovableLengthAtMove(Position sourcePosition, int rankLength) {
        return rankLength == 1
                || (sourcePosition.getRankIndex() == rankIndexStartingPoint && rankLength == 2);
    }

    private void validateMoveForward(Board board, Position sourcePosition, int rankLength,
                                     Direction direction) {
        Piece sourcePiece = board.getPiece(sourcePosition.add(direction));
        validateMoveTop(sourcePosition, rankLength, sourcePiece);
        validateExistPiece(sourcePiece);
    }

    private void validateMoveTop(Position source, int rankLength, Piece sourcePiece) {
        if (rankLength == 2 && source.getRankIndex() == rankIndexStartingPoint) {
            validateExistPiece(sourcePiece);
        }
    }

    private void validateExistPiece(Piece target) {
        if (!target.isEmpty()) {
            throw new IllegalArgumentException("경로에 기물이 존재하여 이동할 수 없습니다.");
        }
    }

    private boolean isMovableLengthAtCapture(int rankLength, int fileLength) {
        return rankLength + fileLength == 2;
    }

    private void validateCapture(Board board, Position targetPosition) {
        Piece targetPiece = board.getPiece(targetPosition);
        validateEmptyPiece(targetPiece);
        validateSameColor(targetPiece);
    }

    private void validateEmptyPiece(Piece targetPiece) {
        if (targetPiece.isEmpty()) {
            throw new IllegalArgumentException("target 위치에 기물이 존재하지 않아 공격할 수 없습니다.");
        }
    }

    abstract void validateSameColor(Piece targetPiece);
}
