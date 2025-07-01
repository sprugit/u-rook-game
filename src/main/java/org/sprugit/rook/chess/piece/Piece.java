package org.sprugit.rook.chess.piece;

import org.sprugit.game.Color;

public record Piece(Color color, Type type) {

    public static Piece wp = new Piece(Color.WHITE,Type.PAWN);
    public static Piece wr = new Piece(Color.WHITE,Type.ROOK);
    public static Piece wb = new Piece(Color.WHITE,Type.BISHOP);
    public static Piece wh = new Piece(Color.WHITE,Type.HORSE);
    public static Piece wq = new Piece(Color.WHITE,Type.QUEEN);
    public static Piece wk = new Piece(Color.WHITE,Type.KING);

    public static Piece bp = new Piece(Color.BLACK,Type.PAWN);
    public static Piece br = new Piece(Color.BLACK,Type.ROOK);
    public static Piece bb = new Piece(Color.BLACK,Type.BISHOP);
    public static Piece bh = new Piece(Color.BLACK,Type.HORSE);
    public static Piece bq = new Piece(Color.BLACK,Type.QUEEN);
    public static Piece bk = new Piece(Color.BLACK,Type.KING);

    @Override
    public Color color() {
        return color;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public String toString(){
        return color.name().toUpperCase() + " " + type.name().toUpperCase();
    }
}
