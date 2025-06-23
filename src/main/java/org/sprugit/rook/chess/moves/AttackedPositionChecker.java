package org.sprugit.rook.chess.moves;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.board.inventory.Pair;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.piece.Color;

import java.util.List;

public class AttackedPositionChecker {

    private static AttackedPositionChecker instance;

    private AttackedPositionChecker(){}

    public static AttackedPositionChecker getInstance() {
        if(instance == null) {
            instance = new AttackedPositionChecker();
        }
        return instance;
    }

    public List<Pair> getPositionAttackers(Color c, Position p, Game g) {

        AbstractScenario board = g.getBoard();
        BoardInventory pieces = board.getPieces();

        Color attacking = Color.BLACK.equals(c) ? Color.WHITE : Color.BLACK;

        return pieces.getPositionsFor(attacking)
                .stream().filter(pair -> board.isValidPath(new GameMovement(pair.getPosition(),p)))
                .toList();
    }

    public boolean isInCheck(Color c, Position p, Game g){
        return !getPositionAttackers(c,p,g).isEmpty();
    }

}
