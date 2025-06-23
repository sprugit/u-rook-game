package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.moves.Position;

import java.util.List;

public class GameMovementTest {

    @Test
    public void createMovement() {

        GameMovement gm = new GameMovement(Position.from("a1"), Position.from("h8"));

        Assertions.assertEquals("a1", gm.getFrom().toString());
        Assertions.assertEquals("h8", gm.getTo().toString());

    }

    @Test
    public void computePathTest() {

        GameMovement gm = new GameMovement(Position.from("a1"), Position.from("h8"));
        List<Position> pos = gm.computePath();

        Assertions.assertEquals("b2,c3,d4,e5,f6,g7,h8",
                String.join(",", pos.stream().map(Position::toString).toList()));

        GameMovement gm1 = new GameMovement(Position.from("h8"), Position.from("a1"));
        List<Position> pos1 = gm1.computePath();

        Assertions.assertEquals("g7,f6,e5,d4,c3,b2,a1",
                String.join(",", pos1.stream().map(Position::toString).toList()));

        GameMovement gm2 = new GameMovement(Position.from("a8"), Position.from("h1"));
        List<Position> pos2 = gm2.computePath();

        Assertions.assertEquals("b7,c6,d5,e4,f3,g2,h1",
                String.join(",", pos2.stream().map(Position::toString).toList()));

        GameMovement gm3 = new GameMovement(Position.from("a1"), Position.from("b2"));
        List<Position> pos3 = gm3.computePath();

        Assertions.assertEquals("b2",
                String.join(",", pos3.stream().map(Position::toString).toList()));

    }
}
