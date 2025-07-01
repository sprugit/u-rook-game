package org.sprugit.rook.chess.moves.execution;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;

public class CapturePiece extends MovementExecutor{
    @Override
    public void apply(GameMovement gm, AbstractScenario as) throws InvalidMoveException {
        as.getCapturedPieces().add(as.getPieces().capture(gm));
    }
}
