package com.github.sebhoss.rondo;

import org.joda.time.Duration;

import com.github.sebhoss.rondo.board.Stone;

/**
 * A single move in the game of Go.
 */
public class Move {

    /** The abscissa on which the stone was placed. */
    public final int      abscissa;

    /** The ordinate on which the stone was placed. */
    public final int      ordinate;

    /** The placed stone in this move. */
    public final Stone    stone;

    /** The time the player took to think for this move. */
    public final Duration timeToThink;

    Move(final int abscissa, final int ordinate, final Stone stone, final Duration timeToThink) {
        this.abscissa = abscissa;
        this.ordinate = ordinate;
        this.stone = stone;
        this.timeToThink = timeToThink;
    }

}
