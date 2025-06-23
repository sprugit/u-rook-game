package org.sprugit.rook.chess.moves;

import org.sprugit.rook.chess.game.GameMovement;

public record MovementVector(int x, int y) {
    public static MovementVector compute(GameMovement gm) {
        return new MovementVector(gm.getTo().getX()-gm.getFrom().getX(),gm.getTo().getY() - gm.getFrom().getY());
    }

    public boolean isHorizontal() {
        return x != 0 ;
    }

    public boolean isVertical() {
        return y != 0;
    }

    public boolean isDiagonal() {
        return (isHorizontal() && isVertical() && (x * -1 == y || x == y));
    }

    public boolean isXHorizontal() {return isHorizontal() && !isVertical();}

    public boolean isXVertical() {return !isHorizontal() && isVertical();}

}