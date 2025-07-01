package org.sprugit.rook.chess.board;

import org.sprugit.game.Turn;
import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.ChessTurn;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.Position;
import org.sprugit.rook.chess.piece.Piece;

public final class StandardScenario extends AbstractScenario {

    public StandardScenario() {
        this.pieces = new BoardInventory();
        // White pieces
        this.pieces.put(Position.from("a1"),new GamePiece(Piece.wr));
        this.pieces.put(Position.from("h1"),new GamePiece(Piece.wr));
        this.pieces.put(Position.from("b1"),new GamePiece(Piece.wh));
        this.pieces.put(Position.from("g1"),new GamePiece(Piece.wh));
        this.pieces.put(Position.from("c1"),new GamePiece(Piece.wb));
        this.pieces.put(Position.from("f1"),new GamePiece(Piece.wb));
        this.pieces.put(Position.from("d1"),new GamePiece(Piece.wq));
        this.pieces.put(Position.from("e1"),new GamePiece(Piece.wk));
        for(int c = 97, end = c+8; c < end; c++) {
            this.pieces.put(Position.from(String.format("%s2", (char) c)),new GamePiece(Piece.wp));
        }

        // Black pieces
        this.pieces.put(Position.from("a8"),new GamePiece(Piece.br));
        this.pieces.put(Position.from("h8"),new GamePiece(Piece.br));
        this.pieces.put(Position.from("b8"),new GamePiece(Piece.bh));
        this.pieces.put(Position.from("g8"),new GamePiece(Piece.bh));
        this.pieces.put(Position.from("c8"),new GamePiece(Piece.bb));
        this.pieces.put(Position.from("f8"),new GamePiece(Piece.bb));
        this.pieces.put(Position.from("d8"),new GamePiece(Piece.bq));
        this.pieces.put(Position.from("e8"),new GamePiece(Piece.bk));
        for(int c = 97, end = c+8; c < end; c++) {
            this.pieces.put(Position.from(String.format("%s7", (char) c)),new GamePiece(Piece.bp));
        }
    }

    public StandardScenario(BoardInventory pieces){
        this.pieces = pieces;
    }

    @Override
    public boolean isValidTile(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return ((int) 'a' <= x && x <= (int) 'g') &&
                ((int) '1' <= y && y <= (int) '8');
    }
}
