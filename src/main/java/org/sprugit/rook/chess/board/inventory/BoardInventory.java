package org.sprugit.rook.chess.board.inventory;

import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.moves.Position;
import org.sprugit.rook.chess.piece.Color;
import org.sprugit.rook.chess.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoardInventory {

    public static class InvalidMoveException extends RuntimeException{}

    Map<Position, GamePiece> pieces = new HashMap<>();

    public boolean isOccupied(Position p) {
        return pieces.containsKey(p);
    }

    public void move(GameMovement gm) {

        if(!isOccupied(gm.getFrom()))
            throw new InvalidMoveException();
        if(isOccupied(gm.getTo()))
            throw new InvalidMoveException();

        GamePiece gp = pieces.remove(gm.getFrom());
        gp.setMoved();
        pieces.put(gm.getTo(), gp);

    }

    public GamePiece capture(GameMovement gm) {

        if(!isOccupied(gm.getFrom()))
            throw new InvalidMoveException();
        if(!isOccupied(gm.getTo()))
            throw new InvalidMoveException();

        GamePiece attacker = pieces.remove(gm.getFrom());
        GamePiece defender = pieces.remove(gm.getTo());
        attacker.setMoved();
        pieces.put(gm.getTo(), attacker);

        return defender;

    }

    public void swap(GameMovement gm) {

        if(!isOccupied(gm.getFrom()))
            throw new InvalidMoveException();
        if(!isOccupied(gm.getTo()))
            throw new InvalidMoveException();

        GamePiece mover = pieces.remove(gm.getFrom());
        GamePiece moved = pieces.remove(gm.getTo());
        mover.setMoved();
        moved.setMoved();
        pieces.put(gm.getTo(), mover);
        pieces.put(gm.getFrom(), moved);

    }


    public void replace(Position p, GamePiece gp) {

        if(!isOccupied(p))
            throw new InvalidMoveException();

        pieces.replace(p, gp);

    }

    public void put(Position p, GamePiece gp) {

        if(isOccupied(p))
            throw new InvalidMoveException();

        pieces.put(p, gp);

    }

    public Optional<GamePiece> pieceAt(Position p) {
        if(isOccupied(p))
            return Optional.of(pieces.get(p));
        return Optional.empty();
    }



    public List<Pair> getPositionsFor(Color c) {
        return pieces.entrySet().stream()
                .map(entry -> new Pair(entry.getKey(), entry.getValue()))
                .filter(pair -> pair.getPiece().getPiece().color().equals(c))
                .toList();
    }

    public List<Position> getPositionsForPiece(Piece p) {
        return pieces.entrySet().stream().filter(entry -> entry.getValue().getPiece().equals(p))
                .map(Map.Entry::getKey).toList();
    }

}
