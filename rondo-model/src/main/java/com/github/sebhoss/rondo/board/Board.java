package com.github.sebhoss.rondo.board;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

/**
 * The Go board which is used to place stones.
 */
public class Board {

    private static Table<Integer, Integer, Stone> createAndInitialize(final int abscissa, final int ordinate) {
        final Table<Integer, Integer, Stone> table = Nullsafe.nullsafe(HashBasedTable.<Integer, Integer, Stone> create(
                abscissa, ordinate));

        for (int row = 1; row <= abscissa; row++) {
            for (int column = 1; column <= ordinate; column++) {
                table.put(Integer.valueOf(row), Integer.valueOf(column), Stone.EMPTY);
            }
        }

        return table;
    }

    private final Table<Integer, Integer, Stone> playedStones;

    Board(final int dimension) {
        this(dimension, dimension);
    }

    Board(final int abscissa, final int ordinate) {
        playedStones = createAndInitialize(abscissa, ordinate);
    }

    /**
     * @param abscissa
     *            The abscissa to place the stone at.
     * @param ordinate
     *            The ordinate to place the stone at.
     * @param stone
     *            The stone to place.
     */
    public void placeStone(final int abscissa, final int ordinate, final Stone stone) {
        playedStones.put(Integer.valueOf(abscissa), Integer.valueOf(ordinate), stone);
    }

    /**
     * @return All occupied cells on this board.
     */
    public Iterable<Cell<Integer, Integer, Stone>> occupiedCells() {
        return Nullsafe.nullsafe(FluentIterable.from(playedStones.cellSet()).filter(BoardPredicates.cellIsOccupied()));
    }

    /**
     * @return All empty cells on this board.
     */
    public Iterable<Cell<Integer, Integer, Stone>> emptyCells() {
        return Nullsafe.nullsafe(FluentIterable.from(playedStones.cellSet()).filter(BoardPredicates.cellIsEmpty()));
    }

    /**
     * @param color
     *            The asking color.
     * @return All cells that have freedom for the given color.
     */
    public Iterable<Cell<Integer, Integer, Stone>> cellsWithFreedom(final Stone color) {
        return Nullsafe.nullsafe(FluentIterable.from(playedStones.cellSet()).filter(
                BoardPredicates.cellsWithFreedom(playedStones, color)));
    }

    /**
     * @param color
     *            The asking color.
     * @return All cells that have no freedom for the given color.
     */
    public Iterable<Cell<Integer, Integer, Stone>> cellsWithoutFreedom(final Stone color) {
        return Nullsafe.nullsafe(FluentIterable.from(playedStones.cellSet()).filter(
                BoardPredicates.cellsWithoutFreedom(playedStones, color)));
    }

    /**
     * @return An immutable copy of the played stones so far.
     */
    public Table<Integer, Integer, Stone> getPlayedStones() {
        return Nullsafe.nullsafe(ImmutableTable.copyOf(playedStones));
    }

    /**
     * @return The maximum abscissa value.
     */
    public int getMaxAbscissa() {
        return playedStones.rowKeySet().size();
    }

    /**
     * @return The maximum ordinate value.
     */
    public int getMaxOrdinate() {
        return playedStones.columnKeySet().size();
    }

}
