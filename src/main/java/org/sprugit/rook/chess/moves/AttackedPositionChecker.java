package org.sprugit.rook.chess.moves;

import org.sprugit.game.Color;
import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.board.inventory.Pair;
import org.sprugit.rook.chess.game.Game;
import org.sprugit.rook.chess.game.GameMovement;

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

    public List<Pair> getPositionAttackers(Color c, Position p, AbstractScenario as) {

        BoardInventory pieces = as.getPieces();

        Color attacking = Color.BLACK.equals(c) ? Color.WHITE : Color.BLACK;

        return pieces.getPositionsFor(attacking)
                .stream().filter(pair -> as.isValidPath(new GameMovement(pair.getPosition(),p)))
                .toList();
    }

    public boolean isInCheck(Color c, Position p, AbstractScenario as){
        return !getPositionAttackers(c,p,as).isEmpty();
    }

}
