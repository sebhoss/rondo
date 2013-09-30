package com.github.sebhoss.rondo.board;

/**
 * Factory for Boards.
 */
public final class Boards {

    /**
     * @return A beginner Go board.
     */
    public static Board createBeginnerBoard() {
        return new Board(13);
    }

    private Boards() {
        // hidden constructor
    }

}
