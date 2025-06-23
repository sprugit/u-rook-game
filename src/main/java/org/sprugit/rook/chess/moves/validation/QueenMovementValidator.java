package org.sprugit.rook.chess.moves.validation;

import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;

import java.util.Optional;

public class QueenMovementValidator extends MovementValidator{

    protected QueenMovementValidator(){}

    protected static final QueenMovementValidator qmv = new QueenMovementValidator();

    @Override
    public MovementExecutor validate(GameMovement gm, Game g) {

        Optional<GamePiece> pieceAtStart = g.getBoard().getPieces().pieceAt(gm.getFrom());
        if(pieceAtStart.isEmpty())
            return MovementExecutor.invalid;

        if(!vectorValidator(gm.getVector()))
            return MovementExecutor.invalid;

        if(!g.getBoard().isValidPath(gm))
            return MovementExecutor.invalid;

        Optional<GamePiece> pieceAtEnd = g.getBoard().getPieces().pieceAt(gm.getTo());

        if(pieceAtEnd.isEmpty())
            return MovementExecutor.move;

        if(!pieceAtEnd.get().getPiece().color().equals(pieceAtStart.get().getPiece().color()))
            return MovementExecutor.capture;

        return MovementExecutor.invalid;
    }

    @Override
    protected boolean vectorValidator(MovementVector v) {
        return ((v.isDiagonal() || (v.isHorizontal() || v.isVertical())));
    }
}
