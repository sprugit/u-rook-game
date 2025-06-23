package org.sprugit.rook.chess.game;

import org.sprugit.rook.chess.piece.Color;

import java.time.LocalDateTime;

public class PlayerTurn {

    private Color player;
    private GameMovement gm;
    private LocalDateTime submitted = LocalDateTime.now();

    public PlayerTurn(Color player, GameMovement gm) {
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
