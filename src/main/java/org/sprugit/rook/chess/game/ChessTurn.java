package org.sprugit.rook.chess.game;

import org.sprugit.game.Color;
import org.sprugit.game.Turn;

import java.time.LocalDateTime;

public class ChessTurn extends Turn {

    private GameMovement gm;
    private LocalDateTime submitted = LocalDateTime.now();

    public ChessTurn(Color player, GameMovement gm) {
        this.player = player;
        this.gm = gm;
    }

    public Color getPlayer() {
        return player;
    }

    public GameMovement getMovement() {
        return gm;
    }
}
