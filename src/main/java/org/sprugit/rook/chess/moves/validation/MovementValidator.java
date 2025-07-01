package org.sprugit.rook.chess.moves.validation;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;

import java.util.Optional;

public abstract class MovementValidator {

    protected abstract MovementExecutor validate(GameMovement gm, AbstractScenario as);

    protected abstract boolean vectorValidator(MovementVector v);

    public static MovementExecutor getFor(GameMovement gm, AbstractScenario as){

        Optional<GamePiece> piece = as.getPieces().pieceAt(gm.getFrom());

        if(piece.isEmpty())
            throw new BoardInventory.InvalidMoveException();

        if(gm.getFrom().equals(gm.getTo()))
            throw new BoardInventory.InvalidMoveException();

        return switch(piece.get().getPiece().type()){
            case PAWN -> PawnMovementValidator.pmv.validate(gm, as);
            case ROOK -> RookMovementValidator.rmv.validate(gm, as);
            case HORSE -> HorseMovementValidator.hmv.validate(gm, as);
            case BISHOP -> BishopMovementValidator.bmv.validate(gm, as);
            case QUEEN -> QueenMovementValidator.qmv.validate(gm, as);
            case KING -> KingMovementValidator.kmv.validate(gm, as);
        };
    }

}
