package org.sprugit.rook.chess.moves.execution;

import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;

public class CaptureEnPassant extends MovementExecutor{
    @Override
    public void apply(GameMovement gm, Game g) throws InvalidMoveException {
        throw new RuntimeException("Unimplemented");
    }
}
