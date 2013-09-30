package com.github.sebhoss.rondo;

import java.util.List;

import org.joda.time.Instant;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.github.sebhoss.rondo.board.Board;
import com.google.common.collect.ImmutableList;

/**
 * The game of Go.
 */
public class Game {

    private final Board      board;
    private final List<Move> moves;
    private final Instant    creationTime;

    Game(final Board board, final List<Move> moves, final Instant creationTime) {
        this.board = board;
        this.moves = moves;
        this.creationTime = creationTime;
    }

    /**
     * @return The current board of this game.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return The previously played moves in this game.
     */
    public List<Move> getMoves() {
        return Nullsafe.nullsafe(ImmutableList.copyOf(moves));
    }

    /**
     * @return The instant this game was created.
     */
    public Instant getCreationTime() {
        return creationTime;
    }

}
