package org.sprugit.rook.chess.moves.validation;

import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;

import java.util.Optional;

public abstract class MovementValidator {

    protected abstract MovementExecutor validate(GameMovement gm, Game g);

    protected abstract boolean vectorValidator(MovementVector v);

    public static MovementExecutor getFor(GameMovement gm, Game g){

        Optional<GamePiece> piece = g.getBoard().getPieces().pieceAt(gm.getFrom());

        if(piece.isEmpty())
            throw new BoardInventory.InvalidMoveException();

        if(gm.getFrom().equals(gm.getTo()))
            throw new BoardInventory.InvalidMoveException();

        return switch(piece.get().getPiece().type()){
            case PAWN -> PawnMovementValidator.pmv.validate(gm, g);
            case ROOK -> RookMovementValidator.rmv.validate(gm, g);
            case HORSE -> HorseMovementValidator.hmv.validate(gm, g);
            case BISHOP -> BishopMovementValidator.bmv.validate(gm, g);
            case QUEEN -> QueenMovementValidator.qmv.validate(gm, g);
            case KING -> KingMovementValidator.kmv.validate(gm, g);
        };
    }

}
