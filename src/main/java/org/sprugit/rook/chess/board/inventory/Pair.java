package org.sprugit.rook.chess.board.inventory;

import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.Position;

public final class Pair {

    private Position p;
    private GamePiece gp;

    public GamePiece getPiece() {
        return gp;
    }

    public Position getPosition() {
        return p;
    }

    public Pair(Position p, GamePiece gp) {
        this.p = p;
        this.gp = gp;
    }

    public void setP(Position p) {
        this.p = p;
    }

    public void setGp(GamePiece gp) {
        this.gp = gp;
    }
}
