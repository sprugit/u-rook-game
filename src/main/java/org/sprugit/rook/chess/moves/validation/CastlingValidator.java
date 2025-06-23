package org.sprugit.rook.chess.moves.validation;

import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.AttackedPositionChecker;
import org.sprugit.rook.chess.moves.Position;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;
import org.sprugit.rook.chess.piece.Color;
import org.sprugit.rook.chess.piece.Type;

public abstract class CastlingValidator extends MovementValidator{

    public MovementExecutor castlingCheck(GameMovement gm, Game g) {

        GamePiece pieceAtStart = g.getBoard().getPieces().pieceAt(gm.getFrom()).get();

        if(pieceAtStart.hasMoved())
            return MovementExecutor.invalid;

        GamePiece pieceAtEnd = g.getBoard().getPieces().pieceAt(gm.getTo()).get();

        if(!pieceAtStart.getPiece().color().equals(pieceAtEnd.getPiece().color()))
            return MovementExecutor.invalid;

        Color activePlayer = pieceAtStart.getPiece().color();

        if(!((pieceAtStart.getPiece().type().equals(Type.KING) && pieceAtEnd.getPiece().type().equals(Type.ROOK)) ||
                (pieceAtStart.getPiece().type().equals(Type.ROOK) && pieceAtEnd.getPiece().type().equals(Type.KING))))
            return  MovementExecutor.invalid;

        if(pieceAtEnd.hasMoved())
            return MovementExecutor.invalid;

        if(!g.getBoard().isValidPath(gm))
            return MovementExecutor.invalid;

        Position rookPosition;
        Position kingPosition;

        switch(pieceAtStart.getPiece().type()) {
            case KING -> {
                rookPosition = gm.getTo();
                kingPosition = gm.getFrom();
            }
            case ROOK -> {
                rookPosition = gm.getFrom();
                kingPosition = gm.getTo();
            }
            default -> {
                return MovementExecutor.invalid;
            }
        }

        GameMovement kingMovement = new GameMovement(kingPosition,rookPosition);

        // king must not be in check at the start of the movemnt
        if(AttackedPositionChecker.getInstance().isInCheck(activePlayer, kingPosition, g))
            return MovementExecutor.invalid;

        if(kingMovement.computePath().stream()
                .anyMatch(p -> AttackedPositionChecker.getInstance().isInCheck(activePlayer, p, g)))
            return MovementExecutor.invalid;


        return MovementExecutor.castle;
    }

}
