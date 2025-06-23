package org.sprugit.rook.chess.game;

import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.Position;

import java.util.ArrayList;
import java.util.List;

public final class GameMovement {

    private Position from;
    private Position to;
    private MovementVector memoisedVector;

    public GameMovement(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public MovementVector getVector() {
        if(memoisedVector == null)
            memoisedVector = MovementVector.compute(this);
        return memoisedVector;
    }

    public List<Position> computePath() {
        List<Position> ret = new ArrayList<>();
        int xdir = 0, ydir = 0;
        Position temp = from.apply(0,0);
        if(getVector().isXVertical()) {
            ydir = (getVector().y() > 0) ? 1 : -1;
            for(int i = from.getY(), end = i+getVector().y(); i != end; i = i + ydir) {
                temp = temp.apply(0,ydir);
                ret.add(temp);
            }
        }
        if(getVector().isXHorizontal()) {
            xdir = (getVector().x() > 0) ? 1 : -1;
            for(int i = from.getX(), end = i+getVector().x(); i != end; i = i + xdir) {
                temp = temp.apply(xdir,0);
                ret.add(temp);
            }
        }
        if(getVector().isDiagonal()) {
            ydir = (getVector().y() > 0) ? 1 : -1;
            xdir = (getVector().x() > 0) ? 1 : -1;
            for(int i = 1; i <= (getVector().x() > 0 ? getVector().x() : getVector().x() * -1); i++) {
                temp = temp.apply(xdir,ydir);
                ret.add(temp);
            }

        }
        return ret;
    }

}
