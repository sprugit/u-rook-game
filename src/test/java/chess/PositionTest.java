package chess;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sprugit.rook.chess.moves.Position;

public class PositionTest {

    @Test
    public void createPositionTest(){

        Position p = Position.from("a1");

        Assertions.assertEquals('a', p.getX());
        Assertions.assertEquals('1', p.getY());

    }

    @Test
    public void createInvalidPositionTest(){
        Assertions.assertThrows(Position.InvalidPositionException.class,() -> Position.from("aa"));

        Assertions.assertThrows(Position.InvalidPositionException.class,() -> Position.from("aaa"));
    }

    @Test
    public void movePositionTest(){

        Position p = Position.from("a1");
        Position p1 = p.apply(1,0);

        Assertions.assertEquals("b1", p1.toString());

        Position p2 = p1.apply(0,1);
        Assertions.assertEquals("b2", p2.toString());

        Position p3 = p2.apply(1,1);
        Assertions.assertEquals("c3", p3.toString());

        Position p4 = p3.apply(0,-1);
        Assertions.assertEquals("c2", p4.toString());

        Position p5 = Position.from("h8"), p6 = p5.apply(-1,-1);
        Assertions.assertEquals("g7", p6.toString());

        Assertions.assertThrows(Position.InvalidPositionException.class, ()-> p.apply(-1,-1));

    }

}
