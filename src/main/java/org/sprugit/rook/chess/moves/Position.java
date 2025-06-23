package org.sprugit.rook.chess.moves;

public record Position(char x, char y) {

    public static class InvalidPositionException extends RuntimeException {}

    public static Position from(char x, char y){
        if(x < (int) 'a' || x > (int) 'z')
            throw new InvalidPositionException();
        if(y < (int) '0' || y > (int) '9')
            throw new InvalidPositionException();
        return new Position(x, y);
    }

    public static Position from(String pos){
        if(pos.length() > 2)
            throw new InvalidPositionException();
        return from(pos.charAt(0),pos.charAt(1));
    }

    public Position apply(int vectorX, int vectorY){
        int newX = (int) x + vectorX, newY = (int) y + vectorY;
        if(newX < (int) 'a' || newX > (int) 'z')
            throw new InvalidPositionException();
        if(newY < (int) '0' || newY > (int) '9')
            throw new InvalidPositionException();
        return Position.from((char) newX, (char) newY);
    }

    @Override
    public String toString(){
        return String.format("%s%s",x,y);
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o instanceof Position p)
            return this.x == p.x && this.y == p.y;
        return false;
    }

    public char getY() {
        return y;
    }

    public char getX() {
        return x;
    }
}
