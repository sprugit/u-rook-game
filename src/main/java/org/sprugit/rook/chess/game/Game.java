package org.sprugit.rook.chess.game;

import org.sprugit.rook.chess.board.AbstractScenario;
import org.sprugit.rook.chess.moves.execution.MovementExecutor;
import org.sprugit.rook.chess.moves.validation.MovementValidator;
import org.sprugit.game.Color;

import java.util.Set;

public class Game extends org.sprugit.game.Game {

    @Override
    public Set<org.sprugit.game.Color> getColors() {
        return Set.of(Color.BLACK, Color.WHITE);
    }

    public Game(AbstractScenario board){
        this.gameState = board;
    }

}
