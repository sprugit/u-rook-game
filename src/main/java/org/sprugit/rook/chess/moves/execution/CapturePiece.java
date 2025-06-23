package org.sprugit.rook.chess.moves.execution;

import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;

public class CapturePiece extends MovementExecutor{
    @Override
    public void apply(GameMovement gm, Game g) throws InvalidMoveException {
        g.getCaptured().add(g.getBoard().getPieces().capture(gm));
    }
}
