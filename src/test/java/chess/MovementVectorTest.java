package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.moves.MovementVector;
import org.sprugit.rook.chess.moves.Position;

public class MovementVectorTest {

    @Test
    public void validMovementsTest() {

        GameMovement pawn = new GameMovement(Position.from("a1"),Position.from("a2"));
        GameMovement bishop = new GameMovement(Position.from("a1"),Position.from("h8"));

        MovementVector pawnv = MovementVector.compute(pawn);
        MovementVector bishopv = MovementVector.compute(bishop);

        Assertions.assertTrue(pawnv.isVertical());
        Assertions.assertFalse(pawnv.isHorizontal());
        Assertions.assertTrue(bishopv.isDiagonal());

    }

}
