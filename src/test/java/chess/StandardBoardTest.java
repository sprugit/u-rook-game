package chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sprugit.rook.chess.board.inventory.BoardInventory;
import org.sprugit.rook.chess.game.GameMovement;
import org.sprugit.rook.chess.game.GamePiece;
import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.board.StandardBoard;
import org.sprugit.rook.chess.moves.Position;
import org.sprugit.rook.chess.piece.Piece;
import org.sprugit.rook.chess.piece.Type;

public class StandardBoardTest {

    @Test
    public void initBoardTest() {

        AbstractScenario as = new StandardBoard();

        Assertions.assertEquals(Type.ROOK, as.getPieces().pieceAt(Position.from("a1")).get().getPiece().type());

    }

    @Test
    public void pathValidationTest() {

        BoardInventory pieces = new BoardInventory();

        GamePiece moving = new GamePiece(Piece.wp, true);
        Position start = Position.from("d4");
        pieces.put(start,moving);
        pieces.put(Position.from("e5"),new GamePiece(Piece.bp, true));

        AbstractScenario as = new StandardBoard(pieces);

        Position target1 = Position.from("d6");
        Position target2 = Position.from("f4");
        Position target3 = Position.from("f5");
        Position target4 = Position.from("d5");

        GameMovement valid = new GameMovement(start, target1);
        Assertions.assertTrue(as.isValidPath(valid));
        pieces.move(valid);

        GameMovement invalid = new GameMovement(target1, target2);
        Assertions.assertFalse(as.isValidPath(invalid));
        pieces.move(invalid);

        valid = new GameMovement(target2, target3);
        Assertions.assertTrue(as.isValidPath(valid));
        pieces.move(valid);

        invalid = new GameMovement(target3, target4);
        Assertions.assertFalse(as.isValidPath(invalid));
        pieces.move(invalid);


    }
}
