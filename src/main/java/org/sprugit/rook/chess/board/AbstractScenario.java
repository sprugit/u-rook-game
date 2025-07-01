package org.sprugit.rook.chess.board;

import org.sprugit.game.Color;
import org.sprugit.game.GameState;
import org.sprugit.game.Turn;
import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.ChessTurn;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.Position;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;
import org.sprugit.rook.chess.moves.validation.MovementValidator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScenario implements GameState {

    public static class InvalidLabelException extends RuntimeException{
        public InvalidLabelException(String pos) {super(String.format("%s isn't a valid game position",pos));}
    }

    public enum GameState {
        CHECKMATE,RESIGNATION,TIMEOUT,STALEMATE,INSUFICIENT_MATERIAL,FIFTY_MOVES,REPETITION,AGREEMENT,ONGOING;
    }

    private GameState state = GameState.ONGOING;
    protected BoardInventory pieces;
    private Color currentPlayer = Color.WHITE;
    private final List<GamePiece> captured = new ArrayList<>();

    public abstract boolean isValidTile(Position pos);

    public void swapCurrentPlayer() {
        currentPlayer = currentPlayer.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

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

    public GameState getState() { return state; }

    public List<GamePiece> getCapturedPieces() { return captured; }

    public void apply(Turn turn) {
        if(turn instanceof ChessTurn ct){
            MovementValidator.getFor(ct.getMovement(), this)
                    .apply(ct.getMovement(), this);
            swapCurrentPlayer();
        }
    }
}
