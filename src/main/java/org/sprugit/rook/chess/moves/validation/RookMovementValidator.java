package org.sprugit.rook.chess.moves.validation;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;

import java.util.Optional;

public class RookMovementValidator extends CastlingValidator{

    protected RookMovementValidator(){}

    protected static final RookMovementValidator rmv = new RookMovementValidator();

    @Override
    public MovementExecutor validate(GameMovement gm, AbstractScenario as) {

        Optional<GamePiece> pieceAtStart = as.getPieces().pieceAt(gm.getFrom());
        if(pieceAtStart.isEmpty())
            return MovementExecutor.invalid;

        if(!vectorValidator(gm.getVector()))
            return MovementExecutor.invalid;

        if(!as.isValidPath(gm))
            return MovementExecutor.invalid;

        Optional<GamePiece> pieceAtEnd = as.getPieces().pieceAt(gm.getTo());

        if(pieceAtEnd.isEmpty())
            return MovementExecutor.move;

        if(!pieceAtEnd.get().getPiece().color().equals(pieceAtStart.get().getPiece().color()))
            return MovementExecutor.capture;

        return castlingCheck(gm, as);
    }

    @Override
    protected boolean vectorValidator(MovementVector v) {
        return v.isHorizontal() || v.isVertical();
    }
}
