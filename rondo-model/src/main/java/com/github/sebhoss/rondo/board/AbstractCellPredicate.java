package com.github.sebhoss.rondo.board;

import com.github.sebhoss.common.annotation.Nullsafe;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

abstract class AbstractCellPredicate implements Predicate<Cell<Integer, Integer, Stone>> {

    private final Table<Integer, Integer, Stone> playedStones;

    protected AbstractCellPredicate(final Table<Integer, Integer, Stone> playedStones) {
        this.playedStones = playedStones;
    }

    protected final boolean cellIsOnBoard(final int abscissa, final int ordinate) {
        return playedStones.containsRow(Integer.valueOf(abscissa))
                && playedStones.containsColumn(Integer.valueOf(ordinate));
    }

    protected final Optional<Stone> stone(final int abscissa, final int ordinate) {
        return Nullsafe.nullsafe(Optional.fromNullable(playedStones.get(Integer.valueOf(abscissa),
                Integer.valueOf(ordinate))));
    }

    protected final Cell<Integer, Integer, Stone> cell(final int abscissa, final int ordinate) {
        return Nullsafe.nullsafe(FluentIterable.from(playedStones.cellSet())
                .filter(rowMatches(Nullsafe.nullsafe(Integer.valueOf(abscissa))))
                .filter(columnMatches(Nullsafe.nullsafe(Integer.valueOf(ordinate)))).first().get());
    }

    private static Predicate<Cell<Integer, Integer, Stone>> rowMatches(final Integer row) {
        return new Predicate<Cell<Integer, Integer, Stone>>() {

            @Override
            public boolean apply(final Cell<Integer, Integer, Stone> cell) {
                return cell.getRowKey().equals(row);
            }

        };
    }

    private static Predicate<Cell<Integer, Integer, Stone>> columnMatches(final Integer column) {
        return new Predicate<Cell<Integer, Integer, Stone>>() {

            @Override
            public boolean apply(final Cell<Integer, Integer, Stone> cell) {
                return cell.getColumnKey().equals(column);
            }

        };
    }

}
