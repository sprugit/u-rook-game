package org.sprugit.rook.chess.board;

import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.moves.Position;

import java.util.List;

public abstract class AbstractScenario {

    public static class InvalidLabelException extends RuntimeException{
        public InvalidLabelException(String pos) {super(String.format("%s isn't a valid game position",pos));}
    }

    protected BoardInventory pieces;

    public abstract boolean isValidTile(Position pos);

    /**
     * @param gm - GameMovement describe ing the movement of a piece
     * @return true if the path is unblocked - if the first position occpied matches the target position of gm,
     * false if a tile in the path doesn't exist or is occupied by an obstacle or piece
     */
    public boolean isValidPath(GameMovement gm) {
        List<Position> pathTiles =  gm.computePath();
        if(pathTiles.size() != pathTiles.stream().filter(this::isValidTile).toList().size())
            return false; // path is interrupted by a blank tile - in case of custom scenarios
        return pathTiles.stream()
                .filter(pieces::isOccupied).findFirst()
                .map(position -> position.equals(gm.getTo()))
                .orElse(true);
    }

    public BoardInventory getPieces() {
        return pieces;
    }
}
