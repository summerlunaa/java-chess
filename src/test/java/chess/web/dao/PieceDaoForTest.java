package chess.web.dao;

import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.Map;

public class PieceDaoForTest implements PieceDao {

    private final Map<String, PieceDto> pieces = new HashMap<>();

    @Override
    public void save(PieceDto pieceDto) {
        pieces.put(pieceDto.getPosition(), pieceDto);
    }

    @Override
    public void update(String position, PieceDto pieceDto) {
        pieces.computeIfPresent(position, (k, v) -> pieceDto);
    }

    @Override
    public void deleteAll() {
        pieces.clear();
    }

    public PieceDto getPieceDtoByPosition(String position) {
        return pieces.get(position);
    }

    public int getSize() {
        return pieces.size();
    }
}
