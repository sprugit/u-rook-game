package org.sprugit.rook.chess.board.flags;

public final class PlayerFlags {
    boolean hasCastled = false;

    public void setHasCastled() {
        this.hasCastled = true;
    }
    public boolean hasCastled() {
        return this.hasCastled;
    }
}
