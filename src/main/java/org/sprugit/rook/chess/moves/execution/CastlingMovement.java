package org.sprugit.rook.chess.moves.execution;

import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;

public class CastlingMovement extends MovementExecutor{

    @Override
    public void apply(GameMovement gm, Game g) throws InvalidMoveException {
        g.getBoard().getPieces().swap(gm);
    }


}
