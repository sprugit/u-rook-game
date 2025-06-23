package org.sprugit.rook.chess.moves.validation;

import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.Position;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;
import org.sprugit.rook.chess.piece.Color;

import java.util.Optional;

public class PawnMovementValidator extends MovementValidator{

    protected PawnMovementValidator(){}

    protected static final PawnMovementValidator pmv = new PawnMovementValidator();

    @Override
    public MovementExecutor validate(GameMovement gm, Game g) {

        BoardInventory pieces = g.getBoard().getPieces();

        Optional<GamePiece> pieceAtStart = pieces.pieceAt(gm.getFrom());
        if(pieceAtStart.isEmpty())
            return MovementExecutor.invalid;

        Color pieceColor = pieceAtStart.get().getPiece().color();

        MovementVector v = gm.getVector();

        boolean movingUp = v.y() > 0;

        if(movingUp && pieceAtStart.get().getPiece().color().equals(Color.BLACK)
        || !movingUp && pieceAtStart.get().getPiece().color().equals(Color.WHITE))
            return MovementExecutor.invalid;

        if(!vectorValidator(v))
            return MovementExecutor.invalid;
        if(isMovementVector(v, 2) && pieceAtStart.get().hasMoved())
            return MovementExecutor.invalid;

        if(!g.getBoard().isValidPath(gm))
            return MovementExecutor.invalid;

        Optional<GamePiece> pieceAtEnd = pieces.pieceAt(gm.getTo());

        if(!isCaptureVector(v) && pieceAtEnd.isPresent())
            return MovementExecutor.invalid;

        if(!isCaptureVector(v) && pieceAtEnd.isEmpty())
            return MovementExecutor.move;

        if(pieceAtEnd.isPresent() && isCaptureVector(v)
                && pieceAtEnd.get().getPiece().color().equals(pieceColor))
            return MovementExecutor.capture;

        // Movement is diagonal and target isnt present - verify EnPassant
        Position behind = gm.getTo().apply(v.x(), v.y());
        if (pieces.isOccupied(behind) && !pieces.pieceAt(behind).get().getPiece().color().equals(pieceColor))
            //return MovementExecutor.capturex; Unimplemented
            return MovementExecutor.invalid;

        return MovementExecutor.invalid; // invalid movement fallthrough
    }

    @Override
    protected boolean vectorValidator(MovementVector v) {
        return isCaptureVector(v) || isMovementVector(v, 1) || isMovementVector(v, 2);
    }

    protected boolean isCaptureVector(MovementVector v){
        int mult =  v.x() * v.y();
        return v.isDiagonal()  && ((mult == -1) || ( mult == 1));
    }

    protected boolean isMovementVector(MovementVector v, int steps){
        return v.isVertical() && (v.y() == steps || v.y() == -steps);
    }
}
