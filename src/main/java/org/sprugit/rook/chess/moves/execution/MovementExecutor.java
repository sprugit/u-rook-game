package org.sprugit.rook.chess.moves.execution;

import org.sprugit.game.Color;
import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.piece.Piece;

public abstract class MovementExecutor {

    public static class InvalidMoveException extends RuntimeException{
        public InvalidMoveException(String pos) {super(String.format("%s contains no piece", pos));}
        public InvalidMoveException(Piece p, String pos) {super(String.format("%s can't move to %s", p.toString(),pos));}
        public InvalidMoveException(Color c, Piece p) {super(String.format("%s can't move piece %s",
                p.toString(),c.name().toUpperCase(),p.toString()));}

        public InvalidMoveException() {

        }
    }

    public static MovePiece move = new MovePiece();
    public static CapturePiece capture = new CapturePiece();
    public static CaptureEnPassant capturex = new CaptureEnPassant();
    public static CastlingMovement castle = new CastlingMovement();
    public static InvalidMovement invalid = new InvalidMovement();

    public abstract void apply(GameMovement gm, AbstractScenario as) throws InvalidMoveException;

}
