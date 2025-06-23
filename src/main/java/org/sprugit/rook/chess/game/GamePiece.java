package org.sprugit.rook.chess.game;

import org.sprugit.rook.chess.piece.Piece;

public class GamePiece {

    private Piece piece;
    private boolean hasMoved;

    public GamePiece(Piece piece, boolean hasMoved) {
        this.piece = piece;
        this.hasMoved = hasMoved;
    }

    public GamePiece(Piece piece) {
        this.piece = piece;
        this.hasMoved = false;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setMoved() {
        this.hasMoved = true;
    }
}
