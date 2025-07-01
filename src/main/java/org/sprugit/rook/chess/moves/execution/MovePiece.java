package org.sprugit.rook.chess.moves.execution;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;

public class MovePiece extends MovementExecutor{
    @Override
    public void apply(GameMovement gm, AbstractScenario as) throws InvalidMoveException {
        as.getPieces().move(gm);
    }
}
