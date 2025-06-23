package org.sprugit.rook.chess.game;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;
import org.sprugit.rook.chess.moves.validation.MovementValidator;
import org.sprugit.rook.chess.piece.Color;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public enum GameState {
        CHECKMATE,RESIGNATION,TIMEOUT,STALEMATE,INSUFICIENT_MATERIAL,FIFTY_MOVES,REPETITION,AGREEMENT,ONGOING;
    }

    public static class GameFlags {
        public boolean whiteCastled = false;
        public boolean blackCastled = false;
        public int noCaptureCounter = 0;
        public int threeFoldthreshold = 0;
    }

    private final AbstractScenario board;
    private final List<GamePiece> captured = new ArrayList<>();
    private GameState state = GameState.ONGOING;
    private Color currentPlayer = Color.WHITE;
    private GameFlags flags = new GameFlags();
    private LocalDateTime start = LocalDateTime.now();
    private List<PlayerTurn> turns = new ArrayList<>();

    public Game(AbstractScenario board){
        this.board = board;
    }

    public AbstractScenario getBoard() {
        return board;
    }

    public List<GamePiece> getCaptured() {
        return captured;
    }

    public GameFlags getFlags() {
        return flags;
    }

    public void swapCurrentPlayer() {
        currentPlayer = currentPlayer.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public void move(Color c, GameMovement gm) {

        try {
            MovementExecutor me = MovementValidator.getFor(gm, this);
            me.apply(gm,this);
            turns.add(new PlayerTurn(c, gm));
            swapCurrentPlayer();
            // check if checkmate or check in general
        } catch (RuntimeException e){
        }

    }

}
